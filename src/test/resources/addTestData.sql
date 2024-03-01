INSERT INTO Account (id, login, password, balance, account_status, owner)
VALUES (UUID(), 'testLogin', 'testPassword', 100.00, 'ACTIVE', 'testOwner');

INSERT INTO Passenger (id, first_name, last_name, age, phone, email, account_id)
VALUES (UUID(), 'John', 'Doe', 25, '+123456789', 'john.doe@example.com', UUID());

INSERT INTO Cart (id, total_cost, account_id)
VALUES (UUID(), 150.00, UUID());

INSERT INTO Trips (id, name_trips, flight_number, departure_time, arrival_time, flight_time, trips_status)
VALUES (UUID(), 'Paris', 12345, '2024-02-01 08:00:00', '2024-02-01 12:00:00', '04:00:00',
        'ACTIVE');

INSERT INTO Airline (id, airline_name, airline_price, trips_id)
VALUES (UUID(), 'Pegasus Airlines', 50.00, UUID());

INSERT INTO Airport (id, name_airport, country, address, airline_id)
VALUES (UUID(), 'Heathrow Airport', 'Great Britain', 'Hounslow TW6 1QG', 'Pegasus Airlines');

INSERT INTO Ticket (id, data, ticket_number, service, type, price, account_id, trips_id, airport_id)
VALUES (UUID(), '2024-02-01 08:00:00', 234, 'ECONOMY', 'ADULT', 100.00, UUID(), UUID(), UUID());