INSERT INTO Airline (id, airline_name, airline_price, trips_id)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Pegasus Airlines', 50.00, (SELECT id FROM Trips WHERE name_trips = 'Rome')),
       (UNHEX(REPLACE(UUID(), '-', '')), 'SkyUp', 30.00, (SELECT id FROM Trips WHERE name_trips = 'Poland'));
