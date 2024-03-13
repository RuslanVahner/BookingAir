INSERT INTO passenger (id, email, first_name, last_name, age, phone, account_id)
VALUES (UUID_TO_BIN(UUID()), 'Doe@gmail.com', 'John', 'Doe', 30, '1234567890',
        (SELECT id FROM account WHERE owner = 'John Doe' LIMIT 1) );
