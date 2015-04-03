/*
 * Class: dao.ItemDAO
 * Luettelo
 *
 * 2015-04-03
 */

package dao;

/**
 *
 * @author Lucia
 */
public class ItemDAO
{
    private Connection con;

    static String USER = "root";
    static String PASSWD = "root";

    public ItemDAO()
        throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/luettelo", USER, PASSWD);
    }

    public void addItem(Item i)
    {
        
    }
    
    public void editItem(Item i)
    {
        
    }
    
    public void removeItem(Item i)
    {
        
    }
    
    public java.util.Collection getItems(dominio.List l, dominio.User u)
    {
        
    }

    public void close()
        throws SQLException, ClassNotFoundException
    {
        con.close();
    }
}
