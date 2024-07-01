# Rentool

Rentool enables tool rental through REST Web Services and CLI Bash shell
scripts.  It persists its data in a MariaDB / MySql DB named "rentool".

Rental lifecycles follow defined states.  A Finite State Machine was
used to enforce them.  See `doc/rental_agreement-finite_state_machine.md`.

**Basic Success Flow:**
    Proposed -> Accepted -> PickedUp -> Returned

## Notes
- The hostname and network port number to access the Web Services may be
configured in the file named `bin/config.sh`.
- Its Web Services were implemented using Spring Boot version 3.3.1.
- The Java version required is 21.  Some prior versions may work.
- A Postmand collection may be found in `doc/rentool.postman_collection.json`.
- The Maven build system was used.

## Setup
### Create the Database
#### Install MariaDB / MySql
1. Find and install the most recent version of MariaDB on your platform.

#### Create the `rentool` Database
1. Set the MariaDB password for its `root` user to `password`.
1. Run the following SQL files:
   1. `conf/create_db-0.0.1-SNAPSHOT.sql`
   1. `conf/refdata_db-0.0.1-SNAPSHOT.sql`
   1. `src/test/resources/testdata_db-0.0.1-SNAPSHOT.sql`

### Compile and Start the Application
#### Compile and Install the Required Library Dependencies
Rentool use a SNAPSHOT Finite State Machine library.  It declares said Maven
dependency as `com.tiffanytimbric:fsm` version `1.0.0-SNAPSHOT`.  Before
building Rentool you must locally `mvn -e -U install` said FSM dependency.

    $ git clone https://github.com/chadwickboggs/fsm.git
    $ cd fsm
    $ git checkout -b develop
    $ mvn -e -U install

#### Compile and Start the Web Services
    $ git clone https://github.com/chadwickboggs/tt0873.git
    $ cd tt0873
    $ git checkout -b develop
    $ cd rentool
    $ mvn -e -U install
    $ mvn org.springframework.boot:spring-boot-maven-plugin:3.3.1:start

### Stop the Web Services
    $ mvn -e -U install
    $ mvn org.springframework.boot:spring-boot-maven-plugin:3.3.1:stop

## Usage Examples
### List Database Contents
    $ bin/list_users.sh | jq
    [
        {
            "id": "1233ba9c-8403-492e-9f4c-5b3b5464c3af",
            "name": "arielhenry"
        },
        {
            "id": "04a7bf6a-f317-4eea-9311-fa4d711f860d",
            "name": "chadwickboggs"
        },
        {
            "id": "04a7bf6a-f317-4eea-9311-fa4d711f860f",
            "name": "tiffanytimbric"
        }
    ]

    $ bin/list_brands.sh | jq
    [
        {
            "name": "DeWalt"
        },
        {
            "name": "Ridgid"
        },
        {
            "name": "Stihl"
        },
        {
            "name": "Werner"
        }
    ]

    $ bin/list_tool_types.sh | jq
    [
        {
            "name": "Chainsaw"
        },
        {
            "name": "Jackhammer"
        },
        {
            "name": "Ladder"
        }
    ]

    $ bin/list_tools.sh | jq
    [
        {
            "id": "1233ba9c-8403-492e-9f4c-5b3b5464c3a1",
            "code": "CHNS",
            "type": "Chainsaw",
            "brand": "Stihl",
            "description": "MSA 220 - The lightweight and powerful MSA 220 C-B features up to 40 minutes of run time when paired with the AP 300 S Battery.",
            "dailyCharge": 0.0,
            "weekdaysFree": false,
            "weekendsFree": false,
            "holidaysFree": false
        },
        {
            "id": "1233ba9c-8403-492e-9f4c-5b3b5464c3a2",
            "code": "LADW",
            "type": "Ladder",
            "brand": "Werner",
            "description": "6206 - With a duty rating of 300 lbs, it features a LOCKTOP® which provides maximum storage capacity for the most frequently used tools and components.",
            "dailyCharge": 0.0,
            "weekdaysFree": false,
            "weekendsFree": false,
            "holidaysFree": false
        },
        {
            "id": "1233ba9c-8403-492e-9f4c-5b3b5464c3a3",
            "code": "JAKD",
            "type": "Jackhammer",
            "brand": "DeWalt",
            "description": "D25980 - 68 lb 1-1/8 inch Hex Pavement Breaker with Active Vibration Control.",
            "dailyCharge": 0.0,
            "weekdaysFree": false,
            "weekendsFree": false,
            "holidaysFree": false
        },
        {
            "id": "1233ba9c-8403-492e-9f4c-5b3b5464c3a4",
            "code": "JAKR",
            "type": "Jackhammer",
            "brand": "Ridgid",
            "description": "R86712B - 18V Brushless 1 inch SDS-Plus Rotary Hammer",
            "dailyCharge": 0.0,
            "weekdaysFree": false,
            "weekendsFree": false,
            "holidaysFree": false
        }
    ]

    $ bin/list_holidays.sh | jq
    [
        {
            "dayDate": "2024-07-04",
            "name": "Independence Day"
        },
        {
            "dayDate": "2024-09-02",
            "name": "Labor Day"
        },
        {
            "dayDate": "2025-07-04",
            "name": "Independency Day"
        },
        {
            "dayDate": "2025-09-01",
            "name": "Labor Day"
        },
        {
            "dayDate": "2026-07-03",
            "name": "Independency Day"
        },
        {
            "dayDate": "2026-09-07",
            "name": "Labor Day"
        }
    ]

    $ bin/list_rental_agreements.sh | jq
    [
        {
            "id": "5a12bfc4-c458-4244-9e5c-831fb782e7ba",
            "toolId": "1233ba9c-8403-492e-9f4c-5b3b5464c3a1",
            "toolCode": null,
            "toolType": null,
            "renterId": "04a7bf6a-f317-4eea-9311-fa4d711f860f",
            "renterName": null,
            "rentalDays": 2,
            "checkoutDate": "03/15/25",
            "dailyRentalCharge": 10.15,
            "dailyRentalChargeCurrency": null,
            "discountPercent": 0,
            "state": "Proposed",
            "dataItem": null
        }
    ]

