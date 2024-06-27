CREATE DATABASE IF NOT EXISTS rentool;

CREATE TABLE IF NOT EXISTS user
(
    id        UUID PRIMARY KEY,
    name      VARCHAR(64) NOT NULL UNIQUE,
    photo_url VARCHAR(128),
    balance   INT         NOT NULL DEFAULT 0
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
    id    UUID PRIMARY KEY,
    code  VARCHAR(64) NOT NULL,
    type  VARCHAR(64) NOT NULL,
    brand VARCHAR(64) NOT NULL,
    INDEX (code),
    FOREIGN KEY (type) REFERENCES tool_type (name),
    FOREIGN KEY (brand) REFERENCES brand (name)
);

CREATE TABLE IF NOT EXISTS tool_brand_xref
(
    brand_name VARCHAR(64) NOT NULL,
    tool_id    UUID        NOT NULL,
    PRIMARY KEY (brand_name, tool_id),
    FOREIGN KEY (brand_name) REFERENCES brand (name),
    FOREIGN KEY (tool_id) REFERENCES tool (id)
);

CREATE TABLE IF NOT EXISTS want_brand_xref
(
    brand_name VARCHAR(64) NOT NULL,
    want_id    UUID        NOT NULL,
    PRIMARY KEY (brand_name, want_id),
    FOREIGN KEY (brand_name) REFERENCES brand (name),
    FOREIGN KEY (want_id) REFERENCES tool (id)
);

CREATE TABLE IF NOT EXISTS rental_agrmnt
(
    id      UUID PRIMARY KEY,
    tool_id UUID NOT NULL,
    FOREIGN KEY (tool_id) REFERENCES tool (id)
);
