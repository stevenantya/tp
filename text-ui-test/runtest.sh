#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"

cd ..
./gradlew clean shadowJar

cd text-ui-test

java -ea -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input.txt > ACTUAL.TXT

# Remove trailing whitespaces
sed -i 's/[[:blank:]]*$//' EXPECTED.TXT
sed -i 's/[[:blank:]]*$//' ACTUAL.TXT

cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix EXPECTED-UNIX.TXT ACTUAL.TXT
diff EXPECTED-UNIX.TXT ACTUAL.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
    rm assets/database.txt
    exit 0
else
    echo "Test failed!"
    rm assets/database.txt
    exit 1
fi


