INSERT INTO Airline (id, airline_name, airline_price, trips_id)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Pegasus Airlines', 50.00, (SELECT id FROM Trips LIMIT 1));
