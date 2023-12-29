INSERT INTO Trips (id, name_trips, number_trips, departure, arrival, flight_time)
VALUES ('4d1b8a2e-5406-11ee-8c99-0242ac120004', 'Stuttgart', 'FR 9773', '8:25', '10:50', '1h 25m');

INSERT INTO Passenger (id, first_name, last_name, age, phone, email)
VALUES ('1763f054-5393-11ee-8c99-0242ac120004', 'Daniel', 'Jones', 45, '+44-20-1234-5678', 'daniel@list-manage.com');

INSERT INTO Cart (id, total_cost, is_refunded, owner_id)
VALUES ('2d0aef7d-5406-11ee-8c99-0242ac120004', 194.76, false, '1763f054-5393-11ee-8c99-0242ac120004');

INSERT INTO Account (id, login, password, balance, cart_id, passenger_id)
VALUES ('64925a86-5406-11ee-8c99-0242ac120004', 'daniel@list-manage.com', '2vs23Jones', 2000.0,
        '2d0aef7d-5406-11ee-8c99-0242ac120004',
        '1763f054-5393-11ee-8c99-0242ac120004');

INSERT INTO Ticket (id, price, data, is_active, passenger_number, account_id, service, type)
VALUES ('55b1a5d8-5406-11ee-8c99-0242ac120004', 164.76, '2024-01-18', true, 65, '64925a86-5406-11ee-8c99-0242ac120004',
        'Business', 'Adult');

INSERT INTO Airline (id, airlin_name, airline_price, trips_id)
VALUES ('3f8d48e6-5406-11ee-8c99-0242ac120004', 'Ryanair', 30.0, '4d1b8a2e-5406-11ee-8c99-0242ac120004');

INSERT INTO Airport (id, name_airport, country, address, airline_id, ticket_id)
VALUES ('50e525ec-5406-11ee-8c99-0242ac120004', 'Heathrow Airport', 'Great Britain', 'Hounslow TW6 1QG',
        '3f8d48e6-5406-11ee-8c99-0242ac120004', '64925a86-5406-11ee-8c99-0242ac120004');
