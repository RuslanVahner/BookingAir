INSERT INTO Cart (id, total_cost, account_id)
VALUES
    (UNHEX(REPLACE(UUID(), '-', '')),150.00, (SELECT id FROM Account WHERE owner = 'Luca Lukas')),
    (UNHEX(REPLACE(UUID(), '-', '')),170.00, (SELECT id FROM Account WHERE owner = 'Jane Kin'));
