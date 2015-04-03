-------------
--- ASIDE
-------------

--V-- CHECK IF A USER IS IN THE DB {user,password}
SELECT username
FROM LUETTELO.User
WHERE username = ?user AND
      password = ?password;

--V-- GET THE LISTS CREATED BY A USER {user}
SELECT L.id,
       L.name,
       LA.average
FROM List L
     INNER JOIN Lists_avg LA
        ON L.id = LA.id
WHERE L.user = ?user
--V-- GET THE List A USER HAS SUBSCRIBED TO {user}
SELECT L.id,
       L.name,
       LA.average
FROM List L
     INNER JOIN Lists_avg LA
        ON L.id = LA.id
     INNER JOIN (   SELECT list, user
                    FROM Subscription
                    WHERE user = ?user
                ) S
             ON L.id = S.list

-------------
--- INDEX
-------------



--V-- GET THE 50 FIRST ITEMS OF A CATEGORY {category,user}
SELECT L.id,
       L.name,
       L.category,
       L.user,
       LA.average,
       COALESCE(C.numcom,0) AS numcom,
       NOT ISNULL(S.user) AS subscribed
FROM List L
     INNER JOIN Lists_avg LA
        ON L.id = LA.id
     LEFT OUTER JOIN (  SELECT  list,
                                COUNT(*) AS numcom
                        FROM Comment
                        GROUP BY list
                    
                ) C
        ON L.id = C.list
     LEFT OUTER JOIN (  SELECT list, user
                        FROM Subscription
                        WHERE user = ?user
                ) S
        ON L.id = S.list

WHERE L.category LIKE ?cat
ORDER BY LA.average DESC;

--V-- SEARCH BY A KEYWORD
SELECT L.id,
       L.name,
       L.category,
       L.user,
       LA.average,
       COALESCE(C.numcom,0) AS numcom,
       NOT ISNULL(S.user) AS subscribed
FROM List L
     INNER JOIN Lists_avg LA
        ON L.id = LA.id
     LEFT OUTER JOIN (  SELECT  list,
                                COUNT(*) AS numcom
                        FROM Comment
                        GROUP BY list
                    
                ) C
        ON L.id = C.list
     LEFT OUTER JOIN (  SELECT list, user
                        FROM Subscription
                        WHERE user = ?user
                ) S
        ON L.id = S.list

WHERE L.name LIKE '%?key%'
ORDER BY LA.average DESC;



-------------
--- LIST
-------------

--V-- GET THE INFO OF A LIST {user,list}

SELECT  L.id,
        L.name,
        L.category,
        L.description,
        L.user,
        LA.average,
        COALESCE(C.numcom,0) AS numcom,
        NOT ISNULL(S.user) AS subscribed
FROM List L
     INNER JOIN Lists_avg LA
        ON L.id = LA.id
     LEFT OUTER JOIN (  SELECT  list,
                                COUNT(*) AS numcom
                        FROM Comment
                    
                ) C
        ON L.id = C.list
     LEFT OUTER JOIN (  SELECT list, user
                        FROM Subscription
                        WHERE user = ?user
                ) S
        ON L.id = S.list
WHERE L.id = ?list
ORDER BY average DESC;

--V-- GET THE ITEMS OF A LIST AS WELL AS THEIR RATINGS (AUTH) {user, list}
SELECT I.id,
       I.name,
       I.url,
       IA.average,
       RU.value AS rating
FROM Item I
     INNER JOIN Items_avg IA
        ON I.id = IA.id
     LEFT OUTER JOIN (  SELECT value, item
                        FROM Rating
                        WHERE user = ?user
                     ) RU
        ON I.id = RU.item
WHERE I.list = ?list
ORDER BY average DESC;
--LIMIT 50;


--V-- GET THE COMMENTS OF A LIST {list}

SELECT  id,
        user
        content
FROM Comment
WHERE list = ?list