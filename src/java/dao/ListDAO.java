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
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author JJ
 */
public class ListDAO extends DAO
{

// DML
    //CREATE LIST {name, category, description, username}
    private static final String ADD_LIST = "INSERT INTO List (name, category, description, username) VALUES (?, ?, ?, ?)";
    
    //EDIT LIST {name, category, description, listId, username}
    private static final String EDIT_LIST = "UPDATE List SET name = ?, category = ?, description = ? WHERE listId = ? AND username = ?";

    //REMOVE LIST {listId, username}
    private static final String REMOVE_LIST = "DELETE FROM List WHERE listId = ? AND username = ?";

    //SUBSCRIBE TO A LIST {username, listId}
    private static final String SUBSCRIBE_LIST = "INSERT INTO Subscription(username,listId) VALUES (?,?)";

    //UNSUBSCRIBE FROM A LIST {username, listId}
    private static final String UNSUBSCRIBE_LIST = "DELETE FROM Subscription WHERE username = ? AND listId = ?";

// QUERY
    // GET THE INFO OF A LIST {username,listId}
    private static final String QUERY_LIST_INFO = "SELECT  L.listId, L.name, L.category, L.description, L.username, LA.average, COALESCE(C.numcom,0) AS numcom, NOT ISNULL(S.username) AS subscribed FROM List L LEFT OUTER JOIN List_avg LA ON L.listId = LA.listId LEFT OUTER JOIN (  SELECT  listId, COUNT(*) AS numcom FROM Comment GROUP BY listId) C ON L.listId = C.listId LEFT OUTER JOIN (  SELECT listId, username FROM Subscription WHERE username = ? ) S ON L.listId = S.listId WHERE L.listId = ? ORDER BY LA.average DESC";

    private static final String QUERY_LISTS = "SELECT L.listId, L.name, L.category, L.username, LA.average, COALESCE(C.numcom,0) AS numcom, NOT ISNULL(S.username) AS subscribed FROM List L LEFT OUTER JOIN List_avg LA ON L.listId = LA.listId LEFT OUTER JOIN (  SELECT  listId, COUNT(*) AS numcom FROM Comment GROUP BY listId ) C ON L.listId = C.listId LEFT OUTER JOIN (  SELECT listId, username FROM Subscription WHERE username = ? ) S ON L.listId = S.listId";

    // GET THE LISTS CREATED BY A USER {username, username(creator)}
    private static final String QUERY_LISTS_BY_USER     = QUERY_LISTS + " WHERE L.username = ? ORDER BY LA.average DESC";
    
    // GET THE List A USER HAS SUBSCRIBED TO {username}
    private static final String QUERY_LISTS_BY_SUBSCRIBED  = QUERY_LISTS + " WHERE S.username IS NOT NULL ORDER BY LA.average DESC";
    
    // GET LISTS OF A CATEGORY {username, category}
    private static final String QUERY_LISTS_BY_CATEGORY    = QUERY_LISTS + " WHERE L.category LIKE ? ORDER BY LA.average DESC";
    
    // SEARCH BY A KEYWORD {username, '%keyword%'}
    private static final String QUERY_LISTS_BY_KEYWORD     = QUERY_LISTS + " WHERE L.name LIKE ? ORDER BY LA.average DESC";

    // GET TOP LISTS {username}
    private static final String QUERY_LISTS_BY_RATING     = QUERY_LISTS + " ORDER BY LA.average DESC LIMIT ?";

    public ListDAO(HttpServletRequest request)
        throws SQLException, ClassNotFoundException
    {
        super(request);
    }

    public List addList(List list, User user) throws SQLException
    {
        //CREATE LIST {name, category, description, username}
        PreparedStatement ps = con.prepareStatement(ADD_LIST);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1,  list.getName()          );
        ps.setString(   2,  list.getCategory()      );
        ps.setString(   3,  list.getDescription()   );
        ps.setString(   4,  username      );
        
