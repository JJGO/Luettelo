CREATE DATABASE IF NOT EXISTS pat;
USE pat;

CREATE TABLE IF NOT EXISTS User
(
    username    VARCHAR(31)     NOT NULL,
    email       VARCHAR(254)    NOT NULL    UNIQUE,
    password    CHAR(60)        NOT NULL,
    CONSTRAINT User_pkey
        PRIMARY KEY(username)
);

CREATE TABLE IF NOT EXISTS List
(
    listId      INT UNSIGNED    NOT NULL    AUTO_INCREMENT,
    name        VARCHAR(255)    NOT NULL,
    category    VARCHAR(31)     NOT NULL,
    description TEXT,

    username    VARCHAR(31)     NOT NULL,

    CONSTRAINT List_pkey
        PRIMARY KEY(listId),

    CONSTRAINT List_username_fkey
        FOREIGN KEY(username) REFERENCES User(username)
            ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Item
(
    itemId      INT UNSIGNED    NOT NULL    AUTO_INCREMENT,
    name        VARCHAR(255)    NOT NULL,
    url         VARCHAR(2083),

    listId      INT UNSIGNED    NOT NULL,

    CONSTRAINT Item_pkey
        PRIMARY KEY(itemId),

    CONSTRAINT Item_listId_fkey
        FOREIGN KEY(listId) REFERENCES List(listId)
            ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Comment
(
    commentId   INT UNSIGNED    NOT NULL    AUTO_INCREMENT,
    username    VARCHAR(31)     NOT NULL,
    content     TEXT            NOT NULL,
    listId      INT UNSIGNED    NOT NULL,

    CONSTRAINT Comment_pkey
        PRIMARY KEY(commentId),

    CONSTRAINT Comment_username_fkey
        FOREIGN KEY(username) REFERENCES User(username)
            ON DELETE CASCADE,

    CONSTRAINT Comment_listId_fkey
        FOREIGN KEY(listId) REFERENCES List(listId)
            ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Subscription
(
    username    VARCHAR(31)     NOT NULL,
    listId      INT UNSIGNED    NOT NULL,

    CONSTRAINT Subscription_pkey
        PRIMARY KEY(username,listId),

    CONSTRAINT Subscription_username_fkey
        FOREIGN KEY(username) REFERENCES User(username)
            ON DELETE CASCADE,

    CONSTRAINT Subscription_listId_fkey
        FOREIGN KEY(listId) REFERENCES List(listId)
            ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Rating
(
    value       TINYINT         DEFAULT 0,
    username    VARCHAR(31)     NOT NULL,
    itemId      INT UNSIGNED    NOT NULL,
    listId      INT UNSIGNED    NOT NULL,

    CONSTRAINT Rating_pkey
        PRIMARY KEY(username,itemId),

    CONSTRAINT Rating_username_listId_fkey
        FOREIGN KEY(username,listId) REFERENCES Subscription(username,listId)
            ON DELETE CASCADE,

    CONSTRAINT Rating_itemId_fkey
        FOREIGN KEY(itemId) REFERENCES Item(itemId)
            ON DELETE CASCADE
);



CREATE VIEW Item_avg AS
SELECT  I.itemId,
        I.listId,
        ROUND(20*AVG(NULLIF(R.value,0))) AS average
FROM Item I
     INNER JOIN Rating R
        ON I.itemId = R.itemId
GROUP BY I.itemId;

CREATE VIEW List_avg AS
SELECT  listId,
        ROUND(AVG(average)) AS average
FROM Item_avg
GROUP BY listId;


INSERT INTO User (username, email, password) VALUES
('joseja',  'joseja@luettelo.com',  '$2a$12$6iaeEV2Bc52YdRekabfnTOY/ba6mriI8rXT0On1X8rLJowyc.CkIC'), -- rojorojo
('lucia',   'lucia@luettelo.com',   '$2a$12$f9kL8BwXqfgTfrf8X9wwS.VjR83/IkMIwwAB3ldcl71cP908bYTy2'), -- azulzaul
('dumbo',   'dumbo@luettelo.com',   '$2a$12$f9kL8BwXqfgTfrf8X9wwS.VjR83/IkMIwwAB3ldcl71cP908bYTy2'), -- grisgris
('pat',     'pat@pat.pat',          '$2a$12$ysApaJy1XExWWOerw2w4kOD5t52MtjpZFdq2N8PWzXlNTqXs.ys.e'); -- pat



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

INSERT INTO Rating(value, username, itemId, listId) VALUES
(5,     'joseja',   1,   1 ),
(5,     'joseja',   2,   1 ),
(5,     'joseja',   3,   1 ),
(5,     'joseja',   4,   2 ),
(3,     'joseja',   5,   2 ),
(4,     'joseja',   6,   2 ),
(5,     'joseja',   7,   2 ),
(4,     'joseja',   8,   3 ),
(4,     'joseja',   9,   3 ),
(5,     'joseja',   10,  3 ),
(4,     'joseja',   11,  3 ),
(3,     'joseja',   12,  3 ),
(5,     'lucia',    1,   1 ),
(0,     'lucia',    3,   1 ),
(5,     'lucia',    8,   3 ),
(0,     'lucia',    9,   3 ),
(5,     'lucia',    10,  3 ),
(3,     'lucia',    11,  3 ),
(3,     'lucia',    12,  3 );

INSERT INTO Comment(content,username,listId) VALUES
('This list is tor','dumbo',1),
('This list is top*','dumbo',1),
('@dumbo, you can edit your comment','joseja',1),
('I will add the remaining animes ASAP','lucia',3);
