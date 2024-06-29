#!/usr/bin/env bash

USAGE="USAGE: \n\n
  $ $0 [Output_Filename] \n
"

if [[ $1 = '-h' || $1 = '--help' || $1 = '-u' || $1 = '--usage' ]]; then
  echo -e "${USAGE}"
  exit 1
fi

echo -n "What is the Code of the tool you wish to rent? "
read -r tool_code

echo -n "What is your User Name? "
read -r user_name

echo -n "For how many days do you wish to rent this tool? "
read -r rental_days

echo -n "On which date to you wish to pick up this rental (yyyy-mm-dd)? "
read -r checkout_date

echo -n "What is the daily rental charge for this tool? "
read -r daily_rental_charge

echo -n "What is the discount percentage for this rental? "
read -r discount_percent

rental_agreement="{
  \"toolCode\": \"${tool_code}\",
  \"renterName\": \"${user_name}\",
  \"rentalDays\": ${rental_days},
  \"checkoutDate\": \"${checkout_date}\",
  \"dailyRentalCharge\": \"${daily_rental_charge}\",
  \"discountPercent\": ${discount_percent}
}"

if [[ $# -gt 0 ]]; then
  echo -e "${rental_agreement}" > "$1"
  exit
fi

echo -e "${rental_agreement}"
