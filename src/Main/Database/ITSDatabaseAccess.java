/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author crimsiris
 */
public class ITSDatabaseAccess {
    
    private Connection connection=null;
    
    private final String DB_CONFIG_FILE_PATH="DB.conf";
    private final String JDBC_FILE_PATH="com.mysql.jdbc.Driver";
    
    private String url="";
    private String username="";
    private String password="";
    
    private final String URL_DELIM=";";
    
    public ITSDatabaseAccess(int urlIndex)
    {
        instantiateDBConnectionParameters(urlIndex);
    }
    
    private void instantiateDBConnectionParameters(int urlIndex)
    {
        if (Main.Debug.debugDatabase) System.out.println("---------- Performing Database Operation ----------");
        if (Main.Debug.debugDatabase) System.out.println("Operation: Read database connection parameters from database configuration file");
        if (Main.Debug.debugDatabase) System.out.println("Status: Reading database configuration file");
        
        File dbConfigFile= new File(DB_CONFIG_FILE_PATH);
        
        try
        {
            Scanner scanner=new Scanner(dbConfigFile);
            
            if (scanner.hasNextLine())
            {
                String temp=scanner.nextLine();
                String[] split=temp.split(URL_DELIM);
                
                url=split[urlIndex];
                if (Main.Debug.debugDatabase) System.out.println("URL: " + url);
                
                if (scanner.hasNextLine())
                {
                    username=scanner.nextLine();
                    if (Main.Debug.debugDatabase) System.out.println("Username: " + username);
                    
                    if (scanner.hasNextLine())
                    {
                        password=scanner.nextLine();
                        if (Main.Debug.debugDatabase) System.out.println("Password: " + password);
                    }
                }
            }           
        }
        catch (FileNotFoundException fnfe)
        {
            if (Main.Debug.debugDatabase) System.out.println("Error: DB.conf cannot be found in the specified file path");
            if (Main.Debug.debugDatabase) System.out.println(fnfe);
        }
        
        if (Main.Debug.debugDatabase) System.out.println("------------ End of Database Operation ------------\n\n");
    }
    
    public Connection connect()
    {
        if (!url.isEmpty() && !username.isEmpty() && !password.isEmpty())
        {
            if (Main.Debug.debugDatabase) System.out.println("Status: Initiating connection to database");
            try 
            {
                Class.forName(JDBC_FILE_PATH);
                connection = DriverManager.getConnection(url, username, password);

                if (Main.Debug.debugDatabase) System.out.println("Status: Successfully connected to the database!");

            } 
            catch (ClassNotFoundException cnfe) 
            {
                if (Main.Debug.debugDatabase) System.out.println("Error: JDBC can't be found");
                if (Main.Debug.debugDatabase) System.out.println(cnfe);
            }
            catch (SQLException sqle) 
            {
                if (Main.Debug.debugDatabase) System.out.println("Error: Error with SQL");
                if (Main.Debug.debugDatabase) System.out.println(sqle);
            }
        }
        else
        {
            if (Main.Debug.debugDatabase) System.out.println("Error: Error with contents of DB.conf");
        }
        
        return connection;
    }
    
    public boolean disconnect()
    {
        try
        {
            if (connection!=null)
            {
                connection.close();
                if (Main.Debug.debugDatabase) System.out.println("Status: Successfully disconnected from the database!");
            }
        }
        catch(Exception e)
        {
            if (Main.Debug.debugDatabase) System.out.println("Error: Error in disconnecting from the database");
            if (Main.Debug.debugDatabase) System.out.println(e);
            return false;
        }
    
        return true;
    }

}