### Rent a Tool
    $ bin/rental_checkout.sh
    What is the Code of the tool you wish to rent? LADW
    What is your User Name? tiffanytimbric
    For how many days do you wish to rent this tool? 2
    On which date to you wish to pick up this rental (mm/dd/yy)? 08/01/24
    What is the daily rental charge for this tool? 15.27
    What is the discount percentage for this rental? 10
    
    Tool ID: 1233ba9c-8403-492e-9f4c-5b3b5464c3a2
    Tool code: LADW
    Tool type: Ladder
    Rental days: 2
    Pickup date: 08/01/24
    Return date: 08/03/24
    Daily rental charge: $15.27
    Pre-discount charge: $30.54
    Discount percent: 10%
    Final charge: $27.49

    $ bin/rental_accept.sh '2f415bfd-ad4f-4889-86d9-570af7e68067' '04a7bf6a-f317-4eea-9311-fa4d711f860f' | jq
    {
        "id": "2f415bfd-ad4f-4889-86d9-570af7e68067",
        "toolId": "1233ba9c-8403-492e-9f4c-5b3b5464c3a2",
        "renterId": "04a7bf6a-f317-4eea-9311-fa4d711f860f",
        "rentalDays": 2,
        "checkoutDate": "08/01/24",
        "dailyRentalCharge": 15.27,
        "discountPercent": 10,
        "state": "Accepted",
        "dataItem": null,
        "toolCode": "LADW",
        "toolType": "Ladder",
        "renterName": "tiffanytimbric",
        "dueDate": "08/03/24",
        "dailyRentalChargeCurrency": "$15.27",
        "chargeDays": 2,
        "preDiscountCharge": 30.54,
        "preDiscountChargeCurrency": "$30.54",
        "finalCharge": 27.486,
        "finalChargeCurrency": "$27.49"
    }

    $ bin/rental_pickup.sh '2f415bfd-ad4f-4889-86d9-570af7e68067' '04a7bf6a-f317-4eea-9311-fa4d711f860f' | jq
    {
        "id": "2f415bfd-ad4f-4889-86d9-570af7e68067",
        "toolId": "1233ba9c-8403-492e-9f4c-5b3b5464c3a2",
        "renterId": "04a7bf6a-f317-4eea-9311-fa4d711f860f",
        "rentalDays": 2,
        "checkoutDate": "08/01/24",
        "dailyRentalCharge": 15.27,
        "discountPercent": 10,
        "state": "PickedUp",
        "dataItem": null,
        "toolCode": "LADW",
        "toolType": "Ladder",
        "renterName": "tiffanytimbric",
        "dueDate": "08/03/24",
        "dailyRentalChargeCurrency": "$15.27",
        "chargeDays": 2,
        "preDiscountCharge": 30.54,
        "preDiscountChargeCurrency": "$30.54",
        "finalCharge": 27.486,
        "finalChargeCurrency": "$27.49"
    }

    $ bin/rental_return.sh '2f415bfd-ad4f-4889-86d9-570af7e68067' '04a7bf6a-f317-4eea-9311-fa4d711f860f' | jq
    {
        "id": "2f415bfd-ad4f-4889-86d9-570af7e68067",
        "toolId": "1233ba9c-8403-492e-9f4c-5b3b5464c3a2",
        "renterId": "04a7bf6a-f317-4eea-9311-fa4d711f860f",
        "rentalDays": 2,
        "checkoutDate": "08/01/24",
        "dailyRentalCharge": 15.27,
        "discountPercent": 10,
        "state": "Returned",
        "dataItem": null,
        "toolCode": "LADW",
        "toolType": "Ladder",
        "renterName": "tiffanytimbric",
        "dueDate": "08/03/24",
        "dailyRentalChargeCurrency": "$15.27",
        "chargeDays": 2,
        "preDiscountCharge": 30.54,
        "preDiscountChargeCurrency": "$30.54",
        "finalCharge": 27.486,
        "finalChargeCurrency": "$27.49"
    }
