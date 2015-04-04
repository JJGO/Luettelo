-------------
--- User
-------------

--INSERT NEW USER IN THE DB {username, email, password}
INSERT INTO User (username, email, password)
VALUES (?,?,?);

--REMOVE USER FROM DB {username, password}
--The password is checked before
DELETE FROM User
WHERE username = ? AND
      password = ?;

-------------
--- List
-------------

--CREATE LIST {name, category, description, username}
INSERT INTO List (name, category, description, username)
VALUES (?, ?, ?, ?);

--EDIT LIST {name, category, description, listId, username}
--Only the creator can edit
UPDATE List
SET name 		= ?,
	category 	= ?,
	description = ?
WHERE listId = ? AND username = ?

--REMOVE LIST {listId, username}
--Only the creator can delete
DELETE FROM List
WHERE listId = ? AND username = ?

-------------
--- Item
-------------

--ADD ITEM {name, url, listId, listId, username}
--Only the creator can add
INSERT INTO Item(name, url, listId)
SELECT ?, ?, ?
FROM List
WHERE listId = ? and username = ?


--EDIT ITEM {name, url, itemId, username}
--Only the creator can edit
UPDATE Item
SET name = ?,
	url  = ?
WHERE 	itemId = ? AND
	  	listId IN (	SELECT listId
					FROM List
					WHERE username = ?)

--REMOVE ITEM {itemId, username}
--Only the creator can delete
DELETE FROM Item
WHERE 	itemId = ? AND
	  	listId IN (	SELECT listId
					FROM List
					WHERE username = ?)

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

--CHECK IN AN ITEM {username, itemId, username, itemId}
--Only a subscriber can check
INSERT INTO Rating(username,itemId)
SELECT ?, ?
FROM Item I
	 INNER JOIN Subscription S
	 	ON I.listId = S.listId
WHERE S.username = ? and I.itemId = ?

--(RE)RATE AN ELEMENT {value, username, itemId}
-- Only a checked item can be rated
UPDATE Rating
SET value = ?
WHERE username = ? AND itemId = ?

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
