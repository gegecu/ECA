/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.Components;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author crimsiris
 */
public class Question {
    
    private final String DELIM=";";
    
    private String questionID;
    private String question;
    private ArrayList<String> choices;
    private String correctAnswer;
    
    public Question(String questionID, String question, ArrayList<String> choices, String correctAnswer)
    {
        this.questionID=questionID;
        this.question=question;
        this.correctAnswer=correctAnswer;
        
        this.choices=choices;
        //Collections.addAll(this.choices, choices);
    }
    
    public String getQuestion()
    {
        return question;
    }
    
    public ArrayList<String> getChoices()
    {
        Collections.shuffle(choices);
        return choices;
    }
    
    public boolean checkAnswer(String answer)
    {
        if (correctAnswer.equalsIgnoreCase(answer))
            return true;
        
        return false;
    }

}
