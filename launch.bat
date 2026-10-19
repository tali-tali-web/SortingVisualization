@echo off
javac -d out src\*.java src\panels\*.java src\sorts\*.java
if errorlevel 1 (
    pause
    exit /b
)

java -cp out src.SortingVisualization
pause