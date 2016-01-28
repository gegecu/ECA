/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.Components;

import Main.Reader.Reader;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author crimsiris
 */
public class Story {

    private String storyID;
    private String title;
    private String storyPath;
    private ArrayList<Page> pages;
    private String[] topicConcepts;
    
    private String iconPath;
    
    public Story (String storyID, String title, String storyPath, String[] topicConcepts, String iconPath)
    {
        this.storyID=storyID;
        this.title=title;
        this.storyPath=storyPath;
        this.topicConcepts=topicConcepts;
        this.iconPath=iconPath;
        
        readStoryFromPath();
    }
    
    private void readStoryFromPath()
    {
        pages=Reader.readStoryFile(storyPath);
    }
    
    public String getStoryID()
    {
        return storyID;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public ArrayList<Page> getPages()
    {
        return pages;
    }

           
    public String[] getTopicConcepts()
    {
        return topicConcepts;
    }
    
    public String getIconPath()
    {
        return iconPath;
    }
    

}
