INSERT INTO users (id, username, password, email, role)
VALUES (UUID_TO_BIN(UUID()), 'user1', '$2a$10$0xC/AcUERUGmCzipH/L.VusFEcJEzlvL31eYJ8iLeoVQN0F4/CXqG', 'user1@example.com', 'USER'),
       (UUID_TO_BIN(UUID()), 'admin', '$2a$10$G6MEyLy0tvr9x588OHFMzuq.hJzN7b.DC9ibf4sAyVLGTYKIWDEgK', 'admin@example.com', 'ADMIN');
