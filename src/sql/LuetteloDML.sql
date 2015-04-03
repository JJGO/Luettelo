-------------
--- User
-------------

--INSERT NEW USER IN THE DB
INSERT INTO User (username, email, password)
VALUES (?user,?email,?hash);

--REMOVE USER FROM DB
DELETE FROM User
WHERE username = ?user

-------------
--- List
-------------

--CREATE LIST
INSERT INTO List (name, category, description, user)
VALUES (?name, ?cat, ?description, ?user);

--EDIT LIST
UPDATE List
SET name 		= ?name,
	category 	= ?cat,
	description = ?description
WHERE id = ?id

--REMOVE LIST
DELETE FROM List
WHERE id = ?id

-------------
--- Item
-------------

--ADD ITEM
INSERT INTO Item(name, url, list)
VALUES (?name, ?url, ?list)

--EDIT ITEM
UPDATE Item
SET name = ?name,
	url  = ?url
WHERE id = ?id

--REMOVE ITEM
DELETE FROM Item
WHERE id = ?id

-------------
--- Comment
-------------

--ADD COMMENT
INSERT INTO Comment(content, user, list)
VALUES (?content, ?user, ?list)

--EDIT COMMENT
UPDATE Comment
SET content = ?content
WHERE id = ?id

--REMOVE COMMENT
DELETE FROM Comment
WHERE id = ?id

-------------
--- Subscription
-------------

--SUBSCRIBE TO A LIST
INSERT INTO Subscription(user,list)
VALUES (?user,?email);

--UNSUBSCRIBE FROM A LIST
DELETE FROM Subscription
WHERE user = ?user AND list = ?list;

-------------
--- Rating
-------------

--CHECK IN AN ITEM
INSERT INTO Rating(user,item)
VALUES (?user, ?item);

--(RE)RATE AN ELEMENT
REPLACE INTO Rating(value,user,item)
VALUES (?rating, ?user, ?item);

--UNCHECK AN ELEMENT
DELETE FROM Rating
WHERE user = ?user and item = ?item

-- -------------
-- --- VOTES
-- -------------

-- --(RE)VOTE AN ELEMENT
-- REPLACE INTO Vote(value,user,comment)
-- VALUES (?value, ?user, ?comment);

-- --UNVOTE AN ELEMENT
-- DELETE FROM Vote
-- WHERE user = ?user and comment = ?comment
