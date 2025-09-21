<#
install_maven.ps1
Download and install Apache Maven locally under the project in a `.maven` folder.
Usage:
  # Install default version and run mvn -v (no PATH changes persistent)
  .\install_maven.ps1

  # Install a specific version and add it to the current session PATH
  .\install_maven.ps1 -Version 3.9.6 -AddToSessionPath

  # Install and add to your user PATH (persists across sessions)
  .\install_maven.ps1 -Version 3.9.6 -AddToUserPath

Notes:
- This script downloads from the Apache archive (no admin privileges required).
- If you choose -AddToUserPath the script will modify your user PATH environment variable.
#>
param(
    [string]$Version = "3.9.6",
    [switch]$AddToSessionPath,
    [switch]$AddToUserPath
)

try {
    $scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Definition
    $destRoot = Join-Path $scriptDir ".maven"
    if (-not (Test-Path $destRoot)) { New-Item -ItemType Directory -Path $destRoot | Out-Null }

    $zipName = "apache-maven-$Version-bin.zip"
    $baseUrl = "https://archive.apache.org/dist/maven/maven-3/$Version/binaries"
    $downloadUrl = "$baseUrl/$zipName"
    $zipPath = Join-Path $destRoot $zipName

    if (-not (Test-Path $zipPath)) {
        Write-Host "Downloading Apache Maven $Version from $downloadUrl ..."
        Invoke-WebRequest -Uri $downloadUrl -OutFile $zipPath -UseBasicParsing
    }
    else {
        Write-Host "Found existing download: $zipPath"
    }

    $extractDir = Join-Path $destRoot "apache-maven-$Version"
    if (-not (Test-Path $extractDir)) {
        Write-Host "Extracting to $destRoot ..."
        Expand-Archive -Path $zipPath -DestinationPath $destRoot -Force
    }
    else {
        Write-Host "Maven already extracted at $extractDir"
    }

    $mavenBin = Join-Path $extractDir "bin"
    $mvnCmd = Join-Path $mavenBin "mvn.cmd"
    if (-not (Test-Path $mvnCmd)) { throw "mvn.cmd not found at $mvnCmd" }

    if ($AddToSessionPath) {
        Write-Host "Adding Maven bin to current session PATH"
        $env:PATH = "$mavenBin;$env:PATH"
    }

    if ($AddToUserPath) {
        Write-Host "Adding Maven bin to user PATH (will take effect in new terminals)"
        $currentUserPath = [Environment]::GetEnvironmentVariable('Path', 'User')
        if ($currentUserPath -notlike "*$mavenBin*") {
            $newUserPath = "$mavenBin;$currentUserPath"
            [Environment]::SetEnvironmentVariable('Path', $newUserPath, 'User')
            Write-Host "User PATH updated. You may need to open a new terminal for changes to take effect."
        }
        else { Write-Host "Maven bin already present in user PATH" }
    }

    Write-Host "Verifying mvn executable..."
    & "$mvnCmd" -v
    Write-Host "Apache Maven $Version installed under $extractDir"
    Write-Host "To run from this project directory without modifying PATH:"
    Write-Host "  $mavenBin\mvn.cmd <goals>"
}
catch {
    Write-Error "Installation failed: $_"
    exit 1
}
