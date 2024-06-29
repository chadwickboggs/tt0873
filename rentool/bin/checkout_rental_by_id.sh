#!/usr/bin/env bash

USAGE="USAGE: \n\n
  $ $0 [Output_JSON_Filename] \n
"

if [[ $1 = '-h' || $1 = '--help' || $1 = '-u' || $1 = '--usage' ]]; then
  echo -e ${USAGE}
  exit 1
fi

if [[ $# -eq 1 ]]; then
  output_json_data_file=$1
else
  output_json_data_file=$(mktemp)
fi

input_json_data_file=$(mktemp)
"$(dirname $0)"/collect_data_for_rental_by_id.sh ${input_json_data_file}

"$(dirname $0)"/create_rental.sh ${input_json_data_file} > ${output_json_data_file}
rm ${input_json_data_file}

created_rental_agreement_id=$(jq '.id' ${output_json_data_file})
renter_id=$(jq '.renterId' ${output_json_data_file})

echo -n ${created_rental_agreement_id} ${renter_id} \
  | xargs "$(dirname $0)"/accept_rental.sh > ${output_json_data_file}

# TODO: Retrieve tool code instead of tool ID.
tool_id=$(jq '.toolId' ${output_json_data_file})
# TODO: Retrieve tool type.
# TODO: Calculate final charge.
# TODO: Calculate discount amount.
rental_days=$(jq '.rentalDays' ${output_json_data_file})
discount_percent=$(jq '.discountPercent' ${output_json_data_file})

[[ $# -eq 0 ]] && rm ${output_json_data_file}

echo
echo -e "Tool ID: ${tool_id}
Tool type: TODO
Rental days: ${rental_days}
Discount percent: ${discount_percent}
Final charge: TODO"
