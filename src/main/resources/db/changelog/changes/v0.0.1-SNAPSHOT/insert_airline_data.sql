INSERT INTO Airline (id, airline_name, airline_price)
VALUES (UNHEX(REPLACE(UUID(), '-', '')),'Pegasus Airlines', 50.00),
       (UNHEX(REPLACE(UUID(), '-', '')),'Ryanair', 50.00),
       (UNHEX(REPLACE(UUID(), '-', '')),'Alitalia', 50.00);