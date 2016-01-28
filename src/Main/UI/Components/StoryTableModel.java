/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.UI.Components;

import Main.Components.StoryProfile;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author crimsiris
 */
public class StoryTableModel extends AbstractTableModel implements TableModel {
    
    private final int COL_COUNT=1;
    
    private ArrayList<StoryProfile> stories;
    
    public StoryTableModel(ArrayList<StoryProfile> stories)
    {
        this.stories=stories;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) 
    { 
        return StoryProfile.class; 
    }

    @Override
    public int getRowCount() 
    {
        return stories.size();
    }

    @Override
    public int getColumnCount() 
    {
        return COL_COUNT;
    }

    @Override
    public Object getValueAt(int row, int col) 
    {       
        return stories.get(row);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) 
    { 
        return false; 
    }
   

}
