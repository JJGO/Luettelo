/*
 * Class: dao.ListDAO
 * Luettelo
 *
 * 2015-04-03
 */

package dao;

import dominio.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JJ
 */
public class ListDAO
{
    private Connection con;

    static String USER = "root";
    static String PASSWD = "root";

// DML
    //CREATE LIST {name, category, description, username}
    private static String QUERY_ADD_LIST = "INSERT INTO List (name, category, description, username) VALUES (?, ?, ?, ?)";
    
    //EDIT LIST {name, category, description, listId}
    private static String QUERY_UPDATE_LIST = "SET name = ?, category = ?, description = ? WHERE listId = ?";
    
    //REMOVE LIST {listId}
    private static String QUERY_REMOVE_LIST = "DELETE FROM List WHERE listId = ?";

// QUERY
    // GET THE LISTS CREATED BY A USER {username}
    private static String QUERY_CREATED_LISTS = "SELECT L.listId, L.name, LA.average FROM List L INNER JOIN List_avg LA ON L.listId = LA.listId WHERE username = ?";
    
    // GET THE List A USER HAS SUBSCRIBED TO {username}
    private static String QUERY_SUBSCRIBED_LISTS = "SELECT L.listId, L.name, LA.average FROM List L INNER JOIN List_avg LA ON L.listId = LA.listId INNER JOIN (   SELECT listId, username FROM Subscription WHERE username = ? ) S ON L.listId = S.listId";
    
    // GET LISTS OF A CATEGORY {username, category}
    private static String QUERY_LISTS_BY_CATEGORY = "SELECT L.listId, L.name, L.category, L.username, LA.average, COALESCE(C.numcom,0) AS numcom, NOT ISNULL(S.username) AS subscribed FROM List L INNER JOIN List_avg LA ON L.listId = LA.listId LEFT OUTER JOIN (  SELECT  listId, COUNT(*) AS numcom FROM Comment GROUP BY listId ) C ON L.listId = C.listId LEFT OUTER JOIN (  SELECT listId, username FROM Subscription WHERE username = ? ) S ON L.listId = S.listId WHERE category LIKE ? ORDER BY LA.average DESC";
    
    // SEARCH BY A KEYWORD {username, '%keyword%'}
    private static String QUERY_LISTS_BY_KEYWORD = "SELECT L.listId, L.name, L.category, L.username, LA.average, COALESCE(C.numcom,0) AS numcom, NOT ISNULL(S.username) AS subscribed FROM List L INNER JOIN List_avg LA ON L.listId = LA.listId LEFT OUTER JOIN (  SELECT  listId, COUNT(*) AS numcom FROM Comment GROUP BY listId ) C ON L.listId = C.listId LEFT OUTER JOIN (  SELECT listId, username FROM Subscription WHERE username = ? ) S ON L.listId = S.listId WHERE name LIKE ORDER BY LA.average DESC";
    
    // GET THE INFO OF A LIST {username,listId}
    private static String QUERY_LIST_INFO = "SELECT  L.listId, L.name, L.category, L.description, L.username, LA.average, COALESCE(C.numcom,0) AS numcom, NOT ISNULL(S.username) AS subscribed FROM List L INNER JOIN List_avg LA ON L.listId = LA.listId LEFT OUTER JOIN (  SELECT  listId, COUNT(*) AS numcom FROM Comment ) C ON L.listId = C.listId LEFT OUTER JOIN (  SELECT listId, username FROM Subscription WHERE username = ? ) S ON L.listId = S.listId WHERE L.listId = ? ORDER BY LA.average DESC";
    
    public ListDAO()
        throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/luettelo", USER, PASSWD);
    }

    public void addList(List list, User user) throws SQLException
    {
        //CREATE LIST {name, category, description, username}
        PreparedStatement ps = c.prepareStatement(QUERY_ADD_LIST);
        ps.setString(   1,  list.getName()          );
        ps.setString(   2,  list.getCategory()      );
        ps.setString(   3,  list.getDescription()   );
        ps.setString(   4,  user.getUsername()      );
        ps.executeQuery();

        rs.close();
        ps.close();
    }

    public void editList(List list) throws SQLException
    {
        //EDIT LIST {name, category, description, listId}
        PreparedStatement ps = c.prepareStatement(QUERY_UPDATE_LIST);
        ps.setString(   1,  list.getName()          );
        ps.setString(   2,  list.getCategory()      );
        ps.setString(   3,  list.getDescription()   );
        ps.setInt(      4,  list.getId()            );
        ps.executeQuery();

        rs.close();
        ps.close();
    }

    public void removeList(List list) throws SQLException
    {
        //REMOVE LIST {listId}
        PreparedStatement ps = c.prepareStatement(QUERY_REMOVE_LIST);
        ps.setInt(      1,  list.getId()            );
        ps.executeQuery();

        rs.close();
        ps.close();
    }

    // GET THE LISTS CREATED BY A USER {username}
    // GET THE List A USER HAS SUBSCRIBED TO {username}
    // GET LISTS OF A CATEGORY {username, category}
    // SEARCH BY A KEYWORD {username, '%keyword%'}
    // GET THE INFO OF A LIST {username,listId}

    public Producto findByCreator(User user) throws SQLException
    {
        // GET THE LISTS CREATED BY A USER {username}
        PreparedStatement ps = c.prepareStatement(QUERY_CREATED_LISTS);
        ps.setString(   1,  user.getUsername()      );
        this.parseResultSetBrief(ps.executeQuery());
        //this.parseResultSetDisplay(ps.executeQuery());
        //this.parseResultSet(ps.executeQuery());

        Producto p = null;
        
        

        rs.close();
        ps.close();
        return p;
    }

    private ArrayList<List> parseResultSet(ResultSet rs)
    {
        ArrayList<List> lists = new ArrayList<List>();
        while (rs.next())
        {
            int id          = rs.getInt("listId");
            String nombre   = rs.getString("nombre");
            double precio   = rs.getDouble("precio");

            int         id          = rs.getInt(    "listId"        );
            String      name        = rs.getString( "name"          );
            String      category    = rs.getString( "category"      );
            String      description = rs.getString( "description"   );
            String      username    = rs.getString( "username"      );
            Integer     average     = rs.getInteger("average"       );
            Integer     comments    = rs.getInteger("numcom"        );
            boolean     subscribed  = rs.getBoolean("subscribed"    );
            
            //List list = new List( id, name, average)
            //List list = new List( id, name, category, username, average, comments, subscribed)
            List list = new List( id, name, category, description, username, average, comments, subscribed)

            
            lists.add(list);
        }
        return lists;
    }

    public void close()
        throws SQLException, ClassNotFoundException
    {
        con.close();
    }
}
