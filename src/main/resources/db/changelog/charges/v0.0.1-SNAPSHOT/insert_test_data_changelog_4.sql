INSERT INTO Trips (id, name_trips, number_trips, departure, arrival, flight_time)
VALUES ('4d1b8a2e-5406-11ee-8c99-0242ac120005', 'Amsterdam', 'AZ121', '09:05', '11:00', '1h 55m');

INSERT INTO Passenger (id, first_name, last_name, age, phone, email)
VALUES ('1763f054-5393-11ee-8c99-0242ac120005', 'Francesca', 'Morette', 25, '+39 02 1234 5678', 'Morette@example.it');

INSERT INTO Cart (id, total_cost, is_refunded, owner_id)
VALUES ('2d0aef7d-5406-11ee-8c99-0242ac120005', 340, false, '1763f054-5393-11ee-8c99-0242ac120005');

INSERT INTO Account (id, login, password, balance, cart_id, passenger_id)
VALUES ('64925a86-5406-11ee-8c99-0242ac120005', 'Morette@example.it', 'morettfarach2424', 1000.0,
        '2d0aef7d-5406-11ee-8c99-0242ac120005',
        '1763f054-5393-11ee-8c99-0242ac120005');

INSERT INTO Ticket (id, price, data, is_active, passenger_number, account_id, service, type)
VALUES ('55b1a5d8-5406-11ee-8c99-0242ac120005', 300, '2024-01-10', true, 20, '64925a86-5406-11ee-8c99-0242ac120005',
        'Business', 'Adult');

INSERT INTO Airline (id, airlin_name, airline_price, trips_id)
VALUES ('3f8d48e6-5406-11ee-8c99-0242ac120005', 'Alitalia', 40.0, '4d1b8a2e-5406-11ee-8c99-0242ac120005');

INSERT INTO Airport (id, name_airport, country, address, airline_id, ticket_id)
VALUES ('50e525ec-5406-11ee-8c99-0242ac120005', 'Milan Linate ', 'Italy', 'Viale Enrico Forlanini, 20054 Segrate MI,',
        '3f8d48e6-5406-11ee-8c99-0242ac120005', '64925a86-5406-11ee-8c99-0242ac120005');

