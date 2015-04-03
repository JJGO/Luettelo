/*
 * Class: dao.CommentDAO
 * Luettelo
 *
 * 2015-04-03
 */

package dao;

import dominio.Comment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lucia
 */
public class CommentDAO
{
    private Connection con;

    static String USER = "root";
    static String PASSWD = "root";

    public CommentDAO()
        throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/luettelo", USER, PASSWD);
    }

    public void addComment(Comment c)
    {

    }

    public void editComment(Comment c)
    {

    }

    public void removeComment(Comment c)
    {

    }

    public java.util.Collection getComments(dominio.List l)
    {
        
    }

    public void close()
        throws SQLException, ClassNotFoundException
    {
        con.close();
    }
}
