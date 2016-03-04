#!/bin/bash

# Setup Colors
colorGreen='\e[1;32m'
colorRed='\e[1;31m'
endColor='\e[0m'

# Setup Functions
function printResult {
	if [ $result == $expected ]
	then
		echo -e "${colorGreen}OK${endColor}"
	else
		echo -e "${colorRed}ERROR${endColor}"
		echo -e "\tFound: ${result}"
		echo -e "\tExpected: ${expected}"
	fi	
}

echo -n "Creating session and fetching items..."
expected='200'
result=`curl -s -i -X GET http://localhost:8080/svc/items | head -n 1 | cut -d$' ' -f2`
printResult

echo -n "Fetching Eagle Rare..."
expected=200
result=`curl -s -i -X PUT http://localhost:8080/svc/item/eagle-rare-10yr | head -n 1 | cut -d$' ' -f2`
printResult

echo -n "Fetching Crazy Ivan (does not exist)..."
expected=404
result=`curl -s -i -X PUT http://localhost:8080/svc/item/crazy-ivan | head -n 1 | cut -d$' ' -f2`
printResult

