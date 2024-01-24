INSERT INTO Airport (id, name_airport, country, address, airline_id)
SELECT
    UNHEX(REPLACE(UUID(), '-', '')),
    'Heathrow Airport',
    'Great Britain',
    'Hounslow TW6 1QG',
     id
FROM
    Airline
        LIMIT 1;

