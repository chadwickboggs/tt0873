#!/usr/bin/env bash

USAGE="USAGE: \n\n
  $ $0 [Output_Filename] \n
"

if [[ $1 = '-h' || $1 = '--help' || $1 = '-u' || $1 = '--usage' ]]; then
  echo -e ${USAGE}
  exit 1
fi

comm_protocol='http'
hostname='localhost'
network_port='8080'
location="${comm_protocol}://${hostname}:${network_port}/rentalAgreement"

echo -n "What is the ID of the tool you wish to rent? "
read tool_id

echo -n "What is your User ID? "
read user_id

echo -n "For how many days do you wish to rent this tool? "
read rental_days

echo -n "On which date to you wish to pick up this rental (yyyy-mm-dd)? "
read checkout_date

echo -n "What is the daily rental charge for this tool? "
read daily_rental_charge

echo -n "What is the discount percentage for this rental? "
read discount_percent

rental_agreement="{
  \"toolId\":\"${tool_id}\",
  \"renterId\":\"${user_id}\",
  \"rentalDays\": ${rental_days},
  \"checkoutDate\": \"${checkout_date}\",
  \"dailyRentalCharge\": \"${daily_rental_charge}\",
  \"discountPercent\": \"${discount_percent}\"
}"

if [[ $# -gt 0 ]]; then
  echo "${rental_agreement}" > $1
  exit
fi

echo "${rental_agreement}"
