INSERT INTO Trip (id, name_trips, flight_number, departure_time, arrival_time, flight_time, trips_status, airline_id)
SELECT
    UNHEX(REPLACE(UUID(), '-', '')),
    'Paris',
    12345,
    '2024-02-01 08:00:00',
    '2024-02-01 12:00:00',
    '04:00:00',
    'ACTIVE',
    id
FROM
    Airline
        LIMIT 1;