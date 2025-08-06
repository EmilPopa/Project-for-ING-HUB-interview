INSERT INTO category(id, name) VALUES (1, 'Electronics');
INSERT INTO category(id, name) VALUES (2, 'Books');
INSERT INTO category(id, name) VALUES (3, 'Clothing');

INSERT INTO users (email, name, password, username) VALUES
('emil@gmail.com', 'emil', '$2a$10$4kCYMgwxYdcM0NFNx3rR7OWV.gpIWkr4S2t6aJucufjbpPPL9NRxO', 'emil'),
('admin@gmail.com', 'admin', '$2a$10$NAx5zqOjBzfofVkl7iBTHekiiuHXCnRF5h7r0P9K.ZSjTtde/ajIe', 'admin');

INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);


