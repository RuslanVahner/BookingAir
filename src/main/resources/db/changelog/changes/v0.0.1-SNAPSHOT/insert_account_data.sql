INSERT INTO Account (id, login, password, owner, balance, account_status)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'lukas@yahoo.com', '12345John', 'Luca Lukas', 800.0, 'ACTIVE'),
       (UNHEX(REPLACE(UUID(), '-', '')), 'jone@yahoo.com', '2vs23Jones', 'Uwe Fisher', 2000.0, 'BLOCKED'),
       (UNHEX(REPLACE(UUID(), '-', '')), 'jane@gamail.com', 'mor2424', 'Jane Kin', 1000.0, 'ACTIVE');


