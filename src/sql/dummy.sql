INSERT INTO User (username, email, password) VALUES
('joseja',  'joseja@luettelo.com',  '$2a$12$6iaeEV2Bc52YdRekabfnTOY/ba6mriI8rXT0On1X8rLJowyc.CkIC'), -- rojorojo
('lucia',   'lucia@luettelo.com',   '$2a$12$f9kL8BwXqfgTfrf8X9wwS.VjR83/IkMIwwAB3ldcl71cP908bYTy2'), -- azulzaul
('dumbo',   'dumbo@luettelo.com',   '$2a$12$f9kL8BwXqfgTfrf8X9wwS.VjR83/IkMIwwAB3ldcl71cP908bYTy2'); -- grisgris



INSERT INTO List (name, category, description, username) VALUES
('Best Fantasy Books',   'Books',    'Best Recent Books',    'joseja'    ),
('Best Sci-Fi',          'Movies',   'Best RecentSF Movies', 'joseja'    ),
('Best Anime Series',    'Series',   'Best Anime Series',    'lucia'     );

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
('joseja',      1),
('joseja',      2),
('joseja',      3),
('lucia',       1),
('lucia',       3),
('dumbo',       1),
('dumbo',       2),
('dumbo',       3);

INSERT INTO Rating(value, username, itemId) VALUES
(5,     'joseja',   1   ),
(5,     'joseja',   2   ),
(5,     'joseja',   3   ),
(5,     'joseja',   4   ),
(3,     'joseja',   5   ),
(4,     'joseja',   6   ),
(5,     'joseja',   7   ),
(4,     'joseja',   8   ),
(4,     'joseja',   9   ),
(5,     'joseja',   10  ),
(4,     'joseja',   11  ),
(3,     'joseja',   12  ),
(5,     'lucia',    1   ),
(0,     'lucia',    3   ),
(5,     'lucia',    8   ),
(0,     'lucia',    9   ),
(5,     'lucia',    10  ),
(3,     'lucia',    11  ),
(3,     'lucia',    12  );

INSERT INTO Comment(content,username,listId) VALUES
('This list is tor','dumbo',1),
('This list is top*','dumbo',1),
('@dumbo, you can edit your comment','joseja',1),
('I will add the remaining animes ASAP','lucia',3);