        int rows = ps.executeUpdate();
        ps.close();
        list = null;
        if(rows != 0)
        {
            ps = con.prepareStatement("SELECT max(listId) AS last_id FROM List");
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                int lastid = rs.getInt("last_id");
                list = new List(lastid);
            }
        }
        return list;
    }

    public boolean editList(List list, User user) throws SQLException
    {
        //EDIT LIST {name, category, description, listId, username}
        PreparedStatement ps = con.prepareStatement(EDIT_LIST);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1,  list.getName()          );
        ps.setString(   2,  list.getCategory()      );
        ps.setString(   3,  list.getDescription()   );
        ps.setInt(      4,  list.getId()            );
        ps.setString(   5,  username      );
        
        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }

    public boolean removeList(List list, User user) throws SQLException
    {
        //REMOVE LIST {listId, username}
        PreparedStatement ps = con.prepareStatement(REMOVE_LIST);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setInt(      1,  list.getId()            );
        ps.setString(   2,  username      );
        
        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }

    public boolean subscribeList(List list, User user) throws SQLException
    {
        //SUBSCRIBE TO A LIST {username, listId}
        PreparedStatement ps = con.prepareStatement(SUBSCRIBE_LIST);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1,  username      );
        ps.setInt(      2,  list.getId()            );
        
        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }

    public boolean unsubscribeList(List list, User user) throws SQLException
    {
        //UNSUBSCRIBE FROM A LIST {username, listId}
        PreparedStatement ps = con.prepareStatement(UNSUBSCRIBE_LIST);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1,  username      );
        ps.setInt(      2,  list.getId()            );
        
        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }

    public ArrayList<List> findByUser(User creator, User user) throws SQLException
    {
        // GET THE LISTS CREATED BY A USER {username, username(creator)}
        PreparedStatement ps = con.prepareStatement(QUERY_LISTS_BY_USER);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1,  username      );
        ps.setString(   2,  creator.getUsername()   );
        ArrayList<List> lists = this.parseResultSetDisplay(ps.executeQuery());
        ps.close();
        return lists;
    }

    public ArrayList<List> findBySubscribed(User user) throws SQLException
    {
        // GET THE List A USER HAS SUBSCRIBED TO {username}
        PreparedStatement ps = con.prepareStatement(QUERY_LISTS_BY_SUBSCRIBED);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1,  username      );
        ArrayList<List> lists = this.parseResultSetDisplay(ps.executeQuery());
        ps.close();
        return lists;
    }

    public ArrayList<List> findByCategory(String category, User user) throws SQLException
    {
        // GET LISTS OF A CATEGORY {username, category}
        PreparedStatement ps = con.prepareStatement(QUERY_LISTS_BY_CATEGORY);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1,  username      );
        ps.setString(   2,  category                );
        ArrayList<List> lists = this.parseResultSetDisplay(ps.executeQuery());
        ps.close();
        return lists;
    }
    
    public ArrayList<List> findByKeyword(String keyword, User user) throws SQLException
    {
        // SEARCH BY A KEYWORD {username, '%keyword%'}
        PreparedStatement ps = con.prepareStatement(QUERY_LISTS_BY_KEYWORD);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1,  username      );
        ps.setString(   2,  "%" + keyword + "%"     );
        ArrayList<List> lists = this.parseResultSetDisplay(ps.executeQuery());
        ps.close();
        return lists;
    }

    public ArrayList<List> findByRating(int limit, User user) throws SQLException
    {
        // GET THE List A USER HAS SUBSCRIBED TO {username}
        PreparedStatement ps = con.prepareStatement(QUERY_LISTS_BY_RATING);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1,  username      );
        ps.setInt(   2,  limit      );
        ArrayList<List> lists = this.parseResultSetDisplay(ps.executeQuery());
        ps.close();
        return lists;
    }

    public List findById(List list, User user) throws SQLException
    {
        // GET THE INFO OF A LIST {username,listId}
        PreparedStatement ps = con.prepareStatement(QUERY_LIST_INFO);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1,  username      );
        ps.setInt(      2,  list.getId()            );
        ArrayList<List> lists = this.parseResultSet(ps.executeQuery());
        ps.close();
        if(lists != null && !lists.isEmpty())
            return lists.get(0);
        else
            return null;
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
            Integer     average     =           rs.getObject("average") != null ? rs.getInt("average") : null;
            Integer     comments    =           rs.getObject("numcom") != null ? rs.getInt("numcom") : null;
            boolean     subscribed  =           rs.getInt("subscribed") == 1;
            
            List list = new List(id, name, category, description, username, average, comments, subscribed);
            lists.add(list);
            //System.out.println(list);
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
            Integer     average     =           rs.getObject("average") != null ? rs.getInt("average") : null;
            Integer     comments    =           rs.getObject("numcom") != null ? rs.getInt("numcom") : null;
            boolean     subscribed  =           rs.getInt("subscribed") == 1;
            
            List list = new List(id, name, category, username, average, comments, subscribed);
            lists.add(list);
            //System.out.println(list);
        }
        rs.close();
        return lists;
    }
}
