INSERT INTO factory(name, address) VALUES
('1_factory', 'London'),
('2_factory', 'Moscow');

INSERT INTO product(name, factory_name, count) VALUES
('1_toy', '1_factory', 20),
('2_toy', '1_factory', 15),
('3_toy', '2_factory', 10),
('4_toy', '2_factory', 25);