INSERT INTO Trips (id, name_trips, flight_number, departure_time, arrival_time, flight_time, trips_status)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Paris', 12345, '2024-02-01 08:00:00', '2024-02-01 12:00:00', '04:00:00',
        'ACTIVE'),
       (UNHEX(REPLACE(UUID(), '-', '')), 'New York', 54321, '2024-03-15 14:30:00', '2024-03-15 20:30:00', '06:00:00',
        'TRANSFERRED'),
       (UNHEX(REPLACE(UUID(), '-', '')), 'Rome', 67890, '2024-05-10 10:45:00', '2024-05-10 16:45:00', '06:00:00',
        'CANCELLED');
