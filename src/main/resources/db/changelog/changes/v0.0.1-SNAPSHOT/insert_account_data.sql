INSERT INTO account (id, login, password, balance, owner, create_account_date, account_status, role)
VALUES (UUID_TO_BIN(UUID()), 'user', 'user123', 100.00, 'John Doe', NOW(), 'ACTIVE', 'PASSENGER'),
       (UUID_TO_BIN(UUID()), 'admin', 'YWRtaW4xNDAz', 0.00, 'Ruslan Ruslan', NOW(), 'ACTIVE', 'ADMIN');

