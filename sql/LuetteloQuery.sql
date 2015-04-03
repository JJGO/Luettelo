-------------
--- ASIDE
-------------

--V-- CHECK IF AN USER IS IN THE DB {user,password}
SELECT USERNAME
FROM LUETTELO.USERS
WHERE USERNAME = ?user AND
      PASSWORD = ?password;

--V-- GET THE LISTS CREATED BY AN USER {user}
SELECT L.ID,
       L.NAME,
       LA.AVERAGE
FROM LISTS L
     INNER JOIN LISTS_AVG LA
        ON L.ID = LA.ID
WHERE L.USER = ?user
--V-- GET THE LISTS A USER HAS SUBSCRIBED TO {user}
SELECT L.ID,
       L.NAME,
       LA.AVERAGE
FROM LISTS L
     INNER JOIN LISTS_AVG LA
        ON L.ID = LA.ID
     INNER JOIN (   SELECT LIST, USER
                    FROM SUBSCRIPTIONS
                    WHERE USER = ?user
                ) S
             ON L.ID = S.LIST

-------------
--- INDEX
-------------



--V-- GET THE 50 FIRST ITEMS OF A CATEGORY {category,user}
SELECT L.ID,
       L.NAME,
       L.CATEGORY,
       L.USER,
       LA.AVERAGE,
       COALESCE(C.NUMCOM,0) AS NUMCOM,
       NOT ISNULL(S.USER) AS SUBSCRIBED
FROM LISTS L
     INNER JOIN LISTS_AVG LA
        ON L.ID = LA.ID
     LEFT OUTER JOIN (  SELECT  LIST,
                                COUNT(*) AS NUMCOM
                        FROM COMMENTS
                        GROUP BY LIST
                    
                ) C
        ON L.ID = C.LIST
     LEFT OUTER JOIN (  SELECT LIST, USER
                        FROM SUBSCRIPTIONS
                        WHERE USER = ?user
                ) S
        ON L.ID = S.LIST

WHERE L.CATEGORY LIKE ?cat
ORDER BY LA.AVERAGE DESC;

--V-- SEARCH BY A KEYWORD
SELECT L.ID,
       L.NAME,
       L.CATEGORY,
       L.USER,
       LA.AVERAGE,
       COALESCE(C.NUMCOM,0) AS NUMCOM,
       NOT ISNULL(S.USER) AS SUBSCRIBED
FROM LISTS L
     INNER JOIN LISTS_AVG LA
        ON L.ID = LA.ID
     LEFT OUTER JOIN (  SELECT  LIST,
                                COUNT(*) AS NUMCOM
                        FROM COMMENTS
                        GROUP BY LIST
                    
                ) C
        ON L.ID = C.LIST
     LEFT OUTER JOIN (  SELECT LIST, USER
                        FROM SUBSCRIPTIONS
                        WHERE USER = ?user
                ) S
        ON L.ID = S.LIST

WHERE CATEGORY LIKE ?cat
ORDER BY LA.AVERAGE DESC;



-------------
--- LIST
-------------

--V-- GET THE INFO OF A LIST {user,list}

SELECT  L.ID,
        L.NAME,
        L.CATEGORY,
        L.DESCRIPTION,
        L.USER,
        LA.AVERAGE,
        COALESCE(C.NUMCOM,0) AS NUMCOM,
        NOT ISNULL(S.USER) AS SUBSCRIBED
FROM LISTS L
     INNER JOIN LISTS_AVG LA
        ON L.ID = LA.ID
     LEFT OUTER JOIN (  SELECT  LIST,
                                COUNT(*) AS NUMCOM
                        FROM COMMENTS
                    
                ) C
        ON L.ID = C.LIST
     LEFT OUTER JOIN (  SELECT LIST, USER
                        FROM SUBSCRIPTIONS
                        WHERE USER = ?user
                ) S
        ON L.ID = S.LIST
WHERE L.ID = ?list
ORDER BY AVERAGE DESC;

--GET THE ITEMS OF A LIST AS WELL AS THEIR RATINGS (AUTH) {user, list}
SELECT I.ID,
       I.NAME,
       I.URL,
       IA.AVERAGE,
       RU.VALUE AS RATING
FROM ITEMS I
     INNER JOIN ITEMS_AVG IA
        ON I.ID = IA.ID
     LEFT OUTER JOIN (  SELECT VALUE, ITEM
                        FROM RATINGS
                        WHERE USER = ?user
                     ) RU
        ON I.ID = RU.ITEM
WHERE I.LIST = ?list
ORDER BY AVERAGE DESC;
--LIMIT 50;


--GET THE COMMENTS OF A LIST


