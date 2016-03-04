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


echo -n "Creating session and fetching empty cart..."
expected='{"items":[],"address":null,"total":0}'
result=`curl -s -c cjar -X GET http://localhost:8080/svc/cart`
printResult

echo -n "Adding item 1 to cart..."
expected=200
result=`curl -s -i -b cjar -X PUT http://localhost:8080/svc/cart/1 | head -n 1 | cut -d$' ' -f2`
printResult

echo -n "Adding item 2 to cart..."
expected=200
result=`curl -s -i -b cjar -X PUT http://localhost:8080/svc/cart/2 | head -n 1 | cut -d$' ' -f2`
printResult

echo -n "Fetching cart..."
expected='{"items":[{"id":1,"qty":1,"price":1399.95},{"id":2,"qty":1,"price":49.99}],"address":null,"total":1449.94}'
result=`curl -s -b cjar -X GET http://localhost:8080/svc/cart`
printResult

echo -n "Updating qty on item 1 to 5..."
expected=200
result=`curl -s -i -b cjar -X POST -d qty=5 http://localhost:8080/svc/cart/1 | head -n 1 | cut -d$' ' -f2`
printResult

echo -n "Fetching cart..."
expected='{"items":[{"id":1,"qty":5,"price":1399.95},{"id":2,"qty":1,"price":49.99}],"address":null,"total":7049.74}'
result=`curl -s -b cjar -X GET http://localhost:8080/svc/cart`
printResult

# Remove Session File
rm cjar
