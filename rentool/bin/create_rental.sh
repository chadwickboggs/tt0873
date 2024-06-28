#!/usr/bin/env bash

comm_protocol='http'
hostname='localhost'
network_port='8080'
location="${comm_protocol}://${hostname}:${network_port}/rentalAgreement"

curl \
  --silent \
  --request POST \
  --location ${location} \
  --header 'Content-Type: application/json' \
  --data ''
