#
# This file may be used to create the database and its tables [DDL - Data Definition Language].
#

CREATE DATABASE IF NOT EXISTS rentool;

USE rentool;

CREATE TABLE IF NOT EXISTS user
(
    id   UUID PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS brand
(
    name VARCHAR(64) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS tool_type
(
    name VARCHAR(64) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS tool
(
    id            UUID PRIMARY KEY,
    code          VARCHAR(64) NOT NULL,
    type          VARCHAR(64) NOT NULL,
    brand         VARCHAR(64) NOT NULL,
    daily_charge  FLOAT       NOT NULL,
    weekdays_free BOOL        NOT NULL,
    weekends_free BOOL        NOT NULL,
    holidays_free BOOL        NOT NULL,
    INDEX (code),
    FOREIGN KEY (type) REFERENCES tool_type (name),
    FOREIGN KEY (brand) REFERENCES brand (name)
);

CREATE TABLE IF NOT EXISTS rental_agrmnt
(
    id                  UUID PRIMARY KEY,
    tool_id             UUID                           NOT NULL,
    renter_id           UUID                           NOT NULL,
    rental_days         DECIMAL                        NOT NULL,
    checkout_date       DATE                           NOT NULL,
    daily_rental_charge FLOAT                          NOT NULL,
    discount_percent    INTEGER                        NOT NULL,
    state               VARCHAR(32) DEFAULT 'Proposed' NOT NULL,
    data_item           TEXT,
    FOREIGN KEY (tool_id) REFERENCES tool (id),
    FOREIGN KEY (renter_id) REFERENCES user (id)
);
