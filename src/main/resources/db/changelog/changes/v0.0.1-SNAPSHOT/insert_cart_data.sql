INSERT INTO cart (id, account_id)
VALUES (UUID_TO_BIN(UUID()), (SELECT id FROM account WHERE login = 'passenger' LIMIT 1));
