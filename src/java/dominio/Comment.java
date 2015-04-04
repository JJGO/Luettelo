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
    private String content;
    private String username;

    public Comment(int id)
    {
        this.id = id;
    }

    public Comment(String content, String username)
    {
        this.content = content;
        this.username = username;
    }

    public Comment(String content, int id)
    {
        this.content = content;
        this.id = id;
    }

    public Comment(int id, String content, String username)
    {
        this(content, username);
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

}
