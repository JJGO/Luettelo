/*
 * Class: dominio.Comment
 * Luettelo
 *
 * 2015-04-03
 */

package dominio;

/**
 *
 * @author Lucia
 */
public class Comment
{
    private int id;
    private String username;
    private String content;

    //Used by RemoveComment.java
    public Comment(int id)
    {
        this.id = id;
    }

    //Used by AddComment.java
    public Comment(String content)
    {
        this.content = content;
    }

    //Used by EditComment.java
    public Comment(String content, int id)
    {
        this.content = content;
        this.id = id;
    }

    //Used by CommentDAO.java
    public Comment(int id, String username, String content)
    {
        this.content = content;
        this.username = username;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
 
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Comment)
        {
            Comment c = (Comment) o;
            return id == c.getId();
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return id+" "+username+" "+content;
    }

}
