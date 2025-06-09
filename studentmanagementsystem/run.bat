@echo off
cd src
javac *.java
if %errorlevel% neq 0 (
    echo Compile failed.
    pause
    exit /b
)
java Main
pause
