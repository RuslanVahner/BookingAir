INSERT INTO Ticket (id, data, ticket_number, service, type, price, account_id, trips_id, airport_id)
SELECT UNHEX(REPLACE(UUID(), '-', '')),
       '2024-02-01 08:00:00',
       234,
       'ECONOMY',
       'ADULT',
       100.00,
       (SELECT id FROM Account LIMIT 1),(SELECT id FROM Trips LIMIT 1),(SELECT id FROM Airport LIMIT 1);


