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
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author JJ
 */
public class DAO
{
    protected Connection con;

    public DAO(HttpServletRequest request)
        throws SQLException, ClassNotFoundException
    {
        String user     = request.getServletContext().getInitParameter("USER");
        String passwd   = request.getServletContext().getInitParameter("PASSWD");
        String db       = request.getServletContext().getInitParameter("DB");

        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/"+db, user, passwd);
    }

    public void close()
        throws SQLException, ClassNotFoundException
    {
        con.close();
    }
}


