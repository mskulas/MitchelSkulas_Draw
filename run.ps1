# Run script for DrawApp using local OpenJFX SDK
# Edit the $jfx path below if your OpenJFX SDK is in a different location
$jfx = 'C:\Users\angry\Downloads\openjfx-21.0.8_windows-x64_bin-sdk\javafx-sdk-21.0.8\lib'

# Compile
Write-Host "Compiling..."
javac --module-path $jfx --add-modules javafx.controls,javafx.graphics -d bin src/DrawApp.java
if ($LASTEXITCODE -ne 0) { Write-Error "Compilation failed (exit $LASTEXITCODE)"; exit $LASTEXITCODE }

# Run
Write-Host "Running..."
java --module-path $jfx --add-modules javafx.controls,javafx.graphics -cp bin DrawApp
