#!/usr/bin/env bash

function exitIfError () {
  if [[ $? -ne 0 ]]; then
    errorMessage='Your operation was unable to be completed successfully.'
    [[ $# -gt 0 ]] && errorMessage=$1

    >&2 echo "${errorMessage}"

    exit 1
  fi
}
