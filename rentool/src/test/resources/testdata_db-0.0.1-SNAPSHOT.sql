#
# This file may be used to insert test data into the database [DML - Data Manipulation Language].
#

INSERT INTO user
(id, name, photo_url, balance)
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
(id, code, type, brand)
VALUES
    ('1233ba9c-8403-492e-9f4c-5b3b5464c3a1', 'CHNS', 'Chainsaw', 'Stihl'),
    ('1233ba9c-8403-492e-9f4c-5b3b5464c3a2', 'LADW', 'Ladder', 'Werner'),
    ('1233ba9c-8403-492e-9f4c-5b3b5464c3a3', 'JAKD', 'Jackhammer', 'DeWalt'),
    ('1233ba9c-8403-492e-9f4c-5b3b5464c3a4', 'JAKR', 'Jackhammer', 'Ridgid')
;

INSERT INTO tool_brand_xref
    (brand_name, tool_id)
VALUES
    ('Stihl', '1233ba9c-8403-492e-9f4c-5b3b5464c3a1'),
    ('Werner', '1233ba9c-8403-492e-9f4c-5b3b5464c3a2'),
    ('DeWalt', '1233ba9c-8403-492e-9f4c-5b3b5464c3a3'),
    ('Ridgid', '1233ba9c-8403-492e-9f4c-5b3b5464c3a4')
;
