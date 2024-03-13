CREATE TABLE baseline_matrix
(
    microcategory_id INT PRIMARY KEY,
    location_id      INT PRIMARY KEY,
    price            INT NOT NULL
);

CREATE TABLE discount_matrix
(
    microcategory_id INT PRIMARY KEY,
    location_id      INT PRIMARY KEY,
    price            INT NOT NULL
);

CREATE TABLE baseline_matrix_info
(
    matrix_id   INT PRIMARY KEY,
    matrix_name varchar NOT NULL,
    segment     INT
);

CREATE TABLE discount_matrix_info
(
    matrix_id   INT PRIMARY KEY,
    matrix_name varchar NOT NULL,
    segment     INT
);