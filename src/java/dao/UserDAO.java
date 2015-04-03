/*
 * Class: dao.UserDAO
 * Luettelo
 * 
 * 2015-04-03
 */

package dao;

/**
 *
 * @author Lucia
 */
public class UserDAO 
{
    private Connection con;

    static String USER = "root";
    static String PASSWD = "root";

    public UserDAO()
        throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/pat", USER, PASSWD);
    }

    public void addUser(User u) 
    {

    }

    public void removeUser(User u) 
    {

    }

    public boolean findUser(User u) 
    {

    }

    public void close()
        throws SQLException, ClassNotFoundException
    {
        con.close();
    }
}
