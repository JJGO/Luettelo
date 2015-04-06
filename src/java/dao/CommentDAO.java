/*
 * Class: dao.CommentDAO
 * Luettelo
 *
 * 2015-04-03
 */

package dao;

import dominio.Comment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lucia
 */
public class CommentDAO extends DAO
{

//DML
    //ADD COMMENT {content, username, listId}
    private static String ADD_COMMENT = ("INSERT INTO Comment(content, username, listId) VALUES (?, ?, ?)");

    //EDIT COMMENT {content, commentId}
    private static String UPDATE_COMMENT = ("UPDATE Comment SET content = ? WHERE commentId = ? AND username = ?");

    //REMOVE COMMENT {commentId}
    private static String REMOVE_COMMENT = ("DELETE FROM Comment WHERE commentId = ? AND username = ?");

//QUERY
    //GET THE COMMENTS OF A LIST {listId}
    private static String QUERY_ALL_COMMENTS = ("SELECT commentId, username, content FROM Comment WHERE listId = ?");

    public CommentDAO()
        throws SQLException, ClassNotFoundException
    {
        super();
    }

    public boolean addComment(Comment comment, dominio.List list, dominio.User user) throws SQLException
    {
        //ADD COMMENT {content, username, listId}
        PreparedStatement ps = con.prepareStatement(ADD_COMMENT);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1, comment.getContent() );
        ps.setString(   2, username   );
        ps.setInt(      3, list.getId()         );

        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }

    public boolean editComment(Comment comment, dominio.User user) throws SQLException
    {
        //EDIT COMMENT {content, commentId}
        PreparedStatement ps = con.prepareStatement(UPDATE_COMMENT);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1, comment.getContent() );
        ps.setInt(      2, comment.getId()      );
        ps.setString(   3, username   );

        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }

    public boolean removeComment(Comment comment, dominio.User user) throws SQLException
    {
        //REMOVE COMMENT {commentId}
        PreparedStatement ps = con.prepareStatement(REMOVE_COMMENT);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setInt(      1, comment.getId()      );
        ps.setString(   2, username   );

        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }

    public java.util.ArrayList<Comment> getComments(dominio.List list) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement(QUERY_ALL_COMMENTS);
        ps.setInt(1, list.getId());

        ResultSet rs = ps.executeQuery();
        java.util.ArrayList<Comment> commentList = new java.util.ArrayList<Comment>();

        while(rs.next())
        {
            int commentId = rs.getInt("commentId");
            String username = rs.getString("username");
            String content = rs.getString("content");
            Comment comment = new Comment(commentId, username, content);
            commentList.add(comment);
            //System.out.println(comment);
        }

        ps.close();
        rs.close();

        return commentList;
    }
}
