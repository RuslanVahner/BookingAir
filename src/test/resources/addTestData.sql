INSERT INTO Account (id, login, password, balance, create_account_date, owner, account_status, role)
VALUES ('269c27bf-dfaa-1177-873a-00155dc38cbe', 'admin', 'adminPass1', 100.00, '2023-03-10 10:53:50',
        'Owner Name 1', 'ACTIVE', 'ADMIN'),
       ('269c27bf-df07-11ee-873a-00155dc35abc', 'user2', 'pass2', 200.00, '2024-03-10 09:45:10',
        'Owner Name 2', 'ACTIVE', 'PASSENGER');

INSERT INTO Cart (id, account_id)
VALUES ('269e0dbb-df07-11ee-873a-00155dc38cbe', '269c27bf-df07-11ee-873a-00155dc35abc');

INSERT INTO Passenger (id, first_name, last_name, age, phone, email, account_id)
VALUES ('555ff51f-df07-11ee-873a-00155dc38cbe', 'Jane', 'Doe', 25, '+0987654321', 'jane.doe@example.com',
        '269c27bf-df07-11ee-873a-00155dc35abc');

INSERT INTO Flight (id, name_flight, flight_number, departure_airport, arrival_airport, departure_date,
                    arrival_date, flight_time, airline, trips_status)
VALUES ('26a3c800-df07-11ee-873a-11155dc38cbe', 'Flight 1', 1001, 'Airport A', 'Airport B',
        '2024-03-15 08:00:00','2024-03-15 10:00:00', '02:00:00', 'Airline 1', 'ACTIVE');

INSERT INTO Reservations (id, reservations_reference, reservations_date)
VALUES ('77a7760d-df07-11ee-873a-00155dc38cbe', 'REF123', '2024-03-10 18:53:50');

INSERT INTO Ticket (id, price, purchase_time, ticket_number, account_id, ticket_class, type, ticket_status,
                    flight_id, reservation_id, passenger_id, cart_id)
VALUES ('88a888fe-df07-11ee-873a-00155dc38cbe', 150.00, '2024-03-05 15:53:50', 123456,
        '269c27bf-df07-11ee-873a-00155dc35abc', 'ECONOMY', 'ADULT', 'RESERVED',
        '26a3c800-df07-11ee-873a-11155dc38cbe', '77a7760d-df07-11ee-873a-00155dc38cbe',
        '555ff51f-df07-11ee-873a-00155dc38cbe', '269e0dbb-df07-11ee-873a-00155dc38cbe');
