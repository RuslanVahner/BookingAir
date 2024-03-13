INSERT INTO ticket (id, price, purchase_time, ticket_number, flight_id, account_id, reservation_id, ticket_class, ticket_status, type)
VALUES (UUID_TO_BIN(UUID()), 299.99, NOW(), 123456,
        (SELECT id FROM flight WHERE name_flight = 'Paris'),
        (SELECT id FROM account WHERE owner = 'John Doe'),
        (SELECT id FROM reservations WHERE reservations_reference = 'REF123'),
        'ECONOMY', 'RESERVED', 'ADULT');
