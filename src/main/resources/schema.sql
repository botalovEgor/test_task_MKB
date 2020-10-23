CREATE TABLE CAR (
  id IDENTITY PRIMARY KEY,
  brand VARCHAR2(150),
  model VARCHAR2(200),
  power DOUBLE,
  year_of_issue YEAR
);

CREATE TABLE AIRPLANE (
  id IDENTITY PRIMARY KEY,
  brand VARCHAR2(150),
  model VARCHAR2(200),
  manufacturer VARCHAR2(500),
  year_of_issue YEAR,
  fuel_capacity INT,
  seats INT
);

CREATE TABLE ASSESSED_VALUE(
  id IDENTITY primary key,
  value DEC(20),
  evaluation_date DATE
);

CREATE TABLE CAR_ASSESSED_VALUES(
    car_id integer REFERENCES CAR (id) ON DELETE CASCADE,
    assessed_value_id INTEGER REFERENCES ASSESSED_VALUE (Id) ON DELETE CASCADE,
    CONSTRAINT car_assessed_value UNIQUE (car_id, assessed_value_id)
);

CREATE TABLE AIRPLANE_ASSESSED_VALUES(
    airplane_id integer REFERENCES AIRPLANE (id) ON DELETE CASCADE,
    assessed_value_id INTEGER REFERENCES ASSESSED_VALUE (Id) ON DELETE CASCADE,
    CONSTRAINT airplane_assessed_value UNIQUE (airplane_id, assessed_value_id)
);