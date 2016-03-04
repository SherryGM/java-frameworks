#!/bin/bash

# Setup Colors
colorGreen='\e[1;32m'
colorRed='\e[1;31m'
endColor='\e[0m'

# Setup Functions
function printResult {
	if [ "$result" == "$expected" ]
	then
		echo -e "${colorGreen}OK${endColor}"
	else
		echo -e "${colorRed}ERROR${endColor}"
		echo -e "\tFound   : ${result}"
		echo -e "\tExpected: ${expected}"
	fi	
}

echo -n "Creating session and fetching empty cart..."
expected='{"items":[],"total":0.0}'
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
expected='{"items":[{"id":1,"qty":1,"price":1399.95,"name":"Pappy Van Winkle Family Reserve 20yr","shortname":"pappy-20yr"},{"id":2,"qty":1,"price":49.99,"name":"Blanton'"'"'s Original Single Barrel Bourbon Whiskey","shortname":"blantons"}],"total":1449.94}'
result=`curl -s -b cjar -X GET http://localhost:8080/svc/cart`
printResult

echo -n "Saving address..."
expected=200
result=`curl -s -i -b cjar -H "Content-Type: application/json" -d '{"name":"Michael Dowden","address":"123 Nowhere Ln","city":"Springfield","state":"TN","zip":"12345","phone":"123-456-7890","email":"xyz@null.org"}' -X PUT http://localhost:8080/svc/order/shipping | head -n 1 | cut -d$' ' -f2`
printResult

echo -n "Fetching address..."
expected='{"name":"Michael Dowden","address":"123 Nowhere Ln","city":"Springfield","state":"TN","zip":"12345","phone":"123-456-7890","email":"xyz@null.org"}'
result=`curl -s -b cjar -X GET http://localhost:8080/svc/order/shipping`
printResult

echo -n "Checkout..."
expected=200
result=`curl -s -i -b cjar -X POST http://localhost:8080/svc/order/checkout | head -n 1 | cut -d$' ' -f2`
printResult

echo -n "Order Details..."
expected='{"orderNumber":1000000,"items":[{"id":1,"qty":1,"price":1399.95,"name":"Pappy Van Winkle Family Reserve 20yr","shortname":"pappy-20yr"},{"id":2,"qty":1,"price":49.99,"name":"Blanton'"'"'s Original Single Barrel Bourbon Whiskey","shortname":"blantons"}],"address":{"name":"Michael Dowden","address":"123 Nowhere Ln","city":"Springfield","state":"TN","zip":"12345","phone":"123-456-7890","email":"xyz@null.org"},"total":1449.94}'
result=`curl -s -b cjar -X GET http://localhost:8080/svc/order/1000000`
printResult

# Remove Session File
rm cjar
