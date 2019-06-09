@echo off
cd ..
mkdir %1
cd NeuralNetwork
xcopy "src/io/bhagat/projects/%1" "../%1" /s/h/e/k/f/c
copy "library.jar" "../%1/library.jar"
copy "j.bat" "../%1/run.bat"
copy "jc.bat" "../%1/compile.bat"
cd ../%1
del package-info.java
cd ../NeuralNetwork