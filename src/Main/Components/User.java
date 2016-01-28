/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.Components;

/**
 *
 * @author crimsiris
 */
public class User {
    
    private String userID;
    private String name;
    private char gender;
    private int age;
    private String avatarPath;
    
    public User(String userID, String username, char gender, int age, String avatarPath)
    {
        this.userID=userID;
        this.name=username;
        this.gender=gender;
        this.age=age;
        this.avatarPath=avatarPath;
    }
    
    public String getUserID()
    {
        return userID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public char getGender()
    {
        return gender;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public String getAvatarPath()
    {
        return avatarPath;
    }

}
