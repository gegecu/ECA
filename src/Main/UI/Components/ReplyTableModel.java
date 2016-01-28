/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.UI.Components;

import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author crimsiris
 */
public class ReplyTableModel extends AbstractTableModel implements TableModel {
    
    private final int COL_COUNT=1;
    
    private final String JSON_KEY_ID=Main.DialogueController.JSON_KEY_ID;
    private final String JSON_KEY_RELATION=Main.DialogueController.JSON_KEY_RELATION;
    private final String JSON_KEY_STRING=Main.DialogueController.JSON_KEY_STRING;
    
    private JsonArray replies;
    
    public ReplyTableModel()
    {
        
    }
    
    public ReplyTableModel(JsonArray replies)
    {
        this.setReplies (replies);
    }
    
    public void setReplies(JsonArray replies)
    {
        this.replies = replies;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) 
    { 
        return String.class; 
    }

    @Override
    public int getRowCount() 
    {
        return replies.size();
    }

    @Override
    public int getColumnCount() 
    {
        return COL_COUNT;
    }

    @Override
    public Object getValueAt(int row, int col) 
    {       
        JsonObject selected=replies.getJsonObject(row);
        return selected.getString(JSON_KEY_STRING);
    }
    
    public JsonObject getValueOfSelectedRow(int row)
    {
        return replies.getJsonObject(row);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) 
    { 
        return false; 
    }


}
