#!/usr/bin/env bash

comm_protocol='http'
hostname='localhost'
network_port='8080'
location="${comm_protocol}://${hostname}:${network_port}/rentalAgreement"

# TODO: Change the Tool ID to the Tool name.

echo -n "What is the ID of the tool you wish to rent? "
read tool_id

echo -n "For how many days do you wish to rent this tool? "
read rental_days

echo -n "On which date to you wish to pick up this rental? "
read checkout_date

echo -n "What is the daily rental charge for this tool? "
read daily_rental_charge

echo -n "What is the discount percentage for this rental? "
read discount_percent

rental_agreement="{
  \"toolId\":\"${tool_id}\",
  \"rentalDays\": ${rental_days},
  \"checkoutDate\": \"${checkout_date}\",
  \"dailyRentalCharge\": \"${daily_rental_charge}\",
  \"discountPercent\": \"${discount_percent}\"
}"

echo curl \
  --silent \
  --request POST \
  --location ${location} \
  --header 'Content-Type: application/json' \
  --data ${rental_agreement}
