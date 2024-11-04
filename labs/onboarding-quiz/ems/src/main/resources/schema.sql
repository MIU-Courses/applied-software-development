CREATE TABLE IF NOT EXISTS employee (
    id BIGINT NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    salary NUMERIC(10, 2),
    PRIMARY KEY (id)
);