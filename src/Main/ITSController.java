package Main;

import Main.Components.Question;
import Main.Components.Story;
import Main.Components.StoryProfile;
import Main.Components.User;
import Main.Database.ImageLibraryDatabase;
import Main.Database.StoryLibraryDatabase;
import Main.Database.UserInformationDatabase;
import Main.UI.*;
import Main.UI.StoryWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author crimsiris
 */
public class ITSController extends Thread implements ActionListener, MouseListener{
    
    private UserInformationDatabase userInformationDB;
    private ImageLibraryDatabase imageLibraryDB;
    private StoryLibraryDatabase storyLibraryDB;   
 
    private Main.DialogueController dialogueController;
    
    private ArrayList<User> allUsers=null;
    private User currentUser=null;
    private User currentAgent=null;
    private boolean currentEmotionalState=true; // true - happy; false - sad
    
    private Story currentStory=null;
            
    private LoginWindow loginWindow=null;
    private SignupWindow signupWindow=null;
    private LibraryWindow libraryWindow=null;
    private ConfirmReadWindow confirmReadWindow=null;
    private StoryWindow storyWindow=null;
    private ConfirmFinishedWindow confirmFinishedWindow=null;
    private QuizWindow quizWindow=null;
    private FeedbackWindow feedbackWindow=null;
    private ChatWindow chatWindow=null;
    private WaitWindow waitWindow=null;
    
    private ITSController This;
    private static JsonObject dialogue=null;
                        
    
    public ITSController()
    {
        userInformationDB=new UserInformationDatabase();
        allUsers=userInformationDB.getAllUsers();
        
        imageLibraryDB=new ImageLibraryDatabase();
        
        dialogueController=new Main.DialogueController();
                              
        if (allUsers==null || allUsers.isEmpty())
        {
            signupWindow=new SignupWindow(this, imageLibraryDB.getAllAvatars());
            signupWindow.initialize();
        }
        else
        {
            loginWindow=new LoginWindow(this, allUsers);
            loginWindow.initialize();
        }
        This = this;
    }
    
    private void instantiateAgent()
    {
        if (currentAgent==null)
        {
            currentAgent=userInformationDB.getAgent(currentUser.getGender());
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) 
    {
        Object source=event.getSource();
        
        if (loginWindow!=null)
        {            
            if (source.equals(loginWindow.cmbNames))
            {
                loginWindow.paintAvatar();
            }
            else if (source.equals(loginWindow.btnLogin))
            {
                currentUser=loginWindow.getCurrentUser();
                
                loginWindow.close();
                loginWindow=null;
                
                storyLibraryDB=new StoryLibraryDatabase();
                ArrayList<StoryProfile> allStories = storyLibraryDB.getAllStoryProfiles();
                
                if (allStories!=null || allStories.isEmpty())
                {
                    libraryWindow=new LibraryWindow(this, currentUser, allStories);
                    libraryWindow.initialize();
                }
                
            }
            else if (source.equals(loginWindow.btnSignup))
            {
                loginWindow.close();
                loginWindow=null;

                signupWindow=new SignupWindow(this, imageLibraryDB.getAllAvatars());
                signupWindow.initialize();
            }
        }
        
        if (signupWindow!=null)
        {
            if (source.equals(signupWindow.btnBack))
            {
                signupWindow.showPreviousAvatar();
            }
            else if (source.equals(signupWindow.btnNext))
            {
                signupWindow.showNextAvatar();
            }
            else if (source.equals(signupWindow.btnSignup))
            {                
                String name=signupWindow.getUsername();
                String avatarID=signupWindow.getAvatarID();
                
                boolean isSuccessful=userInformationDB.addNewUser(name, 8, avatarID);
                
                if (isSuccessful)
                {
                    signupWindow.close();
                    signupWindow=null;
                    
                    currentUser=userInformationDB.getUser(name);
                    
                    storyLibraryDB=new StoryLibraryDatabase();
                    ArrayList<StoryProfile> allStories = storyLibraryDB.getAllStoryProfiles();

                    if (allStories!=null || allStories.isEmpty())
                    {
                        libraryWindow=new LibraryWindow(this, currentUser, allStories);
                        libraryWindow.initialize();
                    }
                }
            }
        }
        
        if (confirmReadWindow!=null)
        {
            if (source.equals(confirmReadWindow.btnNo))
            {
                confirmReadWindow.close();
                confirmReadWindow=null;
            }
            else if (source.equals(confirmReadWindow.btnYes))
            {
                confirmReadWindow.close();
                confirmReadWindow=null;
                
                StoryProfile selected=libraryWindow.getSelectedStory();              
                
                libraryWindow.close();
                libraryWindow=null;
                
                if (selected!=null)
                {
                    currentStory=new Story(selected.getStoryID(), selected.getTitle(), selected.getStoryPath(), selected.getTopicConcepts(), selected.getIconPath());
                    HashMap<String, String> storyIllustrations=imageLibraryDB.getAllStoryIllustrations(currentStory.getStoryID());
                    
                    storyWindow=new StoryWindow(this, currentStory, storyIllustrations);
                    storyWindow.initialize();
                }
            }
        }
        
        if (storyWindow!=null)
        {
            if (source.equals(storyWindow.btnBack))
            {
                storyWindow.showPreviousPage();
            }
            else if (source.equals(storyWindow.btnNext))
            {
                storyWindow.showNextPage();
                
                if (storyWindow.isFinished())
                {
                    confirmFinishedWindow=new ConfirmFinishedWindow(this, currentStory);
                    confirmFinishedWindow.initialize();
                }
            }
        }
        
        if (confirmFinishedWindow!=null)
        {
            if (source.equals(confirmFinishedWindow.btnYes))
            {
                confirmFinishedWindow.close();
                confirmFinishedWindow=null;
                
                storyWindow.close();
                storyWindow=null;
                
                storyLibraryDB.addReadCountOfStory(currentStory.getStoryID());
                
                ArrayList<Question> allQuestions=storyLibraryDB.getQuestions(currentStory.getStoryID());
                
                quizWindow=new QuizWindow(this, allQuestions);
                quizWindow.initialize();
            }
            else if (source.equals(confirmFinishedWindow.btnNo))
            {
                storyWindow.resetIsFinished();
                
                confirmFinishedWindow.close();
                confirmFinishedWindow=null;
                
            }
        }
        
        if (feedbackWindow!=null)
        {
            if (source.equals(feedbackWindow.btnClose))
            {
                boolean showNextQuestion=feedbackWindow.showNextQuestion();
                boolean toIntervene=feedbackWindow.toIntervene();
                
                feedbackWindow.close();
                feedbackWindow=null;
                
                if (showNextQuestion)
                    quizWindow.displayQuestion();
                else
                {
                    if (toIntervene)
                    {
                        instantiateAgent();
                        
                        waitWindow=new WaitWindow(This, currentAgent.getName(), currentAgent.getGender());
                        waitWindow.initialize();
                        
                        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                            @Override
                            public Void doInBackground() {
                                dialogue=dialogueController.initiateDialogue(currentStory.getTopicConcepts(), currentEmotionalState);
                                return null;
                            }

                            @Override
                            public void done() {
                                
                                waitWindow.close();
                                waitWindow=null;
                                
                                if(dialogue != null) {
                                    chatWindow=new ChatWindow(This, currentUser, currentAgent);
                                    chatWindow.initialize(dialogue);
                                }
                                else {
                                    quizWindow.resume();
                                }
                        
                            }

                        };

                        worker.execute();
                        
                    }
                }      
            }
        }
        
        
    }
    
