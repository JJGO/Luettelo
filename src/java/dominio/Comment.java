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

    public Comment(int id)
    {
        this.id = id;
    }

    public Comment(int id, String username, String content)
    {
        this(id);
        this.username = username;
        this.content = content;
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
