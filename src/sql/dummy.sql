INSERT INTO User (username, email, password) VALUES
('Joseja',  'joseja@luettelo.com',  'JJ'),
('Lucia',   'lucia@luettelo.com',   'LU'),
('Dumbo',   'dumbo@luettelo.com',   'DB');

INSERT INTO List (name, category, description, username) VALUES
('Best Fantasy Books',   'Books',    'Best Recent Books',    'Joseja'    ),
('Best Sci-Fi',          'Movies',   'Best RecentSF Movies', 'Joseja'    ),
('Best Anime Series',    'Series',   'Best Anime Series',    'Lucia'     );

INSERT INTO Item (name, url, listId) VALUES
('Name of The Wind',        'a',   1    ),
('A Storm Of Swords',       'a',   1    ),
('The Way Of The Kings',    'a',   1    ),
('Star Wars',               'a',   2    ),
('Star Trek',               'a',   2    ),
('Primer',                  'a',   2    ),
('Inception',               'a',   2    ),
('Death Note',              'a',   3    ),
('Full Metal Alchemist',    'a',   3    ),
('Cowboy Bebop',            'a',   3    ),
('Steins;Gate',             'a',   3    ),
('Sword Art Online',        'a',   3    );


INSERT INTO Subscription (username, listId) VALUES
('Joseja',      1),
('Joseja',      2),
('Joseja',      3),
('Lucia',       1),
('Lucia',       3),
('Dumbo',       1),
('Dumbo',       2),
('Dumbo',       3);

INSERT INTO Rating(value, username, itemId) VALUES
(5,     'Joseja',   1   ),
(5,     'Joseja',   2   ),
(5,     'Joseja',   3   ),
(5,     'Joseja',   4   ),
(3,     'Joseja',   5   ),
(4,     'Joseja',   6   ),
(5,     'Joseja',   7   ),
(4,     'Joseja',   8   ),
(4,     'Joseja',   9   ),
(5,     'Joseja',   10  ),
(4,     'Joseja',   11  ),
(3,     'Joseja',   12  ),
(5,     'Lucia',    1   ),
(0,     'Lucia',    3   ),
(5,     'Lucia',    8   ),
(0,     'Lucia',    9   ),
(5,     'Lucia',    10  ),
(3,     'Lucia',    11  ),
(3,     'Lucia',    12  );

INSERT INTO Comment(content,username,listId) VALUES
('This list is tor','Dumbo',1),
('This list is top*','Dumbo',1),
('@Dumbo, you can edit your comment','Joseja',1),
('I will add the remaining animes ASAP','Lucia',3);