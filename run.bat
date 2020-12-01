@echo off
Rem run with ./run
Rem This file compiles all files and runs the main() function in Database
Rem javac -cp .;dependencies/*.jar *.java
javac control/*.java view/*.java model/*.java *.java
java control/UserInterfaceController