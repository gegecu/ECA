/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.Database;

import Main.Components.Question;
import Main.Components.Story;
import Main.Components.StoryProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author crimsiris
 */
public class StoryLibraryDatabase {
    
    private ITSDatabaseAccess dbAccess;
    private final int URL_INDEX=2;
    
    private Connection connection=null;
    
    
    public StoryLibraryDatabase()
    { 
        dbAccess=new ITSDatabaseAccess(URL_INDEX);
    }
    
    public ArrayList<StoryProfile> getAllStoryProfiles()
    {
        if (Main.Debug.debugDatabase) System.out.println("\n---------- Performing Database Operation ----------");
        if (Main.Debug.debugDatabase) System.out.println("Operation: Retrieve all story profiles");
        
        String query= "SELECT * FROM StoryProfiles";
        if (Main.Debug.debugDatabase) System.out.println("Query: " + query);

        ArrayList<StoryProfile> allStoryProfiles=new ArrayList<StoryProfile>();
        
        try
        {
            connection=dbAccess.connect();
            if (connection!=null)
            {
                PreparedStatement ps=connection.prepareStatement(query);
                
                ResultSet rs=ps.executeQuery();
                
                if (Main.Debug.debugDatabase) System.out.println("Story profiles: ");
                
                while (rs.next())
                {
                    StoryProfile story=new StoryProfile(rs.getString("StoryID"), rs.getString("Title"), 
                            rs.getString("Author"), rs.getString("Summary"), rs.getString("StoryPath"), 
                            rs.getString("IconPath"), rs.getString("TopicConcepts"), rs.getInt("ReadCount"), 
                            rs.getInt("Rating"));
                    
                    allStoryProfiles.add(story);
                    
                    if (Main.Debug.debugDatabase) System.out.println(" - " + rs.getString("Title") + " - " + rs.getString("Author"));

                }
            }
            else
                if (Main.Debug.debugDatabase) System.out.println("Error: Cannot establish connection to the database");
        }
        catch (SQLException sqle) 
        {
            if (Main.Debug.debugDatabase) System.out.println("Error: Error with SQL");
            if (Main.Debug.debugDatabase) System.out.println(sqle);
            
            allStoryProfiles=null;
        }
        finally
        {
            dbAccess.disconnect();
        }
        
        if (Main.Debug.debugDatabase) System.out.println("------------ End of Database Operation ------------\n");
        
        return allStoryProfiles;
    }
    
    public void addReadCountOfStory(String storyID)
    {
        if (Main.Debug.debugDatabase) System.out.println("\n---------- Performing Database Operation ----------");
        if (Main.Debug.debugDatabase) System.out.println("Operation: Add read count of story with story ID \'" + storyID + "\'");
        
        String query= "UPDATE StoryProfiles SET ReadCount=ReadCount+1 WHERE StoryID=?";
        if (Main.Debug.debugDatabase) System.out.println("Query: " + query.replace("?", storyID));
        
        try
        {
            connection=dbAccess.connect();
            if (connection!=null)
            {
                PreparedStatement ps=connection.prepareStatement(query);
                ps.setString(1, storyID);
                ps.executeUpdate();
                
                if (Main.Debug.debugDatabase) System.out.println("Status: Updated read count of story with story ID \'" + storyID + "\'");
                
            }
            else
                if (Main.Debug.debugDatabase) System.out.println("Error: Cannot establish connection to the database");
        }
        catch (SQLException sqle) 
        {
            if (Main.Debug.debugDatabase) System.out.println("Error: Error with SQL");
            if (Main.Debug.debugDatabase) System.out.println(sqle);
        }
        finally
        {
            dbAccess.disconnect();
        }
        
        if (Main.Debug.debugDatabase) System.out.println("------------ End of Database Operation ------------\n");
    }
    
    public ArrayList<Question> getQuestions(String storyID)
    {
        if (Main.Debug.debugDatabase) System.out.println("\n---------- Performing Database Operation ----------");
        if (Main.Debug.debugDatabase) System.out.println("Operation: Retrieve all questions for story with story ID \'" + storyID + "\'");
        
//        String query= "SELECT * FROM Questions WHERE StoryID=?";
        String query = "SELECT * FROM Questions q, Choices c "
                        + "WHERE q.StoryID=? AND q.QuestionID = c.QuestionID "
                        + "ORDER BY q.QuestionID";
        if (Main.Debug.debugDatabase) System.out.println("Query: " + query.replace("?", storyID));

        ArrayList<Question> allQuestions=new ArrayList<Question>();
        
        try
        {
            connection=dbAccess.connect();
            if (connection!=null)
            {
                PreparedStatement ps=connection.prepareStatement(query);
                ps.setString(1, storyID);
                
                ResultSet rs=ps.executeQuery();
                
                String currentQuestionID="";
                String currentQuestion="";               
                ArrayList<String> choices=new ArrayList<String>();
                String correctAnswer="";
                
                if (Main.Debug.debugDatabase) System.out.println("Questions: ");
                
                while (rs.next())
                {
                    if (currentQuestionID.isEmpty())
                    {
                        currentQuestionID=rs.getString("QuestionID");
                    }
                    
                    if (Main.Debug.debugDatabase) System.out.println("Current question: " + currentQuestionID);
                    if (Main.Debug.debugDatabase) System.out.println("Incoming question: " + rs.getString("QuestionID"));
                    if (!currentQuestionID.equalsIgnoreCase(rs.getString("QuestionID")))
                    {
                        Question question=new Question(currentQuestionID, currentQuestion, choices, correctAnswer);
                        allQuestions.add(question);
                        
                        currentQuestionID=rs.getString("QuestionID");
                        choices=new ArrayList<String>();
                        correctAnswer="";
                        if (Main.Debug.debugDatabase) System.out.println("New current question: " + rs.getString("QuestionID"));
                        
                    }
                    
                    if (Main.Debug.debugDatabase) System.out.print("Choice: " + rs.getString("Choice"));
                    
                    currentQuestion=rs.getString("Question");
                    choices.add(rs.getString("Choice"));

                    if (rs.getInt("IsCorrect")==1)
                    {
                        correctAnswer=rs.getString("Choice");
                        if (Main.Debug.debugDatabase) System.out.println(" -- is correct!");
                    } 
                    else
                        if (Main.Debug.debugDatabase) System.out.println(" -- is NOT correct!");
                    
                    
//                    String[] choices=(rs.getString("Choices")).split(";");
//                    Question question=new Question(rs.getString("QuestionID"), rs.getString("Question"), choices, rs.getString("CorrectAnswer"));
//                    
//                    allQuestions.add(question);
//                    
//                    if (Main.Debug.debugDatabase) System.out.println(" - " + question.getQuestion());

                }
                
                Question question=new Question(currentQuestionID, currentQuestion, choices, correctAnswer);
                allQuestions.add(question);
            }
            else
                if (Main.Debug.debugDatabase) System.out.println("Error: Cannot establish connection to the database");
        }
        catch (SQLException sqle) 
        {
            if (Main.Debug.debugDatabase) System.out.println("Error: Error with SQL");
            if (Main.Debug.debugDatabase) System.out.println(sqle);
            
            allQuestions=null;
        }
        finally
        {
            dbAccess.disconnect();
        }
        
        if (Main.Debug.debugDatabase) System.out.println("------------ End of Database Operation ------------\n");
        
        return allQuestions;
    }

}
