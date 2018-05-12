#! /bin/bash

mkdir class
javac *.java
mv *.class class/
cd class
java Main
cd ..
rm -r class
