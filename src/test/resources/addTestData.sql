INSERT INTO users (id, username, password, email, role)
VALUES (UUID_TO_BIN(UUID()), 'user2', 'password1', 'user2@example.com', 'USER'),
       (UUID_TO_BIN(UUID()), 'admin1', 'password2', 'admin1@example.com', 'ADMIN');

INSERT INTO flights (id, flight_number, departure_city, arrival_city, departure_time, arrival_time, status)
VALUES (UUID_TO_BIN(UUID()), 'FL123', 'City A', 'City B', '2024-04-10 10:00:00', '2024-04-10 12:00:00', 'ACTIVE'),
       (UUID_TO_BIN(UUID()), 'FL456', 'City C', 'City D', '2024-04-11 15:00:00', '2024-04-11 17:00:00', 'CANCELLED');


INSERT INTO bookings (id, user_id, flight_id, status, created_at)
VALUES (UUID_TO_BIN(UUID()), (SELECT id FROM users WHERE username = 'user2'),
        (SELECT id FROM flights WHERE flight_number = 'FL123'), 'BOOKED', NOW());

INSERT INTO reviews (id, flight_id, user_id, rating, comment)
VALUES (UUID_TO_BIN(UUID()), (SELECT id FROM flights WHERE flight_number = 'FL123'),
        (SELECT id FROM users WHERE username = 'user2'), 5, 'I really enjoyed your flight!');
