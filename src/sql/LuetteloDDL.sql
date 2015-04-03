CREATE DATABASE IF NOT EXISTS LUETTELO;
USE LUETTELO;

CREATE TABLE IF NOT EXISTS User
(
    username    VARCHAR(31)     NOT NULL,
    email       VARCHAR(254)    NOT NULL,
    password    CHAR(60)        NOT NULL,
    PRIMARY KEY(username)
);

CREATE TABLE IF NOT EXISTS List
(
    id          INT UNSIGNED    NOT NULL    AUTO_INCREMENT,
    name        VARCHAR(255)    NOT NULL,
    category    VARCHAR(31)     NOT NULL,
    description TEXT,

    user        VARCHAR(31)     NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(user) REFERENCES User(username) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Item
(
    id          INT UNSIGNED    NOT NULL    AUTO_INCREMENT,
    name        VARCHAR(255)    NOT NULL,
    url         VARCHAR(2083),

    list        INT UNSIGNED    NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(list) REFERENCES List(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Comment
(
    id          INT UNSIGNED    NOT NULL    AUTO_INCREMENT,
    user        VARCHAR(31)     NOT NULL,
    content     TEXT            NOT NULL,
    list        INT UNSIGNED    NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(user) REFERENCES User(username) ON DELETE CASCADE,
    FOREIGN KEY(list) REFERENCES List(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Subscription
(
    user        VARCHAR(31)     NOT NULL,
    list        INT UNSIGNED    NOT NULL,

    PRIMARY KEY(user,list),
    FOREIGN KEY(user) REFERENCES User(username) ON DELETE CASCADE,
    FOREIGN KEY(list) REFERENCES List(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Rating
(
    value       TINYINT         DEFAULT 0,
    user        VARCHAR(31)     NOT NULL,
    item        INT UNSIGNED    NOT NULL,

    PRIMARY KEY(user,item),
    FOREIGN KEY(user) REFERENCES User(username) ON DELETE CASCADE,
    FOREIGN KEY(item) REFERENCES Item(id) ON DELETE CASCADE
);


-- CREATE TABLE IF NOT EXISTS Vote
-- (
--     value       TINYINT(1)      DEFAULT NULL,
--     user        VARCHAR(31)     NOT NULL,
--     comment     INT UNSIGNED    NOT NULL,

--     PRIMARY KEY(user,comment),
--     FOREIGN KEY(user) REFERENCES User(username) ON DELETE CASCADE,
--     FOREIGN KEY(comment) REFERENCES Comment(id) ON DELETE CASCADE
-- );

CREATE VIEW Items_avg AS
SELECT  id,
        list,
        ROUND(20*AVG(NULLIF(R.value,0))) AS average
FROM Item I
     INNER JOIN Rating R
        ON I.id = R.item
GROUP BY I.id;

CREATE VIEW Lists_avg AS
SELECT  list AS id,
        ROUND(AVG(average)) AS average
FROM Items_avg
GROUP BY list;
