java -jar ../ip.jar < input_JAR.txt > ACTUAL_JAR.TXT
# convert to UNIX format
cp EXPECTED_JAR.TXT EXPECTED-UNIX_JAR.TXT
# dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL_JAR.TXT EXPECTED-UNIX_JAR.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi