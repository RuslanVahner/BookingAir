INSERT INTO Ticket (id, data, ticket_number, service, type, price, account_id, trips_id, airport_id)
VALUES
    (UNHEX(REPLACE(UUID(), '-', '')),'2024-02-01 08:00:00', 234,'ECONOMY','ADULT',100.00,
    (SELECT id FROM Account WHERE owner = 'Luca Lukas'),(SELECT id FROM Trips WHERE name_trips = 'Paris'),
    (SELECT id FROM Airport WHERE name_airport = 'Charles de Gaulle')),
    (UNHEX(REPLACE(UUID(), '-', '')),'2024-03-11 08:00:00', 2244,'BUSINESS','ADULT',150.00,
     (SELECT id FROM Account WHERE owner = 'Jane Kin'),(SELECT id FROM Trips WHERE name_trips = 'Poland'),
     (SELECT id FROM Airport WHERE name_airport = 'Fryderyk Chopin airport'));



