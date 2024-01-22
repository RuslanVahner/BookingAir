INSERT INTO Cart (id, total_cost, account_id)
SELECT
    UNHEX(REPLACE(UUID(), '-', '')),
    150.00,
    (SELECT id FROM Account LIMIT 1);
