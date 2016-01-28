/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.Database;

import Main.Components.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author crimsiris
 */
public class UserInformationDatabase {
    
    private ITSDatabaseAccess dbAccess;
    private final int URL_INDEX=0;
    
    private Connection connection=null;
    
    private final String userIDPrefix="USER";
    
    public UserInformationDatabase()
    { 
        dbAccess=new ITSDatabaseAccess(URL_INDEX);
    }
    
    public User getUser(String name)
    {
        System.out.println("\n---------- Performing Database Operation ----------");
        System.out.println("Operation: Retrieve user " + name);
        
        String query= "SELECT * FROM Users WHERE Name=?";
        System.out.println("Query: " + query);

        User user=null;
        
        try
        {
            connection=dbAccess.connect();
            if (connection!=null)
            {
                PreparedStatement ps=connection.prepareStatement(query);
                ps.setString(1, name);
                
                ResultSet rs=ps.executeQuery();
                
                System.out.println("User: ");
                
                while (rs.next())
                {
                    String avatarID=rs.getString("AvatarID");
                    String avatarPath=new ImageLibraryDatabase().getAvatar(avatarID);
                    
                    user=new User(rs.getString("UserID"), rs.getString("Name"), rs.getString("Gender").charAt(0), rs.getInt("Age"), avatarPath);
                    System.out.println(" - " + rs.getString("Name") + "(" + rs.getInt("Age")+ ")");
                }
            }
            else
                System.out.println("Error: Cannot establish connection to the database");
        }
        catch (SQLException sqle) 
        {
            System.out.println("Error: Error with SQL");
            System.out.println(sqle);
            
            user=null;
        }
        finally
        {
            dbAccess.disconnect();
        }
        
        System.out.println("------------ End of Database Operation ------------\n");
        
        return user;
    }
    
    public ArrayList<User> getAllUsers()
    {
        System.out.println("\n---------- Performing Database Operation ----------");
        System.out.println("Operation: Retrieve all registered users");
        
        String query= "SELECT * FROM Users";
        System.out.println("Query: " + query);

        ArrayList<User> allUsers=new ArrayList<User>();
        
        try
        {
            connection=dbAccess.connect();
            if (connection!=null)
            {
                PreparedStatement ps=connection.prepareStatement(query);
                
                ResultSet rs=ps.executeQuery();
                
                System.out.println("Users: ");
                
                while (rs.next())
                {
                    String avatarID=rs.getString("AvatarID");
                    String avatarPath=new ImageLibraryDatabase().getAvatar(avatarID);
                    
                    allUsers.add(new User(rs.getString("UserID"), rs.getString("Name"), rs.getString("Gender").charAt(0), rs.getInt("Age"), avatarPath));
                    System.out.println(" - " + rs.getString("Name") + "(" + rs.getInt("Age")+ ")");
                }
            }
            else
                System.out.println("Error: Cannot establish connection to the database");
        }
        catch (SQLException sqle) 
        {
            System.out.println("Error: Error with SQL");
            System.out.println(sqle);
            
            allUsers=null;
        }
        finally
        {
            dbAccess.disconnect();
        }
        
        System.out.println("------------ End of Database Operation ------------\n");
        
        return allUsers;
    }
    
    private int getTotalNumberOfUsers(Connection connection)
    {
        System.out.println("Status: Getting total number of users to generate new user ID");
        
        String query= "SELECT COUNT(UserID) FROM Users";
        System.out.println("Query: " + query);
        
        int totalUsersCount=-1;
        
        try
        {
            if (connection!=null)
            {
                PreparedStatement ps=connection.prepareStatement(query);
                ResultSet rs=ps.executeQuery();
                
                rs.next();
                totalUsersCount=rs.getInt(1);
            }
            else
            {
                System.out.println("Error: Cannot establish connection to the database");
                totalUsersCount=-1;
            }
        }
        catch (SQLException sqle) 
        {
            System.out.println("Error: Error with SQL");
            System.out.println(sqle);
            
            totalUsersCount=-1;
        }
        
        return totalUsersCount;
    }
    
    public boolean addNewUser(String name, int age, String avatarID)
    {
        System.out.println("\n---------- Performing Database Operation ----------");
        System.out.println("Operation: Adding user \'" + name + "\' (aged " + age + ") to database");
        
        String query= "INSERT INTO Users (UserID, Name, Age, AvatarID) VALUES (?,?,?,?)";
        System.out.println("Query: " + query.replace("?", name));
        
        boolean isSuccessful=true;
        
        try
        {
            connection=dbAccess.connect();
            if (connection!=null)
            {
                int userCount=getTotalNumberOfUsers(connection);
                
                if (userCount>=0)
                {
                    PreparedStatement ps=connection.prepareStatement(query);
                    
                    userCount++;
                    String formattedNumber=String.format("%04d", userCount);
                    
                    ps.setString(1, userIDPrefix+formattedNumber);
                    ps.setString(2, name);
                    ps.setInt(3, age);
                    ps.setString(4, avatarID);

                    int usersAdded=ps.executeUpdate();

                    System.out.println("Status: Successfully added " + usersAdded + " into the database");
                }
                else
                {
                    System.out.println("Error: Cannot add new user into the databse");
                    isSuccessful=false;
                }
            }
            else
            {
                System.out.println("Error: Cannot establish connection to the database");
                isSuccessful=false;
            }
        }
        catch (SQLException sqle) 
        {
            System.out.println("Error: Error with SQL");
            System.out.println(sqle);
            
            isSuccessful=false;
        }
        finally
        {
            dbAccess.disconnect();
        }
        
        System.out.println("------------ End of Database Operation ------------\n");
        
        return isSuccessful;
    }
    
    public User getAgent (char gender)
    {
        System.out.println("\n---------- Performing Database Operation ----------");
        System.out.println("Operation: Retrieve " + gender + " agent");//, aged " + age);
        
        String query= "SELECT * FROM Agents WHERE Gender=?";// AND Age=?";
        System.out.println("Query: " + query);

        User agent=null;
        
        try
        {
            connection=dbAccess.connect();
            if (connection!=null)
            {
                PreparedStatement ps=connection.prepareStatement(query);
                ps.setString(1, gender+"");
                
                ResultSet rs=ps.executeQuery();
                
                System.out.println("Agent: ");
                
                while (rs.next())
                {
                    String avatarID=rs.getString("AvatarID");
                    String avatarPath=new ImageLibraryDatabase().getAvatar(avatarID);
                    
                    agent=new User(rs.getString("AgentID"), rs.getString("Name"), rs.getString("Gender").charAt(0), rs.getInt("Age"), avatarPath);
                    System.out.println(" - " + rs.getString("Name") + "(" + rs.getInt("Age")+ ")");
                }
            }
            else
                System.out.println("Error: Cannot establish connection to the database");
        }
        catch (SQLException sqle) 
        {
            System.out.println("Error: Error with SQL");
            System.out.println(sqle);
            
            agent=null;
        }
        finally
        {
            dbAccess.disconnect();
        }
        
        System.out.println("------------ End of Database Operation ------------\n");
        
        return agent;
    }

}
