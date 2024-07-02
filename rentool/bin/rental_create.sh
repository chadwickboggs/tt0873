#!/usr/bin/env bash

USAGE="USAGE: \n\n
  $ $0 <Rental_Agreement_JSON_Data_Input_Filename> \n
"

if [[ $# -ne 1 || $1 = '-h' || $1 = '--help' || $1 = '-u' || $1 = '--usage' ]]; then
  echo -e "${USAGE}"
  exit 1
fi

source "$(dirname $0)"/config.sh
source "$(dirname $0)"/util.sh

post_data="$(cat $1)"
tool_id=$(jq '.toolId' $1)

curl \
  --silent \
  --fail-with-body \
  --request POST \
  --location "${comm_protocol}://${hostname}:${network_port}/validateRentalAgreement" \
  --header 'Content-Type: application/json' \
  --data "${post_data}"

exitIfError

if [[ -n ${tool_id} || ${tool_id} -eq "" ]]; then
  location="${comm_protocol}://${hostname}:${network_port}/rentalAgreementByName"
else
  location="${comm_protocol}://${hostname}:${network_port}/rentalAgreement"
fi

curl \
  --silent \
  --fail \
  --request POST \
  --location ${location} \
  --header 'Content-Type: application/json' \
  --data "${post_data}"

exitIfError
