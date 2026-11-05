@echo off
setlocal

set "MAVEN_VERSION=3.9.16"
set "WRAPPER_DIR=%~dp0.mvn\wrapper"
set "MAVEN_HOME=%WRAPPER_DIR%\apache-maven-%MAVEN_VERSION%"
set "MAVEN_ZIP=%WRAPPER_DIR%\apache-maven-%MAVEN_VERSION%-bin.zip"

if not exist "%MAVEN_HOME%\bin\mvn.cmd" (
  if not exist "%WRAPPER_DIR%" mkdir "%WRAPPER_DIR%"
  echo Downloading Apache Maven %MAVEN_VERSION%...
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$ErrorActionPreference = 'Stop'; $ProgressPreference = 'SilentlyContinue'; Invoke-WebRequest 'https://archive.apache.org/dist/maven/maven-3/%MAVEN_VERSION%/binaries/apache-maven-%MAVEN_VERSION%-bin.zip' -OutFile '%MAVEN_ZIP%' -TimeoutSec 120; if ((Get-Item '%MAVEN_ZIP%').Length -lt 1000000) { throw 'Maven archive download is incomplete.' }; Expand-Archive -LiteralPath '%MAVEN_ZIP%' -DestinationPath '%WRAPPER_DIR%' -Force"
  if errorlevel 1 exit /b %errorlevel%
)

for /f "usebackq delims=" %%J in (`powershell -NoProfile -ExecutionPolicy Bypass -File "%WRAPPER_DIR%\find-jdk.ps1"`) do set "JAVA_HOME=%%J"

:java_found
if not defined JAVA_HOME (
  echo JDK 17 or later is required. Install a JDK and make javac available on PATH.
  exit /b 1
)
if not exist "%JAVA_HOME%\bin\java.exe" (
  echo Could not find java.exe next to the JDK compiler at "%JAVA_HOME%".
  exit /b 1
)
set "PATH=%JAVA_HOME%\bin;%PATH%"
call "%MAVEN_HOME%\bin\mvn.cmd" %*
exit /b %errorlevel%
