CREATE TABLE CAR (
  id IDENTITY PRIMARY KEY,
  brand VARCHAR2(150),
  model VARCHAR2(200),
  power DOUBLE,
  year_of_issue YEAR,
  assessed_value DEC(20)
);

CREATE TABLE AIRPLANE (
  id IDENTITY PRIMARY KEY,
  brand VARCHAR2(150),
  model VARCHAR2(200),
  manufacturer VARCHAR2(500),
  year_of_issue YEAR,
  fuelCapacity INT,
  seats INT
);

CREATE TABLE ASSESSED_VALUE(
  id IDENTITY primary key,
  value DEC(20),
  evaluation_date DATE
);

CREATE TABLE CAR_ASSESSED_VALUE(
    car_id integer REFERENCES CAR (id) ON DELETE CASCADE,
    assessed_value_id INTEGER REFERENCES ASSESSED_VALUE (Id) ON DELETE CASCADE,
    CONSTRAINT UNIQUE (car_id, assessed_value_id)
)

CREATE TABLE AIRPLANE_ASSESSED_VALUE(
    airplane_id integer REFERENCES AIRPLANE (id) ON DELETE CASCADE,
    assessed_value_id INTEGER REFERENCES ASSESSED_VALUE (Id) ON DELETE CASCADE,
    CONSTRAINT UNIQUE (airplane_id, assessed_value_id)
)