/*
 * Class: dao.ItemDAO
 * Luettelo
 *
 * 2015-04-03
 */

package dao;

import dominio.Item;
import dominio.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lucia
 */
public class ItemDAO extends DAO
{

//DML
    //ADD ITEM {name, url, listId, listId, username}
    private static String ADD_ITEM = "INSERT INTO Item(name, url, listId) SELECT ?, ?, ? FROM List WHERE listId = ? and username = ?";

    //EDIT ITEM {name, url, itemId, username}
    private static String UPDATE_ITEM = "UPDATE Item SET name = ?, url  = ? WHERE   itemId = ? AND listId IN ( SELECT listId FROM List WHERE username = ?)";

    //REMOVE ITEM {itemId, username}
    private static String REMOVE_ITEM = "DELETE FROM Item WHERE   itemId = ? AND listId IN ( SELECT listId FROM List WHERE username = ?)";
 
    //CHECK IN AN ITEM {username, itemId, username, itemId}
    private static String CHECK_ITEM = "INSERT INTO Rating(username,itemId)  SELECT ?, ?  FROM Item I  INNER JOIN Subscription S  ON I.listId = S.listId  WHERE S.username = ? and I.itemId = ?";

    //UNCHECK AN ELEMENT {username, itemId}
    private static String UNCHECK_ITEM = "DELETE FROM Rating WHERE username = ? AND itemId = ?";

    //(RE)RATE AN ELEMENT {value, username, itemId}
    private static String RATE_ITEM = "UPDATE Rating SET value = ? WHERE username = ? AND itemId = ?";

//QUERY
    //GET THE ITEMS OF A LIST AS WELL AS THEIR RATINGS (AUTH) {username, listId}
    private static String QUERY_ALL_ITEMS = "SELECT I.itemId, I.name, I.url, IA.average, RU.value AS rating FROM Item I INNER JOIN Item_avg IA ON I.itemId = IA.itemId LEFT OUTER JOIN ( SELECT itemId, value FROM Rating WHERE username = ?) RU ON I.itemId = RU.itemId WHERE I.listId = ? ORDER BY IA.average DESC;";

    public ItemDAO()
        throws SQLException, ClassNotFoundException
    {
        super();
    }

    public boolean addItem(Item item, dominio.List list, User user) throws SQLException
    {
        //ADD ITEM {name, url, listId, listId, username}
        PreparedStatement ps = con.prepareStatement(ADD_ITEM);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1, item.getName()       );
        ps.setString(   2, item.getUrl()        );
        ps.setInt(      3, list.getId()         );
        ps.setInt(      4, list.getId()         );
        ps.setString(   5, username   );

        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }
    
    public boolean editItem(Item item, User user) throws SQLException
    {
        //EDIT ITEM {name, url, itemId, username}
        PreparedStatement ps = con.prepareStatement(UPDATE_ITEM);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1, item.getName()       );
        ps.setString(   2, item.getUrl()        );
        ps.setInt(      3, item.getId()         );
        ps.setString(   4, username   );

        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }
    
    public boolean removeItem(Item item, User user) throws SQLException
    {
        //REMOVE ITEM {itemId, username}
        PreparedStatement ps = con.prepareStatement(REMOVE_ITEM);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setInt(      1, item.getId()         );
        ps.setString(   2, username   );

        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }

    public boolean checkItem(Item item, User user) throws SQLException
    {
        //CHECK IN AN ITEM {username, itemId, username, itemId}
        PreparedStatement ps = con.prepareStatement(CHECK_ITEM);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1, username   );
        ps.setInt(      2, item.getId()         );
        ps.setString(   3, username   );
        ps.setInt(      4, item.getId()         );

        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }

    public boolean uncheckItem(Item item, User user) throws SQLException
    {
        //UNCHECK AN ELEMENT {username, itemId}
        PreparedStatement ps = con.prepareStatement(UNCHECK_ITEM);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(   1, username   );
        ps.setInt(      2, item.getId()         );

        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }

    public boolean rateItem(Item item, User user) throws SQLException
    {
        //(RE)RATE AN ELEMENT {value, username, itemId}
        PreparedStatement ps = con.prepareStatement(RATE_ITEM);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setInt(      3, item.getRating()     );
        ps.setString(   2, username   );
        ps.setInt(      3, item.getId()         );

        int rows = ps.executeUpdate();
        ps.close();
        return (rows != 0);
    }
    
    public java.util.ArrayList<Item> getItems(dominio.List list, dominio.User user) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement(QUERY_ALL_ITEMS);
        String username = null;
        if (user != null){
            username = user.getUsername();
        }
        ps.setString(1, username);
        ps.setInt(2, list.getId());

        ResultSet rs = ps.executeQuery();
        java.util.ArrayList<Item> itemList = new java.util.ArrayList<Item>();

        while(rs.next())
        {
            int itemId      = rs.getInt("itemId");
            String name     = rs.getString("name");
            String url      = rs.getString("url");
            Integer average = rs.getInt("average"); //average rating
            Integer value   = rs.getInt("value");     //user rating

            itemList.add(new Item(itemId, name, url, average, value));
        }

        ps.close();
        rs.close();

        return itemList;
    }
}
