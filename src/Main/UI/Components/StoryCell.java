/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.UI.Components;

import Main.Components.StoryProfile;
import java.awt.Color;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author crimsiris
 */
public class StoryCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
    
    private int ICON_WIDTH=100;
    
    private int LABEL_WIDTH=380;
    
    private final Color BORDER_COLOR=new Color(211,211,211);
   
    private String storyID;
    
    private StoryInfoPanel storyPanel;
    
    public StoryCell()
    {
        storyID=null;

        storyPanel=new StoryInfoPanel();
    }

    
    private void updateCell(StoryProfile story, boolean isSelected)
    {
        if (story!=null)
        {
            if (storyID==null)
                this.storyID=story.getStoryID();
       
            storyPanel.updatePanel(story, ICON_WIDTH, LABEL_WIDTH);
            
            if (isSelected) 
            {
                storyPanel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, BORDER_COLOR));
            }
            else
            {
                storyPanel.setBorder(BorderFactory.createEmptyBorder());
            }
	
        }
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) 
    {
        StoryProfile story=(StoryProfile)value;

        updateCell(story, isSelected);
        
        return storyPanel;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
        boolean isSelected, int row, int column) 
    {
        StoryProfile story=(StoryProfile)value;

        updateCell(story, isSelected);
        
        return storyPanel;
    }

    @Override
    public Object getCellEditorValue() 
    {
        return null;
    }
    

}
