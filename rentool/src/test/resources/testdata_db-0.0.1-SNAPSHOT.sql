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
(id, code, type, brand, daily_charge, weekdays_free, weekends_free, holidays_free)
VALUES
    ('1233ba9c-8403-492e-9f4c-5b3b5464c3a1', 'CHNS', 'Chainsaw', 'Stihl', 0.0, FALSE, FALSE, FALSE),
    ('1233ba9c-8403-492e-9f4c-5b3b5464c3a2', 'LADW', 'Ladder', 'Werner', 0.0, FALSE, FALSE, FALSE),
    ('1233ba9c-8403-492e-9f4c-5b3b5464c3a3', 'JAKD', 'Jackhammer', 'DeWalt', 0.0, FALSE, FALSE, FALSE),
    ('1233ba9c-8403-492e-9f4c-5b3b5464c3a4', 'JAKR', 'Jackhammer', 'Ridgid', 0.0, FALSE, FALSE, FALSE)
;
