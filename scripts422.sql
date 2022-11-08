CREATE TABLE cars
(
    id_car INTEGER PRIMARY KEY,
    brand  TEXT NOT NULL,
    model  TEXT NOT NULL,
    cost   REAL CHECK ( cost > 0 )
);

CREATE TABLE men
(
    id      INTEGER PRIMARY KEY,
    name    TEXT NOT NULL,
    age     INTEGER CHECK ( age > 0 ),
    license BOOLEAN,
    -- колонка id_car связывает таблицы людей и машин -------------------
    id_car  INTEGER REFERENCES cars (id_car)
);



