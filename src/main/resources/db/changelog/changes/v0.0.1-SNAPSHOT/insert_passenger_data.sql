INSERT INTO Passenger (id, first_name, last_name, age, email, phone, account_id)
VALUES
    (UNHEX(REPLACE(UUID(), '-', '')), 'Uwe', 'Fisher', 25, 'jone@yahoo.com', '+4915188569',
    (SELECT id FROM Account WHERE owner = 'Uwe Fisher')),
    (UNHEX(REPLACE(UUID(), '-', '')), 'Luca', 'Lukas', 34, 'lukas@yahoo.com', '+4915188569',
    (SELECT id FROM Account WHERE owner = 'Luca Lukas')),
    (UNHEX(REPLACE(UUID(), '-', '')), 'Jane', 'Kin', 45, 'jane@gamail.com', '+81345789087',
    (SELECT id FROM Account WHERE owner = 'Jane Kin'));