    @Override
    public void mouseClicked(MouseEvent event) 
    {
        Object source = event.getSource();
        
        if (libraryWindow!=null)
        {
            if (source.equals(libraryWindow.tblStories))
            {
                StoryProfile story=libraryWindow.getSelectedStory();
                System.out.println("Selected story: " + story.getTitle());
                
                if (story!=null)
                {
                    confirmReadWindow=new ConfirmReadWindow(this, story);
                    confirmReadWindow.initialize();
                }
            }
        }
        
        if (quizWindow!=null)
        {
            if (source.equals(quizWindow.tblChoices))
            {
                int isCorrect=quizWindow.checkForAnswer();
                feedbackWindow=new FeedbackWindow(this, isCorrect, currentUser.getName());
                
                
                if(quizWindow.endQuestions() && isCorrect == 1) {
                    quizWindow.close();
                    //feedbackWindow.close();
                    ArrayList<StoryProfile> allStories = storyLibraryDB.getAllStoryProfiles();
                    if (allStories!=null || allStories.isEmpty())
                    {
                        libraryWindow=new LibraryWindow(this, currentUser, allStories);
                        libraryWindow.initialize();
                    }
                }
                feedbackWindow.initialize();
                
            }
            else 
                if (source.equals((quizWindow.selfReportingPanel).happyPanel) || 
                    source.equals((quizWindow.selfReportingPanel).sadPanel))
            {
                JPanel panel=(JPanel)source;
                quizWindow.selfReportingPanel.setBorder(panel);
                currentEmotionalState=quizWindow.selfReportingPanel.getEmotionalState(panel);
                
                if (!currentEmotionalState)
                {
                    quizWindow.suspend();
                    
                    feedbackWindow=new FeedbackWindow(this, currentUser.getName());
                    feedbackWindow.initialize();
                    
                }  

            }  
        }
        
        if (chatWindow!=null)
        {
            
            if (source.equals(chatWindow.replyDialoguePanel.tblChoices))
            {
                JsonObject selectedReply=chatWindow.getSelectedReply();
                JsonObject dialogue=dialogueController.generateDialogue(selectedReply, currentEmotionalState);
                if(dialogue != null) {
                    chatWindow.setDialogue(dialogue);
                }
                else {
                    chatWindow.close();
                    quizWindow.resume();
                }
            }  
            else if (source.equals(chatWindow.replyDialoguePanel.selfReportingPanel.happyPanel) ||
                    source.equals(chatWindow.replyDialoguePanel.selfReportingPanel.sadPanel))
            {
                JPanel panel=(JPanel)source;
                chatWindow.replyDialoguePanel.selfReportingPanel.setBorder(panel);
                currentEmotionalState=chatWindow.replyDialoguePanel.selfReportingPanel.getEmotionalState(panel);
                
                System.out.println("Current emotional state: " + (currentEmotionalState?"happy": "sad"));
                
                if(currentEmotionalState) {
                     chatWindow.close();
                     quizWindow.resume();
                    
                }//
            }
        }
    }
    

    public static void main (String[] args)
    {
        new ITSController();
    }


    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

    
    

}
