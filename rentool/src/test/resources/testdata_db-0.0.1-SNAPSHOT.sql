#
# This file may be used to insert test data into the database [DML - Data Manipulation Language].
#

INSERT INTO user
    (id, name)
VALUES
    ('1233ba9c-8403-492e-9f4c-5b3b5464c3af', 'arielhenry'),
    ('04a7bf6a-f317-4eea-9311-fa4d711f860f', 'tiffanytimbric'),
    ('04a7bf6a-f317-4eea-9311-fa4d711f860d', 'chadwickboggs')
;

INSERT INTO brand
    (name)
VALUES
    ('Stihl'),
    ('Werner'),
    ('DeWalt'),
    ('Ridgid')
;

INSERT INTO tool_type
    (name)
VALUES
    ('Chainsaw'),
    ('Ladder'),
    ('Jackhammer')
;

INSERT INTO tool
    (id, code, type, brand, description, daily_charge, weekdays_free, weekends_free, holidays_free)
VALUES
    (
     '1233ba9c-8403-492e-9f4c-5b3b5464c3a1', 'CHNS', 'Chainsaw', 'Stihl',
     'MSA 220 - The lightweight and powerful MSA 220 C-B features up to 40 minutes of run time when paired with the AP 300 S Battery.',
     0.0, FALSE, FALSE, FALSE
    ),
    (
     '1233ba9c-8403-492e-9f4c-5b3b5464c3a2', 'LADW', 'Ladder', 'Werner',
     '6206 - With a duty rating of 300 lbs, it features a LOCKTOP® which provides maximum storage capacity for the most frequently used tools and components.',
     0.0, FALSE, FALSE, FALSE
    ),
    (
     '1233ba9c-8403-492e-9f4c-5b3b5464c3a3', 'JAKD', 'Jackhammer', 'DeWalt',
     'D25980 - 68 lb 1-1/8 inch Hex Pavement Breaker with Active Vibration Control.',
     0.0, FALSE, FALSE, FALSE
    ),
    (
     '1233ba9c-8403-492e-9f4c-5b3b5464c3a4', 'JAKR', 'Jackhammer', 'Ridgid',
     'R86712B - 18V Brushless 1 inch SDS-Plus Rotary Hammer',
     0.0, FALSE, FALSE, FALSE
    )
;

INSERT INTO rental_agrmnt
    (id, tool_id, renter_id, rental_days, checkout_date, daily_rental_charge, discount_percent, state)
VALUES
    (
     '5a12bfc4-c458-4244-9e5c-831fb782e7ba',
     '1233ba9c-8403-492e-9f4c-5b3b5464c3a1',
     '04a7bf6a-f317-4eea-9311-fa4d711f860f',
     2, '2025-03-15', '10.15', 0,
     'Proposed'
    )
;
