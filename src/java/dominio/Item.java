/*
 * Class: dominio.Item
 * Luettelo
 * 
 * 2015-04-03
 */

package dominio;

/**
 *
 * @author Lucia
 */
public class Item 
{
    private int id;
    private String name;
    private String url;
    private Integer average;
    private Integer rating;

    public Item(int id)
    {
        this.id = id;
    }

    public Item(int id, String name, String url, Integer average, Integer rating)
    {
        this(id);
        this.name = name;
        this.url = url;
        this.average = average;
        this.rating = rating;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Item)
        {
            Item i = (Item) o;
            return id == i.getId();
        }
        else
        {
            return false;
        }
    }

}
