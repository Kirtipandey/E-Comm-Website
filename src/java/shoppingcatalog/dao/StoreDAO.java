/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shoppingcatalog.dao;
import shoppingcatalog.dto.OrderDTO;
import shoppingcatalog.dto.ItemDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.ItemInfoDTO;
public class StoreDAO {
   private static Statement st,st2;
   private static PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8,ps9,ps10,ps11;
  
    static
    {
        try
        {
            
            st=DBConnection.getConnection().createStatement();
            ps1=DBConnection.getConnection().prepareStatement("Select id,item_name from store_item where item_type=?");
            ps2=DBConnection.getConnection().prepareStatement("Select item_type, item_name,item_price, item_desc,item_image from store_item WHERE id =?");
            ps3=DBConnection.getConnection().prepareStatement("Insert into order_master values(?,?,?,?)");
            ps4=DBConnection.getConnection().prepareStatement("Insert into order_details values(?,?,?)");
            ps5=DBConnection.getConnection().prepareStatement("Select count(*) as count from order_master");
            ps6=DBConnection.getConnection().prepareStatement("Select order_id,order_amount,order_date from order_master where cust_name=?");
            ps7=DBConnection.getConnection().prepareStatement("Select item_name from store_item WHERE id =?");
            ps8=DBConnection.getConnection().prepareStatement("Select max(id) as count from store_item");
            ps9=DBConnection.getConnection().prepareStatement("Insert into store_item values(?,?,?,?,?,?)");
            ps10=DBConnection.getConnection().prepareStatement("update store_itme set ITEM_TYPE=?,item_name=?,item_price=?,item_desc=?,item_image=? where id=?");
            ps11=DBConnection.getConnection().prepareStatement("delete from store_item where id=?");
            st2=DBConnection.getConnection().createStatement();
        }
      catch(Exception ex)
        {
        System.out.println("Error In DB comm:"+ex);
        
        }
    }
public static List<String> getItemTypes()throws SQLException
{
    ArrayList<String> itemList=new ArrayList<String>();
    
        System.out.println("st is "+st);
        ResultSet rs=st.executeQuery("Select distinct item_type from store_item");
    while(rs.next())
    {
        itemList.add(rs.getString(1));
    }
     
        System.out.println("List is of "+itemList.size()+" items");
        return itemList;
   
} 
public static List<ItemInfoDTO> getItemsByType(String itemType)throws SQLException
{
   ArrayList<ItemInfoDTO> itemList=new ArrayList<ItemInfoDTO>();
    ps1.setString(1, itemType);
    System.out.println("Item type in Model is "+itemType);
    ResultSet rs=ps1.executeQuery();
    System.out.println("again Item type is "+itemType);
    while(rs.next())
    {
        ItemInfoDTO obj=new ItemInfoDTO();
        obj.setItemId(rs.getInt(1));
        obj.setItemName(rs.getString(2));
        System.out.println("Item  is "+obj.getItemId()+","+obj.getItemName());
        itemList.add(obj);
    }
    
        System.out.println("List is of "+itemList.size()+" items");
        return itemList;
   
}
public static ItemDTO getItemDetails(int itemId)throws SQLException
{
  ItemDTO obj=null;
    
    ps2.setInt(1, itemId);
    ResultSet rs=ps2.executeQuery();
    if(rs.next())
    {
       
        obj=new ItemDTO();
        obj.setItemId(itemId);
        obj.setItemType(rs.getString("item_type"));
        obj.setItemName(rs.getString("item_name"));
        obj.setItemPrice(rs.getDouble("item_price"));
        obj.setItemDesc(rs.getString("item_desc"));
        obj.setItemImage(rs.getString("item_image"));
        
    }
    
        return obj;
     
}

public static void addOrder(String custName,ArrayList<ItemDTO>itemList,double totalAmount)throws SQLException{
    
    ResultSet rs=ps5.executeQuery();
    rs.next();
    int lastId=rs.getInt(1);
    String nextId="ORD-00"+(lastId+1);
    ps3.setString(1, nextId);
    ps3.setString(2, custName);
    ps3.setDouble(3,totalAmount);
    java.util.Date today=new java.util.Date();
    long ms=today.getTime();
    java.sql.Date currDate=new java.sql.Date(ms);
    ps3.setDate(4, currDate);
    int ans1=ps3.executeUpdate();
    System.out.println("Record inserted in Order Master:"+ans1);
    for(ItemDTO item:itemList){
        ps4.setString(1, nextId);
        ps4.setString(2, item.getItemName());
        ps4.setDouble(3,item.getItemPrice());
        int ans2=ps4.executeUpdate();
        System.out.println("Record inserted in Order Details:"+ans2);
    }
}
public static ArrayList<OrderDTO> getOrdersByCustomer(String custName)throws SQLException{
ArrayList<OrderDTO> orderList=new ArrayList<OrderDTO>();
    
    ps6.setString(1, custName);
    
    ResultSet rs=ps6.executeQuery();
   
    while(rs.next())
    {
        OrderDTO obj=new OrderDTO();
        obj.setOrderId(rs.getString(1));
        obj.setOrderAmount(rs.getDouble(2));
        obj.setOrderDate(rs.getDate(3));
        //System.out.println("Item  is "+obj.getItemId()+","+obj.getItemName());
        orderList.add(obj);
    }
    
        System.out.println("List is of "+orderList.size()+" items");
        return orderList;
   
}
public static boolean addNewProduct(ItemDTO obj)throws SQLException
{
    ResultSet rs=ps8.executeQuery();
    rs.next();
    int lastId=rs.getInt(1);
    int nextId=lastId+1;
    ps9.setInt(1, nextId);
    ps9.setString(2, obj.getItemType());
    ps9.setString(3, obj.getItemName());
    ps9.setDouble(4, obj.getItemPrice());
    ps9.setString(5, obj.getItemDesc());
    ps9.setString(6, obj.getItemImage());
    int ans=ps9.executeUpdate();
    return ans==1;
}
public static ArrayList<Integer> getAllProductId()throws SQLException
{
   ArrayList<Integer> itemIdList=new ArrayList<Integer>();
   ResultSet rs=st2.executeQuery("Select id from store_item");
    while(rs.next())
    {
        itemIdList.add(rs.getInt(1));
    }
     
        System.out.println("List is of "+itemIdList.size()+" items");
        return itemIdList; 
}
public static boolean updateProduct(ItemDTO item)throws SQLException
{
   ps10.setString(1,item.getItemType());
   ps10.setString(2, item.getItemName());
   ps10.setDouble(3,item.getItemPrice());
   ps10.setString(4,item.getItemDesc());
   ps10.setString(5,item.getItemImage());
   ps10.setInt(6,item.getItemId());
   int ans=ps10.executeUpdate();
   return ans==1;
}
public static boolean deleteProduct(int id)throws SQLException
{
    ps11.setInt(1, id);
    int ans=ps11.executeUpdate();
   return ans==1;
}
}