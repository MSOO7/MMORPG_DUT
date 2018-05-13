#! /bin/bash

mkdir class
javac *.java
mv *.class class/
cd class
java $1
cd ..
rm -r class
