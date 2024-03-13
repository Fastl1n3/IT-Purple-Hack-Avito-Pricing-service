CREATE TABLE baseline_matrix
(
    microcategory_id INT,
    location_id      INT,
    price            INT NOT NULL,
	PRIMARY KEY (microcategory_id, location_id)
);

CREATE TABLE discount_matrix
(
    microcategory_id INT,
    location_id      INT,
    price            INT NOT NULL,
	PRIMARY KEY (microcategory_id, location_id)
);

CREATE TABLE BMatrices_info
(
    matrix_id   INT PRIMARY KEY,
    matrix_name varchar NOT NULL,
    segment     INT
);

CREATE TABLE DMatrices_info
(
    matrix_id   INT PRIMARY KEY,
    matrix_name varchar NOT NULL,
    segment     INT
);
