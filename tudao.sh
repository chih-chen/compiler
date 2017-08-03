#!/bin/bash
echo "STARTING..."
java -cp antlr.jar antlr.Tool gram.g

if [ $? -eq 0 ]; then
   echo "PARSER AND LEXER GENERATED"
   javac -classpath antlr.jar:. *.java
   
   if [ $? -eq 0 ]; then
       echo "ALL CLASSES COMPILED"
       java -cp antlr.jar:. MainClass
   else 
       echo "GAVE BAD COMPILING"
   fi
else
   echo "GAVE BAD TO GENERATE PARSER"
fi