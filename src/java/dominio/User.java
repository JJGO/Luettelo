/*
 * Class: dominio.User
 * Luettelo
 * 
 * 2015-04-03
 */

package dominio;

/**
 *
 * @author Lucia
 */
public class User 
{
    private String username;
    private String email;
    private String password;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public User(String username, String email, String password)
    {
        this(username, password);
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o instanceof User)
        {
            User u = (User) o;
            return username.equals(u.getUsername());
        }
        else
        {
            return false;
        }
    }
}
