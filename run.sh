#!/usr/bin/bash

echo "You are in $(pwd) directory"
mkdir ../src
cp Main.java ../src/Main.java
cp test.txt ../src/test.txt
cd ../src
javac Main.java
echo "Compile OK"
java Main < test.txt
cd ..
rm -rf src
