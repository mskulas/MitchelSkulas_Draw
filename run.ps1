# Run script for DrawApp using local OpenJFX SDK
# Edit the $jfx path below if your OpenJFX SDK is in a different location
$jfx = 'C:\Users\angry\Downloads\openjfx-21.0.8_windows-x64_bin-sdk\javafx-sdk-21.0.8\lib'

# Compile
Write-Host "Compiling..."
# Prefer the Maven source layout if present
if (Test-Path "src/main/java/DrawApp.java") {
	$src = "src/main/java/DrawApp.java"
} else {
	$src = "src/DrawApp.java"
}
javac --module-path $jfx --add-modules javafx.controls,javafx.graphics -d bin $src
if ($LASTEXITCODE -ne 0) { Write-Error "Compilation failed (exit $LASTEXITCODE)"; exit $LASTEXITCODE }

# Run (use fully-qualified class name if the source declares a package)
Write-Host "Running..."
if (Test-Path "src/main/java/DrawApp.java") {
	java --module-path $jfx --add-modules javafx.controls,javafx.graphics -cp bin main.java.DrawApp
} else {
	java --module-path $jfx --add-modules javafx.controls,javafx.graphics -cp bin DrawApp
}
