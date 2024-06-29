#!/usr/bin/env bash

USAGE="USAGE: \n\n
  $ $0 <Rental_Agreement_JSON_Data_Input_Filename> \n
"

if [[ $# -ne 1 || $1 = '-h' || $1 = '--help' || $1 = '-u' || $1 = '--usage' ]]; then
  echo -e ${USAGE}
  exit 1
fi

comm_protocol='http'
hostname='localhost'
network_port='8080'
location="${comm_protocol}://${hostname}:${network_port}/rentalAgreement"

curl \
  --silent \
  --request POST \
  --location ${location} \
  --header 'Content-Type: application/json' \
  --data "$(cat $1)"
