INSERT INTO flights (id, flight_number, departure_airport, arrival_airport, departure_time, arrival_time, price)
VALUES (UUID_TO_BIN(UUID()), 'FL1234', 'Airport 1', 'Airport 2', '2024-01-01 09:00:00', '2024-01-01 12:00:00', 100.00),
       (UUID_TO_BIN(UUID()), 'FL5678', 'Airport 2', 'Airport 3', '2024-01-02 15:00:00', '2024-01-02 18:00:00', 150.00);
