#!/usr/bin/env bash

USAGE="USAGE: \n\n
  $ $0 <Rental_Agreement_ID> <User_Name> \n
"

if [[ $# -ne 2 || $1 = '-h' || $1 = '--help' || $1 = '-u' || $1 = '--usage' ]]; then
  echo -e ${USAGE}
  exit 1
fi

comm_protocol='http'
hostname='localhost'
network_port='8080'
location="${comm_protocol}://${hostname}:${network_port}/pickUpRentalAgreement/$1/$2"

curl \
  --silent \
  --request GET \
  --location ${location} \
  --header 'Content-Type: application/json'