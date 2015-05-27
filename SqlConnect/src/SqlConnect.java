import java.sql.*;
import java.util.*;


public class SqlConnect {
	static Connection conn=null;
	static Scanner input = new Scanner(System.in);
	
	public static void ordersGivenID() throws SQLException {
		  System.out.println("Choose an account ID");
		  int accountChoice = input.nextInt();
		  
		  int space;
		  
	      String sql = "select * from orders where orders.AccountId=" + accountChoice;
	      
	      conn = DriverManager.getConnection("jdbc:mysql://localhost/productorders","root","christian123");
	      ResultSet rs =  conn.createStatement().executeQuery(sql);
	      
	      System.out.println("OrderID  AccountID  DateAndTime  ShippingAddressID  BillingAddressID");
	      
	      while(rs.next()) {
		      int orderID = rs.getInt("orderID");
		      int accID = rs.getInt("accountID");
		      String dateAndTime = rs.getString("DateAndTime");
		      String shipAddress = rs.getString("ShippingAddressID");
		      String billingAddress = rs.getString("BillingAddressID");
		      
		      System.out.print(orderID);
		      
		      space=9-Integer.toString(orderID).length();
		      
		      while(space>0) {
		    	  System.out.print(" ");
		    	  space--;
		      }
		      
		      System.out.print(accID);
		      
		      space=11-Integer.toString(accID).length();
		      
		      while(space>0) {
		    	  System.out.print(" ");
		    	  space--;
		      }
		      
		      System.out.print(dateAndTime);
		      
		      space=13-dateAndTime.length();
		      
		      while(space>0) {
		    	  System.out.print(" ");
		    	  space--;
		      }
		      
		      System.out.print(shipAddress);
		      
		      space=19-shipAddress.length();
		      
		      while(space>0) {
		    	  System.out.print(" ");
		    	  space--;
		      }
		      
		      System.out.println(billingAddress);
		  }
	}
	
	public static void productsGivenOrder() throws SQLException {
		  System.out.println("Choose an order ID");
		  int orderChoice = input.nextInt();
		   
	      String sql = "select * from products natural join orderItems where orderID=" + orderChoice;
	      
	      conn = DriverManager.getConnection("jdbc:mysql://localhost/productorders","root","christian123");
	      ResultSet rs =  conn.createStatement().executeQuery(sql);
	      
	      System.out.println("ProductID  ProductName          Description                        Price    OrderID  Quantity");
	      
	      while(rs.next()) {
		      int productID = rs.getInt("ProductID");
		      String ProductName = rs.getString("ProductName");
		      String Description = rs.getString("Description");
		      Double Price = rs.getDouble("Price");
		      int OrderID = rs.getInt("OrderID");
		      int Quantity = rs.getInt("Quantity");
		      int space;
		      
		      System.out.print(productID);
		      
		      space=11-Integer.toString(productID).length();
		      
		      while(space>0) {
		    	  System.out.print(" ");
		    	  space--;
		      }
		      
		      System.out.print(ProductName);
		     
		      space=21-ProductName.length();
		      
		      while(space>0) {
		    	  System.out.print(" ");
		    	  space--;
		      }
		      
		      System.out.print(Description);
		      
		      space=35-Description.length();
		      
		      while(space>0) {
		    	  System.out.print(" ");
		    	  space--;
		      }
		      
		      System.out.print(Price);
		      
		      space=9-Double.toString(Price).length();
		      
		      while(space>0) {
		    	  System.out.print(" ");
		    	  space--;
		      }
		      
		      System.out.print(OrderID);
		      
		      space=9-Integer.toString(OrderID).length();
		      
		      while(space>0) {
		    	  System.out.print(" ");
		    	  space--;
		      }
		      
		      System.out.println(Quantity);
	      }
	}
	
	public static void accountsGivenProdID() throws SQLException
	{
		  System.out.println("Choose an product ID");
		  int productChoice = input.nextInt();
		  
	      String sql = "select distinct AccountID,F_Name,L_Name,DOB from person natural join orders natural join orderItems natural join products where productID=" 
	    		  		+ productChoice;
	      
	      conn = DriverManager.getConnection("jdbc:mysql://localhost/productorders","root","christian123");
	      ResultSet rs =  conn.createStatement().executeQuery(sql);
	      
	      System.out.println("AccountID  First Name       Last Name      DOB");
	      
	      while(rs.next()) {
	    	  int space;
	    	  int AccountID = rs.getInt("AccountID");
	    	  String F_Name = rs.getString("F_Name");
	    	  String L_Name = rs.getString("L_Name");
	    	  String DOB = rs.getString("DOB");
	    	  
		      System.out.print(AccountID);

		      space=11-Integer.toString(AccountID).length();
		      
		      while(space>0) {
		    	  System.out.print(" ");
		    	  space--;
		      }
	    	  
		      System.out.print(F_Name);

		      space=17-F_Name.length();
		      
		      while(space>0) {
		    	  System.out.print(" ");
		    	  space--;
		      }
	    	  
		      System.out.print(L_Name);
		      
		      
		      space=15-L_Name.length();
		      
		      while(space>0) {
		    	  System.out.print(" ");
		    	  space--;
		      }
	    	  
		      System.out.println(DOB);
	      }
	}
	
	public static void subtotalGivenOrderID() throws SQLException
	{
		  System.out.println("Choose an order ID");
		  int orderChoice = input.nextInt();
		  String sql;
	      
		  sql = "select sum(total) as total from (select *,quantity*price as total from OrderItems natural join products where OrderID=" 
	    		 + orderChoice + ") as orderSummary";
	      
	      conn = DriverManager.getConnection("jdbc:mysql://localhost/productorders","root","christian123");
	      ResultSet rs =  conn.createStatement().executeQuery(sql);
	      
	      System.out.println("Subtotal");
	      
	      while(rs.next()) {
	    	  Double subtotal = rs.getDouble("total");

		      System.out.print(subtotal);
	      }
	}
	
	public static void main(String [] args) {
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		 
			  System.out.println("Enter 1 to get an array of orders given account ID");
			  System.out.println("Enter 2 to get an array of products given order ID");	
			  System.out.println("Enter 3 to get an array of accounts given product ID");	
			  System.out.println("Enter 4 to get a subtotal given order ID");
			  int choice = input.nextInt();
			  
			  switch(choice)
			  {
			  	case 1: ordersGivenID();break;
			  	case 2: productsGivenOrder();break;
			  	case 3: accountsGivenProdID();break;
			  	case 4: subtotalGivenOrderID();break;
			  }	
		}
		catch(Exception x) {
		    System.out.println("Error");
		}
	}
}