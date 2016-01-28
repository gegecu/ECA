/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.UI.Components;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author crimsiris
 */
public final class ChoiceTableModel extends AbstractTableModel implements TableModel {
    
    private final int COL_COUNT=1;
    
    private ArrayList<String> choices;
    
    public ChoiceTableModel()
    {
        
    }
    
    public ChoiceTableModel(ArrayList<String> choices)
    {
        this.setChoices(choices);
    }
    
    public void setChoices(ArrayList<String> choices)
    {
        this.choices=choices;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) 
    { 
        return String.class; 
    }

    @Override
    public int getRowCount() 
    {
        return choices.size();
    }

    @Override
    public int getColumnCount() 
    {
        return COL_COUNT;
    }

    @Override
    public Object getValueAt(int row, int col) 
    {       
        return choices.get(row);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) 
    { 
        return false; 
    }

}
