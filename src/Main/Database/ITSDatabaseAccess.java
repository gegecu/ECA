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
        System.out.println("---------- Performing Database Operation ----------");
        System.out.println("Operation: Read database connection parameters from database configuration file");
        System.out.println("Status: Reading database configuration file");
        
        File dbConfigFile= new File(DB_CONFIG_FILE_PATH);
        
        try
        {
            Scanner scanner=new Scanner(dbConfigFile);
            
            if (scanner.hasNextLine())
            {
                String temp=scanner.nextLine();
                String[] split=temp.split(URL_DELIM);
                
                url=split[urlIndex];
                System.out.println("URL: " + url);
                
                if (scanner.hasNextLine())
                {
                    username=scanner.nextLine();
                    System.out.println("Username: " + username);
                    
                    if (scanner.hasNextLine())
                    {
                        password=scanner.nextLine();
                        System.out.println("Password: " + password);
                    }
                }
            }           
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("Error: DB.conf cannot be found in the specified file path");
            System.out.println(fnfe);
        }
        
        System.out.println("------------ End of Database Operation ------------\n\n");
    }
    
    public Connection connect()
    {
        if (!url.isEmpty() && !username.isEmpty() && !password.isEmpty())
        {
            System.out.println("Status: Initiating connection to database");
            try 
            {
                Class.forName(JDBC_FILE_PATH);
                connection = DriverManager.getConnection(url, username, password);

                System.out.println("Status: Successfully connected to the database!");

            } 
            catch (ClassNotFoundException cnfe) 
            {
                System.out.println("Error: JDBC can't be found");
                System.out.println(cnfe);
            }
            catch (SQLException sqle) 
            {
                System.out.println("Error: Error with SQL");
                System.out.println(sqle);
            }
        }
        else
        {
            System.out.println("Error: Error with contents of DB.conf");
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
                System.out.println("Status: Successfully disconnected from the database!");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error: Error in disconnecting from the database");
            System.out.println(e);
            return false;
        }
    
        return true;
    }

}
