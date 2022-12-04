INSERT INTO security.users(name, password) VALUES
('andrey', 'qwerty'),
('manager', 'qwerty');

INSERT INTO security.roles(role) VALUES
('guest'),
('manager');

INSERT INTO security.user_roles VALUES
(1, 1),
(2, 1),
(2, 2);