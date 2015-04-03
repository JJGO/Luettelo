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

    //CREATE LIST {name, category, description, username}
    private static String QUERY_ADD_LIST = "INSERT INTO List (name, category, description, username) VALUES (?, ?, ?, ?)";
    //EDIT LIST {name, category, description, listId}
    private static String QUERY_UPDATE_LIST = "SET name = ?, category = ?, description = ? WHERE listId = ?";
    //REMOVE LIST {listId}
    private static String QUERY_REMOVE_LIST = "DELETE FROM List WHERE listId = ?";

    public ListDAO()
        throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/luettelo", USER, PASSWD);
    }

    public void addList(List list)
    {
        PreparedStatement ps = c.prepareStatement(QUERY_ADD_LIST);
        ps.setString(   1,  list.getName()          );
        ps.setString(   2,  list.getCategory()      );
        ps.setString(   3,  list.getDescription()   );
        ps.setString(   4,  list.getUsername()      );
        ps.executeQuery();

        rs.close();
        ps.close();
    }

    public void editList(List list)
    {
        PreparedStatement ps = c.prepareStatement(QUERY_UPDATE_LIST);
        ps.setString(   1,  list.getName()          );
        ps.setString(   2,  list.getCategory()      );
        ps.setString(   3,  list.getDescription()   );
        ps.setInt(      4,  list.getId()            );
        ps.executeQuery();

        rs.close();
        ps.close();
    }

    public void removeList(List list)
    {
        PreparedStatement ps = c.prepareStatement(QUERY_REMOVE_LIST);
        ps.setInt(      1,  list.getId()            );
        ps.executeQuery();

        rs.close();
        ps.close();
    }

    public void close()
        throws SQLException, ClassNotFoundException
    {
        con.close();
    }
}