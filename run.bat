@echo off
Rem run with ./run
Rem This file compiles all files and runs the main() function in Database
javac model/*.java control/*.java view/*.java MovieSystem.java
java MovieSystem