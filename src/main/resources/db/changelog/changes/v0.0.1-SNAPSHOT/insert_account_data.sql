INSERT INTO Account (id, login, password, balance, account_status)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'luca@web.de', '12345John', 800.0, 'ACTIVE'),
       (UNHEX(REPLACE(UUID(), '-', '')), 'daniel@list-manage.com', '2vs23Jones', 2000.0,'BLOCKED'),
       (UNHEX(REPLACE(UUID(), '-', '')), 'Morette@example.it', 'mor2424', 1000.0,'REMOVED');


