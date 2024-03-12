CREATE TABLE baseline_matrix
(
    microcategory_id SERIAL PRIMARY KEY,
    location_id      SERIAL PRIMARY KEY,
    matrix_id        SERIAL NOT NULL REFERENCES baseline_matrix_info (matrix_id),
    price            INT    NOT NULL
);

CREATE TABLE discount_matrix
(
    microcategory_id SERIAL PRIMARY KEY,
    location_id      SERIAL PRIMARY KEY,
    matrix_id        SERIAL NOT NULL REFERENCES discount_matrix_info (matrix_id),
    price            INT    NOT NULL
);

CREATE TABLE baseline_matrix_info
(
    matrix_id   SERIAL PRIMARY KEY,
    matrix_name varchar NOT NULL,
    segment     SERIAL
);

CREATE TABLE discount_matrix_info
(
    matrix_id   SERIAL PRIMARY KEY,
    matrix_name varchar NOT NULL,
    segment     SERIAL
);