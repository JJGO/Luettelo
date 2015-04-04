/*
 * Class: dao.ListDAO
 * Luettelo
 *
 * 2015-04-03
 */

package dao;

import dominio.List;
import dominio.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JJ
 */
public class ListDAO extends DAO
{

// DML
    //CREATE LIST {name, category, description, username}
    private static String QUERY_ADD_LIST = "INSERT INTO List (name, category, description, username) VALUES (?, ?, ?, ?)";
    
    //EDIT LIST {name, category, description, listId}
    private static String QUERY_UPDATE_LIST = "SET name = ?, category = ?, description = ? WHERE listId = ?";
    
    //REMOVE LIST {listId}
    private static String QUERY_REMOVE_LIST = "DELETE FROM List WHERE listId = ?";

// QUERY
    // GET THE INFO OF A LIST {username,listId}
    private static String QUERY_LIST_INFO = "SELECT  L.listId, L.name, L.category, L.description, L.username, LA.average, COALESCE(C.numcom,0) AS numcom, NOT ISNULL(S.username) AS subscribed FROM List L INNER JOIN List_avg LA ON L.listId = LA.listId LEFT OUTER JOIN (  SELECT  listId, COUNT(*) AS numcom FROM Comment ) C ON L.listId = C.listId LEFT OUTER JOIN (  SELECT listId, username FROM Subscription WHERE username = ? ) S ON L.listId = S.listId WHERE L.listId = ? ORDER BY LA.average DESC";

    private static String QUERY_LISTS = "SELECT L.listId, L.name, L.category, L.username, LA.average, COALESCE(C.numcom,0) AS numcom, NOT ISNULL(S.username) AS subscribed FROM List L INNER JOIN List_avg LA ON L.listId = LA.listId LEFT OUTER JOIN (  SELECT  listId, COUNT(*) AS numcom FROM Comment GROUP BY listId ) C ON L.listId = C.listId LEFT OUTER JOIN (  SELECT listId, username FROM Subscription WHERE username = ? ) S ON L.listId = S.listId";

    // GET THE LISTS CREATED BY A USER {username, username(creator)}
    private static String QUERY_LISTS_BY_CREATOR     = QUERY_LISTS + " WHERE L.username = ? ORDER BY LA.average DESC";
    
    // GET THE List A USER HAS SUBSCRIBED TO {username}
    private static String QUERY_LISTS_BY_SUBSCRIBED  = QUERY_LISTS + " WHERE S.username IS NOT NULL ORDER BY LA.average DESC";
    
    // GET LISTS OF A CATEGORY {username, category}
    private static String QUERY_LISTS_BY_CATEGORY    = QUERY_LISTS + " WHERE L.category LIKE ? ORDER BY LA.average DESC";
    
    // SEARCH BY A KEYWORD {username, '%keyword%'}
    private static String QUERY_LISTS_BY_KEYWORD     = QUERY_LISTS + " WHERE L.name LIKE '%?%' ORDER BY LA.average DESC";


    public ListDAO()
        throws SQLException, ClassNotFoundException
    {
        super();
    }

    public void addList(List list, User user) throws SQLException
    {
        //CREATE LIST {name, category, description, username}
        PreparedStatement ps = con.prepareStatement(QUERY_ADD_LIST);
        ps.setString(   1,  list.getName()          );
        ps.setString(   2,  list.getCategory()      );
        ps.setString(   3,  list.getDescription()   );
        ps.setString(   4,  user.getUsername()      );
        ps.executeQuery();

        ps.close();
    }

    public void editList(List list) throws SQLException
    {
        //EDIT LIST {name, category, description, listId}
        PreparedStatement ps = con.prepareStatement(QUERY_UPDATE_LIST);
        ps.setString(   1,  list.getName()          );
        ps.setString(   2,  list.getCategory()      );
        ps.setString(   3,  list.getDescription()   );
        ps.setInt(      4,  list.getId()            );
        ps.executeQuery();

        ps.close();
    }

