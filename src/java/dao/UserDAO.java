/*
 * Class: dao.UserDAO
 * Luettelo
 *
 * 2015-04-03
 */

package dao;

/**
 *
 * @author JJ
 */
public class UserDAO
{
    private Connection con;

    static String USER = "root";
    static String PASSWD = "root";

    //INSERT NEW USER IN THE DB {username, email, password}
    private static String QUERY_ADD_USER = "INSERT INTO User (username, email, password) VALUES (?,?,?)";

    //REMOVE USER FROM DB {username, password}
    private static String QUERY_REMOVE_USER = "DELETE FROM User WHERE username = ? AND password = ?";

    // CHECK IF A USER IS IN THE DB {username,password}
    private static String QUERY_FIND_USER = "SELECT username FROM User WHERE username = ? AND password = ?";


    public UserDAO()
        throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/luettelo", USER, PASSWD);
    }

    public void addUser(User u) throws SQLException
    {
        //INSERT NEW USER IN THE DB {username, email, password}
        PreparedStatement ps = con.prepareStatement(QUERY_ADD_USER);
        ps.setString(   1,  user.getUsername()   );
        ps.setString(   2,  user.getEmail()      );
        ps.setString(   3,  user.getPassword()   );
        ps.executeQuery();

        ps.close();
    }

    public boolean removeUser(User u) throws SQLException
    {
        //REMOVE USER FROM DB {username}
        PreparedStatement ps = con.prepareStatement(QUERY_REMOVE_USER);
        ps.setString(   1,  user.getUsername()   );
        ps.setString(   2,  user.getPassword()   );
        int rows = ps.executeUpdate();
        ps.close();
        if(rows == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
        
        
    }

    public boolean findUser(User u) throws SQLException
    {
        // CHECK IF A USER IS IN THE DB {username,password}
        PreparedStatement ps = con.prepareStatement(QUERY_FIND_USER);
        ps.setString(   1,  user.getUsername()   );
        ps.setString(   2,  user.getPassword()   );
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return true;
        else
            return false;
    }

    public void close()
        throws SQLException, ClassNotFoundException
    {
        con.close();
    }
}


