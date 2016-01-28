/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author crimsiris
 */
public class ImageLibraryDatabase {
    
    private ITSDatabaseAccess dbAccess;
    private final int URL_INDEX=1;
    
    private Connection connection=null;
    
    
    public ImageLibraryDatabase()
    { 
        dbAccess=new ITSDatabaseAccess(URL_INDEX);
    }

    public HashMap<String, String> getAllAvatars()
    {
        System.out.println("\n---------- Performing Database Operation ----------");
        System.out.println("Operation: Retrieve all user avatars");
        
        String query= "SELECT * from UserAvatars";
        System.out.println("Query: " + query);

        HashMap<String, String> allAvatars=new HashMap<String, String>();
        
        try
        {
            connection=dbAccess.connect();
            if (connection!=null)
            {
                PreparedStatement ps=connection.prepareStatement(query);
                
                ResultSet rs=ps.executeQuery();
                
                System.out.println("Avatars: ");
                
                while (rs.next())
                {
                    allAvatars.put(rs.getString("AvatarID"), rs.getString("Path"));
                    System.out.println(" - " + rs.getString("Path"));
                }
            }
            else
                System.out.println("Error: Cannot establish connection to the database");
        }
        catch (SQLException sqle) 
        {
            System.out.println("Error: Error with SQL");
            System.out.println(sqle);
            
            allAvatars=null;
        }
        finally
        {
            dbAccess.disconnect();
        }
        
        System.out.println("------------ End of Database Operation ------------\n");
        
        return allAvatars;
    }
    
    public String getAvatar(String avatarID)
    {
        System.out.println("\n---------- Performing Database Operation ----------");
        System.out.println("Operation: Retrieve user avatars with avatar ID \'" + avatarID + "\'");
        
        String query= "SELECT * from UserAvatars WHERE AvatarID=?";
        System.out.println("Query: " + query.replace("?", avatarID));

        String avatarPath=null;
        
        try
        {
            connection=dbAccess.connect();
            if (connection!=null)
            {
                PreparedStatement ps=connection.prepareStatement(query);
                ps.setString(1, avatarID);
                
                ResultSet rs=ps.executeQuery();
                
                System.out.println("Avatar: ");
                
                while (rs.next())
                {
                    avatarPath=rs.getString("Path");
                    System.out.println(" - " + avatarPath);
                }
            }
            else
                System.out.println("Error: Cannot establish connection to the database");
        }
        catch (SQLException sqle) 
        {
            System.out.println("Error: Error with SQL");
            System.out.println(sqle);
            
            avatarPath=null;
        }
        finally
        {
            dbAccess.disconnect();
        }
        
        System.out.println("------------ End of Database Operation ------------\n");
        
        return avatarPath;
    }
    
    public HashMap<String, String> getAllStoryIllustrations(String storyID)
    {
        System.out.println("\n---------- Performing Database Operation ----------");
        System.out.println("Operation: Retrieve all illustrations for story with story id \'" + storyID + "\'");
        
        String query= "SELECT * from StoryIllustrations WHERE StoryID=?";
        System.out.println("Query: " + query.replace("?", storyID));

        HashMap<String, String> allIllustrations=new HashMap<String, String>();
        
        try
        {
            connection=dbAccess.connect();
            if (connection!=null)
            {
                PreparedStatement ps=connection.prepareStatement(query);
                ps.setString(1, storyID);
                
                ResultSet rs=ps.executeQuery();
                
                System.out.println("Illustrations: ");
                
                while (rs.next())
                {
                    allIllustrations.put(rs.getString("ImageID"), rs.getString("Path"));
                    System.out.println(" - " + rs.getString("Path"));
                }
            }
            else
                System.out.println("Error: Cannot establish connection to the database");
        }
        catch (SQLException sqle) 
        {
            System.out.println("Error: Error with SQL");
            System.out.println(sqle);
            
            allIllustrations=null;
        }
        finally
        {
            dbAccess.disconnect();
        }
        
        System.out.println("------------ End of Database Operation ------------\n");
        
        return allIllustrations;
        
    }
}
