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
    private String user;
    private String content;

    Comment(int id)
    {
        this.id = id;
    }

    Comment(int id, String user, String content)
    {
        this(id);
        this.user = user;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
