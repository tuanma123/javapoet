#!/bin/bash

git clone https://github.com/tuanma123/javapoet
cd javapoet
echo SHA: $(git rev-parse HEAD) >>testexecution.txt
time mvn test >>testexecution.txt
mvn jacoco:prepare-agent test
mvn jacoco:report

mkdir temp
cp -r testexecution.txt target/site/jacoco/index.html target/site/jacoco/jacoco-resources temp
cd temp
zip -r hw2.zip *
