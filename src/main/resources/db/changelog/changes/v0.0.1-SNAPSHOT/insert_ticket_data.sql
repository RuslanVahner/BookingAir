INSERT INTO Ticket (flight_number, passenger_id, seat_number, price)
VALUES
    (2345, LAST_INSERT_ID(), 'A123', 150.00),
    (9773, LAST_INSERT_ID(), 'B456', 200.00),
    (121, LAST_INSERT_ID(), 'C789', 180.00);
