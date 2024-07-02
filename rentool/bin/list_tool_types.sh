#!/usr/bin/env bash

USAGE="USAGE: \n\n
  $ $0 [Tool_Type_Name] \n
"

if [[ $1 = '-h' || $1 = '--help' || $1 = '-u' || $1 = '--usage' ]]; then
  echo -e "${USAGE}"
  exit 1
fi

source "$(dirname $0)"/config.sh
source "$(dirname $0)"/util.sh

location="${comm_protocol}://${hostname}:${network_port}/toolType"

[[ $# -gt 0 ]] && location=${location}/$1

curl \
  --silent \
  --fail \
  --request GET \
  --location "${location}" \
  --header 'Content-Type: application/json'

exitIfError
