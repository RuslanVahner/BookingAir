INSERT INTO Trip (id, name_trips, flight_number, departure_time, arrival_time, flight_time, trips_status, airline_id)
SELECT
    UNHEX(REPLACE(UUID(), '-', '')),
    'New York',
    54321,
    '2024-03-15 14:30:00',
    '2024-03-15 20:30:00',
    '06:00:00',
    'TRANSFERRED',
    id
FROM
    Airline
        LIMIT 1;