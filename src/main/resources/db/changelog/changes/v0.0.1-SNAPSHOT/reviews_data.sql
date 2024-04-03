INSERT INTO reviews (id, flight_id, user_id, rating, comment)
VALUES (UUID_TO_BIN(UUID()), (SELECT id FROM flights WHERE flight_number = 'FL1234'),
        (SELECT id FROM users WHERE username = 'user1'), 5, 'I really enjoyed your flight!');
