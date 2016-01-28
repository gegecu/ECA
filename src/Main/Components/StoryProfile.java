/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.Components;

/**
 *
 * @author crimsiris
 */
public class StoryProfile {
    
    private String storyID;
    private String title;
    private String author;
    private String summary;
    private String storyPath;
    private String iconPath;
    private String[] topicConcepts;
    private int readCount;
    private int rating;
    
    public StoryProfile (String storyID, String title, String author, String summary, String storyPath, String iconPath, String topicConcepts, int readCount, int rating)
    {
        this.storyID=storyID;
        this.title=title;
        this.author=author;
        this.summary=summary;
        this.storyPath=storyPath;
        this.iconPath=iconPath;
        this.topicConcepts=topicConcepts.split(",");
        this.readCount=readCount;
        this.rating=rating;
    }

    public String getStoryID() 
    {
        return storyID;
    }

    public String getTitle() 
    {
        return title;
    }

    public String getAuthor() 
    {
        return author;
    }

    public String getSummary() 
    {
        return summary;
    }
    
    public String getStoryPath()
    {
        return storyPath;
    }
    
    public String getIconPath()
    {
        return iconPath;
    }
    
    public String[] getTopicConcepts()
    {
        return topicConcepts;
    }

    public int getReadCount() 
    {
        return readCount;
    }

    public int getRating() 
    {
        return rating;
    }

}
