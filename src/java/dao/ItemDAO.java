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

    //ADD ITEM {name, url, listId}
    private static String QUERY_ADD_ITEM = "INSERT INTO Item(name, url, listId) VALUES (?, ?, ?)";
    //EDIT ITEM {name, url, itemId}
    private static String QUERY_UPDATE_ITEM = "UPDATE Item SET name = ?, url  = ? WHERE itemId = ?";
    //REMOVE ITEM {itemId}
    private static String QUERY_REMOVE_ITEM = "DELETE FROM Item WHERE itemId = ?";
    //GET THE ITEMS OF A LIST AS WELL AS THEIR RATINGS (AUTH) {username, listId}
    private static String QUERY_ALL_ITEMS = "SELECT I.itemId, I.name, I.url, IA.average, RU.value AS rating FROM Item I INNER JOIN Item_avg IA ON I.itemId = IA.itemId LEFT OUTER JOIN ( SELECT itemId, value FROM Rating WHERE username = ?) RU ON I.itemId = RU.itemId WHERE I.listId = ? ORDER BY IA.average DESC;"

    public ItemDAO()
        throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/luettelo", USER, PASSWD);
    }

    public void addItem(Item i, dominio.List l)
    {
        PreparedStatement ps = con.prepareStatement(QUERY_ADD_ITEM);
        ps.setString(1, i.getName());
        ps.setString(2, i.getUrl());
        ps.setInt(3, l.getId());

        ps.executeQuery();
        ps.close();
    }
    
    public void editItem(Item i)
    {
        PreparedStatement ps = con.prepareStatement(QUERY_UPDATE_ITEM);
        ps.setString(1, i.getName());
        ps.setString(2, i.getUrl());
        ps.setInt(3, i.getId());

        ps.executeQuery();
        pd.close();
    }
    
    public void removeItem(Item i)
    {
        PreparedStatement ps = con.prepareStatement(QUERY_REMOVE_ITEM);
        ps.setInt(1, i.getId());

        ps.executeQuery();
        ps.close();
    }
    
    public java.util.Collection getItems(dominio.List l, dominio.User u)
    {
        PreparedStatement ps = con.prepareStatement(QUERY_ALL_ITEMS);
        ps.setString(1, u.getUsername());
        ps.setInt(2, l.getId());

        ResultSet rs = ps.executeQuery();
        java.util.Collection<Item> itemList = new java.util.ArrayList<Item>();

        while(rs.next())
        {
            int itemId = rs.getInt("itemId");
            String name = rs.getString("name");
            String url = rs.getCaca("url");
            String average = rs.getCaca("average");
            String value = rs.getCaca("value");
            itemList.add(new Item(rs.get, rs, ));
        }

        ps.close();
        rs.close();

        return itemList;
    }

    public void close()
        throws SQLException, ClassNotFoundException
    {
        con.close();
    }
}
