/*
 * Class: dao.ItemDAO
 * Luettelo
 *
 * 2015-04-03
 */

package dao;

import dominio.Item;
import java.sql.Connection;
import java.sql.DriverManager;
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
    //ADD ITEM {name, url, listId}
    private static String QUERY_ADD_ITEM = "INSERT INTO Item(name, url, listId) VALUES (?, ?, ?)";
    
    //EDIT ITEM {name, url, itemId}
    private static String QUERY_UPDATE_ITEM = "UPDATE Item SET name = ?, url  = ? WHERE itemId = ?";
    
    //REMOVE ITEM {itemId}
    private static String QUERY_REMOVE_ITEM = "DELETE FROM Item WHERE itemId = ?";

//QUERY
    //GET THE ITEMS OF A LIST AS WELL AS THEIR RATINGS (AUTH) {username, listId}
    private static String QUERY_ALL_ITEMS = "SELECT I.itemId, I.name, I.url, IA.average, RU.value AS rating FROM Item I INNER JOIN Item_avg IA ON I.itemId = IA.itemId LEFT OUTER JOIN ( SELECT itemId, value FROM Rating WHERE username = ?) RU ON I.itemId = RU.itemId WHERE I.listId = ? ORDER BY IA.average DESC;";

    public ItemDAO()
        throws SQLException, ClassNotFoundException
    {
        super();
    }

    public void addItem(Item item, dominio.List list) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement(QUERY_ADD_ITEM);
        ps.setString(1, item.getName());
        ps.setString(2, item.getUrl());
        ps.setInt(3, list.getId());

        ps.executeQuery();
        ps.close();
    }
    
    public void editItem(Item item) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement(QUERY_UPDATE_ITEM);
        ps.setString(1, item.getName());
        ps.setString(2, item.getUrl());
        ps.setInt(3, item.getId());

        ps.executeQuery();
        ps.close();
    }
    
    public void removeItem(Item item) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement(QUERY_REMOVE_ITEM);
        ps.setInt(1, item.getId());

        ps.executeQuery();
        ps.close();
    }
    
    public java.util.ArrayList<Item> getItems(dominio.List list, dominio.User user) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement(QUERY_ALL_ITEMS);
        ps.setString(1, user.getUsername());
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
