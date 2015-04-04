/*
 * Class: dao.UserDAO
 * Luettelo
 *
 * 2015-04-03
 */

package dao;

import dominio.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JJ
 */
public class UserDAO extends DAO
{

    //INSERT NEW USER IN THE DB {username, email, password}
    private static String QUERY_ADD_USER = "INSERT INTO User (username, email, password) VALUES (?,?,?)";

    //REMOVE USER FROM DB {username, password}
    private static String QUERY_REMOVE_USER = "DELETE FROM User WHERE username = ? AND password = ?";

    // CHECK IF A USER IS IN THE DB {username,password}
    private static String QUERY_FIND_USER = "SELECT username FROM User WHERE username = ? AND password = ?";


    public UserDAO()
        throws SQLException, ClassNotFoundException
    {
        super();
    }

    public boolean addUser(User user) throws SQLException
    {
        // INSERT NEW USER IN THE DB {username, email, password}
        PreparedStatement ps = con.prepareStatement(QUERY_ADD_USER);
        ps.setString(   1,  user.getUsername()   );
        ps.setString(   2,  user.getEmail()      );
        ps.setString(   3,  user.getPassword()   );
        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }

    public boolean removeUser(User user) throws SQLException
    {
        // REMOVE USER FROM DB {username}
        PreparedStatement ps = con.prepareStatement(QUERY_REMOVE_USER);
        ps.setString(   1,  user.getUsername()   );
        ps.setString(   2,  user.getPassword()   );
        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }

    public boolean findUser(User user) throws SQLException
    {
        // CHECK IF A USER IS IN THE DB {username,password}
        PreparedStatement ps = con.prepareStatement(QUERY_FIND_USER);
        ps.setString(   1,  user.getUsername()   );
        ps.setString(   2,  user.getPassword()   );
        ResultSet rs = ps.executeQuery();
        boolean exists = rs.next();
        ps.close();
        rs.close();
        return exists;
    }

}


