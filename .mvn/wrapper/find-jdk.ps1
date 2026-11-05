$ErrorActionPreference = 'Stop'
$compiler = Get-Command javac.exe -ErrorAction SilentlyContinue
if (-not $compiler) {
    exit 1
}

$versionOutput = (& $compiler.Source -version 2>&1 | Out-String).Trim()
if ($versionOutput -notmatch 'javac\s+(\d+)') {
    Write-Error "Could not determine the JDK version from '$versionOutput'."
    exit 1
}
if ([int]$Matches[1] -lt 17) {
    Write-Error "JDK 17 or later is required; found $versionOutput."
    exit 1
}

$javaHome = Split-Path (Split-Path $compiler.Source -Parent) -Parent
if (-not (Test-Path (Join-Path $javaHome 'bin\java.exe'))) {
    Write-Error "Could not find java.exe in '$javaHome'."
    exit 1
}
Write-Output $javaHome
