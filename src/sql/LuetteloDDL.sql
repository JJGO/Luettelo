CREATE DATABASE IF NOT EXISTS luettelo;
USE luettelo;

CREATE TABLE IF NOT EXISTS User
(
    username    VARCHAR(31)     NOT NULL,
    email       VARCHAR(254)    NOT NULL,
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
        FOREIGN KEY(username) REFERENCES User(username) ON DELETE CASCADE
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
        FOREIGN KEY(listId) REFERENCES List(listId) ON DELETE CASCADE
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
        FOREIGN KEY(username) REFERENCES User(username) ON DELETE CASCADE,
    CONSTRAINT Comment_listId_fkey
        FOREIGN KEY(listId) REFERENCES List(listId) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Subscription
(
    username    VARCHAR(31)     NOT NULL,
    listId      INT UNSIGNED    NOT NULL,

    CONSTRAINT Subscription_pkey
        PRIMARY KEY(username,listId),
    CONSTRAINT Subscription_username_fkey
        FOREIGN KEY(username) REFERENCES User(username) ON DELETE CASCADE,
    CONSTRAINT Subscription_listId_fkey
        FOREIGN KEY(listId) REFERENCES List(listId) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Rating
(
    value       TINYINT         DEFAULT 0,
    username    VARCHAR(31)     NOT NULL,
    itemId      INT UNSIGNED    NOT NULL,

    PRIMARY KEY(username,itemId),
    FOREIGN KEY(username) REFERENCES User(username) ON DELETE CASCADE,
    FOREIGN KEY(itemId) REFERENCES Item(itemId) ON DELETE CASCADE
);



CREATE VIEW Item_avg AS
SELECT  I.itemId,
        listId,
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

-- CREATE TABLE IF NOT EXISTS Vote
-- (
--     value       TINYINT(1)      DEFAULT NULL,
--     user        VARCHAR(31)     NOT NULL,
--     comment     INT UNSIGNED    NOT NULL,

--     PRIMARY KEY(user,comment),
--     FOREIGN KEY(user) REFERENCES User(username) ON DELETE CASCADE,
--     FOREIGN KEY(comment) REFERENCES Comment(id) ON DELETE CASCADE
-- );
