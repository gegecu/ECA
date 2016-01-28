/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.UI.Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author crimsiris
 */
public class IconPanel extends JPanel {
    
    protected ArrayList<Color> bgColors;
    protected Color iconBGColor;
    
    protected BufferedImage icon=null;
    
    private final String FONT="Edmondsans";
    private int FONT_SIZE=30;
    private final int FONT_STYLE=Font.BOLD;
    protected Font font;
    
    protected String textToRender=null;
    
    public IconPanel()
    {
        super();
        initializeComponents();
    }
    
    protected void initializeComponents()
    {       
        this.setSize(getPreferredSize());
        this.setBackground(Color.WHITE);
        
        bgColors=new ArrayList<Color>();
        bgColors.add(new Color(72,209,204)); // Medium turquoise
        bgColors.add(new Color(132,112,255)); // Light slate blue
        bgColors.add(new Color(32,178,170)); // Light sea green
        bgColors.add(new Color(127,255,212));   //Aquamarine
        bgColors.add(new Color(255,215,0)); // Gold
        bgColors.add(new Color(255,99,71)); // Tomato
        bgColors.add(new Color(250,128,114)); // Salmon
        bgColors.add(new Color(255,105,180)); // Hot pink
        
        font=new Font(FONT, FONT_STYLE, FONT_SIZE);
        
    }
    
    protected Color getRandomColor()
    {
        Random r=new Random();
        int index=r.nextInt(bgColors.size());
        
        return bgColors.get(index);
    }
    
    public void setIcon(String path)
    {
        try
        {
            if (path!=null && !path.isEmpty())
            {
                icon = ImageIO.read(new File(path));
                textToRender=null;
            }              
            
            if (iconBGColor==null)
                iconBGColor=getRandomColor();
            
        }
        catch (IOException e)
        {
            icon=null;
            iconBGColor=Color.WHITE;
        }
    }
    
    public void setTextToRender(String text)
    {
        if (text!=null && !text.isEmpty())
        {
            textToRender=text;
            icon = null;
        }              

        if (iconBGColor==null)
            iconBGColor=getRandomColor();
    }

}
