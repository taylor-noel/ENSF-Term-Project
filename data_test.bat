@echo off
Rem run with ./data_test
Rem This file compiles only the model folder and runs the main() function in Database
javac model/*.java control/Receipt.java control/User.java control/RegisteredUser.java
java model/Database