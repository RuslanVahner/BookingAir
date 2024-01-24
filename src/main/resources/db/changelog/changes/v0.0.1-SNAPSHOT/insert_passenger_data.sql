INSERT INTO Passenger (id, first_name, last_name, age, email, phone, account_id)
SELECT
    UNHEX(REPLACE(UUID(), '-', '')), 'Uwe', 'Fisher', 25, 'Jone@yahoo.com', '+4915188569', id FROM Account LIMIT 1;
