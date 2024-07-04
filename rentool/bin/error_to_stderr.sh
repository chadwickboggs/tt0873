#!/usr/bin/env bash

USAGE="USAGE: \n\n
  This command outputs the contents of the specified input file either to
  stdout or to stderr based on the provided exit code of the prior command,
  0/success for stdout, otherwise stderr.

  $ $0 <Response_Code> <Input_Filename> \n
"

if [[ $# -ne 2 || $1 = '-h' || $1 = '--help' || $1 = '-u' || $1 = '--usage' ]]; then
  echo -e "${USAGE}"
  exit 1
fi

prior_command_exit_code=$1
input_filename=$2

if [[ ${prior_command_exit_code} -eq 0 ]]; then
  cat "${input_filename}"
else
  >&2 cat "${input_filename}"
fi

rm "${input_filename}"

exit ${prior_command_exit_code}
