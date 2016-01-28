/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.UI.Components;

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
public class ChoiceCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
    
    private int ICON_WIDTH;
    private int LABEL_WIDTH;
    
    private final Color BORDER_COLOR=new Color(211,211,211);
    
    private final int ASCII_A=65;
    private String choice;
    
    private GeneralInfoPanel choicePanel;
    
    public ChoiceCell(int iconWidth, int labelWidth)
    {
        ICON_WIDTH=iconWidth;
        LABEL_WIDTH=labelWidth;
        
        choice=null;
        
        choicePanel=new GeneralInfoPanel();
    }
    
    private void updateCell(String choice, char charToRender, boolean isSelected)
    {
        if (choice!=null)
        {
            if (this.choice==null)
                this.choice=choice;
       
            choicePanel.updatePanel(choice, charToRender, ICON_WIDTH, LABEL_WIDTH);
            
            if (isSelected) 
            {
                choicePanel.setBorder(BorderFactory.createMatteBorder(1, 4, 1, 1, BORDER_COLOR));
            }
            else
            {
                choicePanel.setBorder(BorderFactory.createEmptyBorder());
            }
	
        }
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) 
    {
        String choice=(String)value;

        int letterChoiceAscii=ASCII_A+row;
        updateCell(choice, (char)letterChoiceAscii, isSelected);
        
        return choicePanel;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
        boolean isSelected, int row, int column) 
    {
        String choice=(String)value;

        int letterChoiceAscii=ASCII_A+row;
        updateCell(choice, (char)letterChoiceAscii, isSelected);
        
        return choicePanel;
    }

    @Override
    public Object getCellEditorValue() 
    {
        return null;
    }

}
