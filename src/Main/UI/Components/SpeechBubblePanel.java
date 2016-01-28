/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.UI.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

/**
 *
 * @author crimsiris
 */
public class SpeechBubblePanel extends JPanel{
     
    protected Dimension bubbleSize;
    protected Dimension bubbleCurve;
    protected int padding;
    
    protected int[] triangleXPoints;
    protected int[] triangleYPoints;
    
    protected Color BGColor;
    
    private GeneralPath triangle;
    
    /**
     * Creates new form SpeechBubblePanel
     */
    public SpeechBubblePanel()
    {
        initializeComponents();
    }
    
    private void initializeComponents()
    {
        this.bubbleSize=new Dimension();
        this.bubbleCurve=new Dimension();
        this.padding=0;
        
        this.triangleXPoints=new int[0];
        this.triangleYPoints=new int[0];
        
        this.setBackground(Color.WHITE);
        this.BGColor=Color.WHITE;
        
        this.triangle=new GeneralPath();
    }
    
    public void initializeComponents(Dimension bubbleSize, Dimension bubbleCurve, int padding, int[] triangleXPoints, int[] triangleYPoints, Color BGColor) 
    {
        this.bubbleSize=bubbleSize;
        this.bubbleCurve=bubbleCurve;
        this.padding=padding;
   
        this.triangleXPoints=triangleXPoints;
        this.triangleYPoints=triangleYPoints;
        
        this.BGColor=BGColor;
        
        drawTriangle();
    }
    
    protected void drawTriangle()
    {
        int numPoints=triangleXPoints.length;
        triangle=new GeneralPath(GeneralPath.WIND_EVEN_ODD, numPoints);
        triangle.moveTo(triangleXPoints[0], triangleYPoints[0]);
        
        for (int i=1; i<numPoints; i++)
        {
            triangle.lineTo(triangleXPoints[i], triangleYPoints[i]);    
        }
        
        triangle.closePath();
    }


    
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);       
        
        //int panelWidth=getPreferredSize().width;
        int panelHeight=getPreferredSize().height;
        
        int bubbleWidth=bubbleSize.width;
        int bubbleHeight=bubbleSize.height;
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw rounded rectangle
        int x=padding;
        int y=(panelHeight/2)-(bubbleHeight/2);
        
        g2d.setColor(BGColor);
        g2d.fillRoundRect(x, y, bubbleWidth, bubbleHeight, bubbleCurve.width, bubbleCurve.height);
        
        // draw arrow
        g2d.fill(triangle);
        
        
    }

}
