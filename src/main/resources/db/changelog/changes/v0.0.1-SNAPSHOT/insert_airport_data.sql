INSERT INTO Airport (id, name_airport, country, address, airline_id, ticket_id)
SELECT
    UNHEX(REPLACE(UUID(), '-', '')),
    'Heathrow Airport',
    'Great Britain',
    'Hounslow TW6 1QG',
    (SELECT id FROM Airline LIMIT 1),
    (SELECT id FROM Ticket LIMIT 1);
