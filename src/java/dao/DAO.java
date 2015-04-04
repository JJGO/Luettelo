/*
 * Class: dao.DAO
 * Luettelo
 *
 * 2015-04-03
 */

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JJ
 */
public class DAO
{
    protected Connection con;

    static String USER = "root";
    static String PASSWD = "root";

    public DAO()
        throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/luettelo", USER, PASSWD);
    }

    public void close()
        throws SQLException, ClassNotFoundException
    {
        con.close();
    }
}


