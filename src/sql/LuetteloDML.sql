-------------
--- User
-------------

--INSERT NEW USER IN THE DB {username, email, password}
INSERT INTO User (username, email, password)
VALUES (?,?,?);

--REMOVE USER FROM DB {username, password}
DELETE FROM User
WHERE username = ? AND
      password = ?;

-------------
--- List
-------------

--CREATE LIST {name, category, description, username}
INSERT INTO List (name, category, description, username)
VALUES (?, ?, ?, ?);

--EDIT LIST {name, category, description, listId}
UPDATE List
SET name 		= ?,
	category 	= ?,
	description = ?
WHERE listId = ?

--REMOVE LIST {listId}
DELETE FROM List
WHERE listId = ?

-------------
--- Item
-------------

--ADD ITEM {name, url, listId}
INSERT INTO Item(name, url, listId)
VALUES (?, ?, ?)

--EDIT ITEM {name, url, itemId}
UPDATE Item
SET name = ?,
	url  = ?
WHERE itemId = ?

--REMOVE ITEM {itemId}
DELETE FROM Item
WHERE itemId = ?

-------------
--- Comment
-------------

--ADD COMMENT {content, username, listId}
INSERT INTO Comment(content, username, listId)
VALUES (?, ?, ?)

--EDIT COMMENT {content, commentId, username}
UPDATE Comment
SET content = ?
WHERE commentId = ? AND username = ?

--REMOVE COMMENT {commentId, username}
DELETE FROM Comment
WHERE commentId = ? AND username = ?

-------------
--- Subscription
-------------

--SUBSCRIBE TO A LIST {username, listId}
INSERT INTO Subscription(username,listId)
VALUES (?,?);

--UNSUBSCRIBE FROM A LIST {username, listId}
DELETE FROM Subscription
WHERE username = ? AND listId = ?;

-------------
--- Rating
-------------

--CHECK IN AN ITEM {username, itemId}
INSERT INTO Rating(username,itemId)
VALUES (?, ?);

--(RE)RATE AN ELEMENT {value, username, itemId}
REPLACE INTO Rating(value,username,itemId)
VALUES (?, ?, ?);

--UNCHECK AN ELEMENT {username, itemId}
DELETE FROM Rating
WHERE username = ? AND itemId = ?

-- -------------
-- --- VOTES
-- -------------

-- --(RE)VOTE AN ELEMENT {value, username, commentId}
-- REPLACE INTO Vote(value,username,commentId)
-- VALUES (?, ?, ?);

-- --UNVOTE AN ELEMENT {username, commentId}
-- DELETE FROM Vote
-- WHERE username = ? and commentId = ?
