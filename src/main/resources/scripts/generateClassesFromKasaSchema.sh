#!/bin/bash
echo "Generating classes"
toolsDir="/home/$USER/.m2/repository/org/apache/avro/avro-tools/1.12.0/avro-tools-1.12.0.jar"
schemaDir="../../java/com/github/RakhmedovRS/kasa/kp115/KasaKP115.avsc"
outputDir=../../java/
java -jar "$toolsDir" compile schema "$schemaDir" "$outputDir"