INSERT INTO account (id, login, password, balance, owner, create_account_date, account_status, role)
VALUES (UUID_TO_BIN(UUID()), 'passenger', 'p4553', 100.00, 'John Doe', NOW(), 'ACTIVE', 'PASSENGER'),
       (UUID_TO_BIN(UUID()), 'admin', 'a453423', 0.00, 'Ruslan Ruslan', NOW(), 'ACTIVE', 'ADMIN');

