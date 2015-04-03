/*
 * Class: dominio.List
 * Luettelo
 * 
 * 2015-04-03
 */

package dominio;

/**
 *
 * @author Lucia
 */
public class List 
{
    private int id;
    private String name;
    private String category;
    private String description;
    private String user;        //person who created the list
    private Integer average;    //avg rating of the list
    private Integer comments;   //number of comments in the list
    private boolean subscribed;
    
    public List(int id)
    {
        this.id = id;
    }
    
    public List(int id, String name, Integer average)
    {
        this(id);
        this.name = name;
        this.average = average;
    }

    public List(int id, String name, String category, String user, Integer average, Integer comments, boolean subscribed)
    {
        this(id, name, average);
        this.category = category;
        this.user = user;
        this.comments = comments;
        this.subscribed = subscribed;
    }

    public List(int id, String name, String category, String description, String user, Integer average, Integer comments, boolean subscribed)
    {
        this(id, name, category, user, average, comments, subscribed);
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof List)
        {
            List l = (List) o;
            return id == l.getId();
        }
        else
        {
            return false;
        }
    }
}
