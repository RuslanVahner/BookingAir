INSERT INTO Trips (id, name_trips, number_trips, departure, arrival, flight_time)
VALUES ('4d1b8a2e-5406-11ee-8c99-0242ac120002', 'Paris', '2345', '11:17', '12:42', '1h 25m');

INSERT INTO Passenger (id, first_name, last_name, age, phone, email)
VALUES ('1763f054-5393-11ee-8c99-0242ac120002', 'Luca', 'Schmidt', 30, '+49-171-2138-637', 'luca@web.de');

INSERT INTO Cart (id, total_cost, is_refunded, owner_id)
VALUES ('2d0aef7d-5406-11ee-8c99-0242ac120002', 103.80, false, '1763f054-5393-11ee-8c99-0242ac120002');

INSERT INTO Account (id, login, password, balance, cart_id, passenger_id)
VALUES ('64925a86-5406-11ee-8c99-0242ac120002', 'luca@web.de', '12345John', 800.0, '2d0aef7d-5406-11ee-8c99-0242ac120002',
        '1763f054-5393-11ee-8c99-0242ac120002');

INSERT INTO Ticket (id, price, data, is_active, passenger_number, account_id, service, type)
VALUES ('55b1a5d8-5406-11ee-8c99-0242ac120002', 53.80, '2024-01-05', true, 33, '64925a86-5406-11ee-8c99-0242ac120002',
        'Economy', 'Adult');

INSERT INTO Airline (id, airlin_name, airline_price, trips_id)
VALUES ('3f8d48e6-5406-11ee-8c99-0242ac120002', 'Pegasus Airlines', 50.0, '4d1b8a2e-5406-11ee-8c99-0242ac120002');

INSERT INTO Airport (id, name_airport, country, address, airline_id, ticket_id)
VALUES ('50e525ec-5406-11ee-8c99-0242ac120002', 'Flughafen-Stuttgart', 'Germany', 'Flughafenstraße 32, 70629 Stuttgart',
        '3f8d48e6-5406-11ee-8c99-0242ac120002', '64925a86-5406-11ee-8c99-0242ac120002');