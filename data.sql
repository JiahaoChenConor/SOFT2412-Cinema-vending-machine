USE CINEMA;

INSERT INTO Film VALUES
(1, 'ACADEMY DINOSAUR', 'PG', 'A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies', '2021-1-1', 'Alan'),
(2, 'ACE GOLDFINGER', 'PG', 'A Astounding Epistle of a Database Administrator And a Explorer who must Find a Car in Ancient China', '2021-1-1', 'Daniel'),
(3, 'ADAPTATION HOLES', 'PG+', 'A Astounding Reflection of a Lumberjack And a Car who must Sink a Lumberjack in A Baloon Factory', '2021-1-1', 'Tom');

INSERT INTO Casts VALUES
(1, 'Jack', 1),
(2, 'Julia', 1),
(3, 'Brad Pit', 1),
(4, 'George', 2),
(5, 'Robert', 3);

INSERT INTO Cinema_Film VALUES
(1, 1, 1, '2021-12-1 16:00:00', 'Gold', 29.9, 15, 12),
(2, 1, 1, '2021-12-2 15:00:00', 'Silver', 29.9, 12, 15),
(3, 2, 2, '2021-12-2 18:00:00', 'Silver', 29.9, 12, 10);

INSERT INTO Ticket VALUES
(1, 1, 'child', 1, 1, 'purchased', 'ZhiQian'),
(2, 2, 'adult', 2, 3, 'purchased', 'ZhiQian'),
(3, 3, 'student', 4, 4, 'purchased', 'ZhiQian');

INSERT INTO Cinema VALUES
(1, 'TownHall'),
(2, 'Burwood');

INSERT INTO User VALUES
('Conor', '123456abc', 'CUSTOMER'),
('ZhiQian', 'abcdefg123', 'CUSTOMER'),
('Qihan', 'caiqihan123', 'MANAGER');

INSERT INTO GiftCard VALUES
('11111111111111GC', '5432', 1000.00, false),
('22222222222222GC', '8765', 500.00, true);


