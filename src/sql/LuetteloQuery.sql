-------------
--- ASIDE
-------------
--V--CHECK IF A USER IS IN THE DB {username}
SELECT username, password
FROM User
WHERE username = ?

-------------
--- INDEX
-------------

--V-- GET LISTS OF A CATEGORY {username, category}
SELECT L.listId,
       L.name,
       L.category,
       L.username,
       LA.average,
       COALESCE(C.numcom,0) AS numcom,
       NOT ISNULL(S.username) AS subscribed
FROM List L
     LEFT  OUTER JOIN List_avg LA
        ON L.listId = LA.listId
     LEFT OUTER JOIN (  SELECT  listId,
                                COUNT(*) AS numcom
                        FROM Comment
                        GROUP BY listId
                ) C
        ON L.listId = C.listId
     LEFT OUTER JOIN (  SELECT listId, username
                        FROM Subscription
                        WHERE username = ?
                ) S
        ON L.listId = S.listId
WHERE L.category LIKE ?
ORDER BY LA.average DESC;

--V-- SEARCH BY A KEYWORD {username, keyword}
SELECT L.listId,
       L.name,
       L.category,
       L.username,
       LA.average,
       COALESCE(C.numcom,0) AS numcom,
       NOT ISNULL(S.username) AS subscribed
FROM List L
     LEFT  OUTER JOIN List_avg LA
        ON L.listId = LA.listId
     LEFT OUTER JOIN (  SELECT  listId,
                                COUNT(*) AS numcom
                        FROM Comment
                        GROUP BY listId
                ) C
        ON L.listId = C.listId
     LEFT OUTER JOIN (  SELECT listId, username
                        FROM Subscription
                        WHERE username = ?
                ) S
        ON L.listId = S.listId
WHERE L.name LIKE '%?%'
ORDER BY LA.average DESC;


--V-- GET THE LISTS CREATED BY A USER {username, username(creator)}
SELECT L.listId,
       L.name,
       L.category,
       L.username,
       LA.average,
       COALESCE(C.numcom,0) AS numcom,
       NOT ISNULL(S.username) AS subscribed
FROM List L
     LEFT  OUTER JOIN List_avg LA
        ON L.listId = LA.listId
     LEFT OUTER JOIN (  SELECT  listId,
                                COUNT(*) AS numcom
                        FROM Comment
                        GROUP BY listId
                ) C
        ON L.listId = C.listId
     LEFT OUTER JOIN (  SELECT listId, username
                        FROM Subscription
                        WHERE username = ?
                ) S
        ON L.listId = S.listId
WHERE L.username = ?
ORDER BY LA.average DESC;

--V-- GET THE LISTS A USER HAS SUBSCRIBED TO {username}
SELECT L.listId,
       L.name,
       L.category,
       L.username,
       LA.average,
       COALESCE(C.numcom,0) AS numcom,
       NOT ISNULL(S.username) AS subscribed
FROM List L
     LEFT  OUTER JOIN List_avg LA
        ON L.listId = LA.listId
     LEFT OUTER JOIN (  SELECT  listId,
                                COUNT(*) AS numcom
                        FROM Comment
                        GROUP BY listId
                ) C
        ON L.listId = C.listId
     LEFT OUTER JOIN (  SELECT listId, username
                        FROM Subscription
                        WHERE username = ?
                ) S
        ON L.listId = S.listId
WHERE S.username IS NOT NULL
ORDER BY LA.average DESC;


-------------
--- LIST
-------------

--V-- GET THE INFO OF A LIST {username,listId}

SELECT  L.listId,
        L.name,
        L.category,
        L.description,
        L.username,
        LA.average,
        COALESCE(C.numcom,0) AS numcom,
        NOT ISNULL(S.username) AS subscribed
FROM List L
     LEFT  OUTER JOIN List_avg LA
        ON L.listId = LA.listId
     LEFT OUTER JOIN (  SELECT  listId,
                                COUNT(*) AS numcom
                        FROM Comment
                        GROUP BY listId
                ) C
        ON L.listId = C.listId
     LEFT OUTER JOIN (  SELECT listId, username
                        FROM Subscription
                        WHERE username = ?
                ) S
        ON L.listId = S.listId
WHERE L.listId = ?
ORDER BY LA.average DESC;

--V-- GET THE ITEMS OF A LIST AS WELL AS THEIR RATINGS (AUTH) {username, listId}
SELECT I.itemId,
       I.name,
       I.url,
       IA.average,
       RU.value AS rating
FROM Item I
     LEFT OUTER JOIN Item_avg IA
        ON I.itemId = IA.itemId
     LEFT OUTER JOIN (  SELECT itemId, value
                        FROM Rating
                        WHERE username = ?
                     ) RU
        ON I.itemId = RU.itemId
WHERE I.listId = ?
ORDER BY IA.average DESC;
--LIMIT 50;


--V-- GET THE COMMENTS OF A LIST {listId}

SELECT  commentId,
        username,
        content
FROM Comment
WHERE listId = ?