    public void removeList(List list) throws SQLException
    {
        //REMOVE LIST {listId}
        PreparedStatement ps = con.prepareStatement(QUERY_REMOVE_LIST);
        ps.setInt(      1,  list.getId()            );
        ps.executeQuery();

        ps.close();
    }

    public ArrayList<List> findByCreator(User creator, User user) throws SQLException
    {
        // GET THE LISTS CREATED BY A USER {username, username(creator)}
        PreparedStatement ps = con.prepareStatement(QUERY_LISTS_BY_CREATOR);
        ps.setString(   1,  user.getUsername()      );
        ps.setString(   2,  creator.getUsername()   );
        ArrayList<List> lists = this.parseResultSetDisplay(ps.executeQuery());
        ps.close();
        return lists;
    }

    public ArrayList<List> findBySubscribed(User user) throws SQLException
    {
        // GET THE List A USER HAS SUBSCRIBED TO {username}
        PreparedStatement ps = con.prepareStatement(QUERY_LISTS_BY_SUBSCRIBED);
        ps.setString(   1,  user.getUsername()      );
        ArrayList<List> lists = this.parseResultSetDisplay(ps.executeQuery());
        ps.close();
        return lists;
    }

    public ArrayList<List> findByCategory(String category, User user) throws SQLException
    {
        // GET LISTS OF A CATEGORY {username, category}
        PreparedStatement ps = con.prepareStatement(QUERY_LISTS_BY_CATEGORY);
        ps.setString(   1,  user.getUsername()      );
        ps.setString(   2,  category                );
        ArrayList<List> lists = this.parseResultSetDisplay(ps.executeQuery());
        ps.close();
        return lists;
    }
    
    public ArrayList<List> findByKeyword(String keyword, User user) throws SQLException
    {
        // SEARCH BY A KEYWORD {username, '%keyword%'}
        PreparedStatement ps = con.prepareStatement(QUERY_LISTS_BY_KEYWORD);
        ps.setString(   1,  user.getUsername()      );
        ps.setString(   2,  "%" + keyword + "%"     );
        ArrayList<List> lists = this.parseResultSetDisplay(ps.executeQuery());
        ps.close();
        return lists;
    }

    public List findById(List list, User user) throws SQLException
    {
        // GET THE INFO OF A LIST {username,listId}
        PreparedStatement ps = con.prepareStatement(QUERY_LISTS_INFO);
        ps.setString(   1,  user.getUsername()      );
        ps.setInt(      2,  list.getId()            );
        ArrayList<List> lists = this.parseResultSet(ps.executeQuery());
        ps.close();
        return lists.get(0);
    }


    private ArrayList<List> parseResultSet(ResultSet rs) throws SQLException
    {
        ArrayList<List> lists = new ArrayList<List>();
        while (rs.next())
        {
            int         id          =           rs.getInt(    "listId"        );
            String      name        =           rs.getString( "name"          );
            String      category    =           rs.getString( "category"      );
            String      description =           rs.getString( "description"   );
            String      username    =           rs.getString( "username"      );
            Integer     average     = (Integer) rs.getObject( "average"       );
            Integer     comments    = (Integer) rs.getObject( "numcom"        );
            boolean     subscribed  =           rs.getBoolean("subscribed"    );
            
            List list = new List(id, name, category, description, username, average, comments, subscribed);
            lists.add(list);
        }
        rs.close();
        return lists;
    }


    private ArrayList<List> parseResultSetDisplay(ResultSet rs) throws SQLException
    {
        ArrayList<List> lists = new ArrayList<List>();
        while (rs.next())
        {
            int         id          =           rs.getInt(    "listId"        );
            String      name        =           rs.getString( "name"          );
            String      category    =           rs.getString( "category"      );
            String      username    =           rs.getString( "username"      );
            Integer     average     = (Integer) rs.getObject( "average"       );
            Integer     comments    = (Integer) rs.getObject( "numcom"        );
            boolean     subscribed  =           rs.getBoolean("subscribed"    );
            
            List list = new List(id, name, category, username, average, comments, subscribed);
            lists.add(list);
        }
        rs.close();
        return lists;
    }
}
