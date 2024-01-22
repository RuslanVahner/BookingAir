INSERT INTO Account (id, login, password, balance, account_status, passenger_id)
SELECT
    UNHEX(REPLACE(UUID(), '-', '')),
    'luca@web.de',
    '12345John',
    800.0,
    'ACTIVE',
    (SELECT id FROM Passenger LIMIT 1);

