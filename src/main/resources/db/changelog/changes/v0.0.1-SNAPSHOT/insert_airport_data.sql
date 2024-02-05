INSERT INTO Airport (id, name_airport, country, address, airline_id)
VALUES
    (UNHEX(REPLACE(UUID(), '-', '')),'Heathrow Airport','Great Britain','Hounslow TW6 1QG',
    (SELECT id FROM Airline WHERE airline_name = 'Pegasus Airlines'));

