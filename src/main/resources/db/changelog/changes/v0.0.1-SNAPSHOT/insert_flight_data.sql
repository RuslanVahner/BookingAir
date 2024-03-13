INSERT INTO flight (id, name_flight, flight_number, departure_airport, arrival_airport, flight_time, departure_date,
                    arrival_date, airline, flight_status)
VALUES (UUID_TO_BIN(UUID()), 'Paris', 101, 'JFK', 'LAX', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 5 HOUR),
        'Qatar Airways.', 'ACTIVE');