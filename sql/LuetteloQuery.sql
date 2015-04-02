-------------
--- ASIDE
-------------

--CHECK IF AN USER IS IN THE DB
SELECT USERNAME
FROM LUETTELO.USERS
WHERE USERNAME = ?user AND
      PASSWORD = ?password;

--GET THE LISTS CREATED BY AN USER

--GET THE LISTS A USER HAS SUBSCRIBED TO

-------------
--- INDEX
-------------



--GET THE 50 FIRST ITEMS OF A CATEGORY {category,user}
SELECT L.ID,
       L.NAME,
       L.CATEGORY,
       L.USER,
       ROUND(AVG(RI.ITEMAVG)) AS AVERAGE,
       COALESCE(C.NUMCOM,0) AS NUMCOM,
       NOT ISNULL(S.USER) AS SUBSCRIBED
FROM LISTS L
     INNER JOIN (       SELECT  LIST,
                                ROUND(20*AVG(NULLIF(R.VALUE,0))) AS ITEMAVG
                        FROM ITEMS I
                        INNER JOIN RATINGS R
                            ON I.ID = R.ITEM
                        GROUP BY I.ID
                ) RI
        ON L.ID = RI.LIST
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

WHERE CATEGORY LIKE ? cat --'%'
GROUP BY RI.LIST
ORDER BY AVERAGE DESC
LIMIT 50;



-------------
--- LIST
-------------

--GET THE INFO OF A LIST (NO AUTH) {list}
SELECT L.ID, L.NAME, L.CATEGORY, L.DESCRIPTION, L.USER, ROUND(AVG(RI.ITEMAVG)) AS AVERAGE
FROM LISTS L
     INNER JOIN (   SELECT LIST, ROUND(20*AVG(R.VALUE)) AS ITEMAVG
                    FROM ITEMS I
                    INNER JOIN RATINGS R
                        ON I.ID = R.ITEM
                    WHERE LIST = ?list
                    GROUP BY I.ID
                ) RI
        ON L.ID = RI.LIST

GROUP BY RI.LIST
ORDER BY AVERAGE DESC;

--GET THE INFO OF A LIST AS WELL AS THE SUBSCRIPTION (AUTH) {user, list}


--GET THE ITEMS OF A LIST (NO AUTH) {list}
SELECT I.ID, I.NAME, I.URL, ROUND(20*AVG(R.VALUE)) AS AVERAGE
FROM ITEMS I
     INNER JOIN RATINGS R
        ON I.ID = R.ITEM
WHERE I.LIST=?list
GROUP BY I.ID
ORDER BY AVERAGE DESC;

--GET THE ITEMS OF A LIST AS WELL AS THEIR RATINGS (AUTH) {user, list}
SELECT I.ID, I.NAME, I.URL, ROUND(20*AVG(R.VALUE)) AS AVERAGE, RU.VALUE
FROM ITEMS I
     INNER JOIN RATINGS R
        ON I.ID = R.ITEM
     LEFT OUTER JOIN (  SELECT VALUE
                        FROM RATINGS
                        WHERE USER = ?user
                     ) RU
        ON I.ID = RU.ITEM
WHERE I.LIST = ?list
GROUP BY I.ID
ORDER BY AVERAGE DESC;



--###GET THE AVERAGE RATING OF A LIST
