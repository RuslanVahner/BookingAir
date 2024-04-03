INSERT INTO bookings (id, user_id, flight_id, status, created_at)
VALUES (UUID_TO_BIN(UUID()), (SELECT id FROM users WHERE username = 'user1'),
        (SELECT id FROM flights WHERE flight_number = 'FL1234'), 'BOOKED', NOW());
