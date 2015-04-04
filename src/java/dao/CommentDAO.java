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
    private static String QUERY_ADD_COMMENT = ("INSERT INTO Comment(content, username, listId) VALUES (?, ?, ?)");

    //EDIT COMMENT {content, commentId}
    private static String QUERY_UPDATE_COMMENT = ("UPDATE Comment SET content = ? WHERE commentId = ?");

    //REMOVE COMMENT {commentId}
    private static String QUERY_REMOVE_COMMENT = ("DELETE FROM Comment WHERE commentId = ?");

//QUERY
    //GET THE COMMENTS OF A LIST {listId}
    private static String QUERY_ALL_COMMENTS = ("SELECT commentId, username, content FROM Comment WHERE listId = ?");

    public CommentDAO()
        throws SQLException, ClassNotFoundException
    {
        super();
    }

    public void addComment(Comment comment, dominio.List list) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement(QUERY_ADD_COMMENT);
        ps.setString(1, comment.getContent());
        ps.setString(2, comment.getUsername());
        ps.setInt(3, list.getId());

        ps.executeQuery();
        ps.close();
    }

    public void editComment(Comment comment) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement(QUERY_UPDATE_COMMENT);
        ps.setString(1, comment.getContent());
        ps.setInt(2, comment.getId());

        ps.executeQuery();
        ps.close();
    }

    public void removeComment(Comment comment) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement(QUERY_REMOVE_COMMENT);
        ps.setInt(1, comment.getId());

        ps.executeQuery();
        ps.close();
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

            commentList.add(new Comment(commentId, username, content));
        }

        ps.close();
        rs.close();

        return commentList;
    }
}
