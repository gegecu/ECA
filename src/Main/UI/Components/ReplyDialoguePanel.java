/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.UI.Components;

import java.awt.Color;
import java.awt.Dimension;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.swing.JPanel;

/**
 *
 * @author crimsiris
 */
public class ReplyDialoguePanel extends JPanel {

    private final int BUBBLE_WIDTH=466;
    private final int BUBBLE_HEIGHT=307;
    private final int BUBBLE_CURVE=30;
    
    private final int[] TRIANGLE_X_POINTS=new int[]{470, 481, 470};
    private final int[] TRIANGLE_Y_POINTS=new int[]{55, 65, 75};
    
    private final Color BGCOLOR=new Color(245,245,245); // white smoke
    
    private final int ROW_HEIGHT=55;
    private final int ICON_WIDTH=45;
    private final int REPLY_LABEL_WIDTH=290;
    
    private ReplyTableModel tableModel;
    
    /**
     * Creates new form ReplyDialoguePanel
     */
    public ReplyDialoguePanel() 
    {
        super();
        initComponents();
        initializeComponents();
    }
    
    private void initializeComponents()
    {
        this.setBackground(Color.WHITE);
        
        int padding=Math.abs(TRIANGLE_X_POINTS[0]-BUBBLE_WIDTH);
        bubblePanel.initializeComponents(new Dimension(BUBBLE_WIDTH, BUBBLE_HEIGHT), new Dimension(BUBBLE_CURVE, BUBBLE_CURVE), padding, TRIANGLE_X_POINTS, TRIANGLE_Y_POINTS, BGCOLOR);
        bubblePanel.repaint();
        
        tblChoices.setDefaultRenderer(String.class, new ChoiceCell(ICON_WIDTH, REPLY_LABEL_WIDTH));
	tblChoices.setDefaultEditor(String.class, new ChoiceCell(ICON_WIDTH, REPLY_LABEL_WIDTH));
        
        tblChoices.setSize(new Dimension(450, 287));
        tblChoices.getTableHeader().setPreferredSize(new Dimension(-1, 0));
        tblChoices.setRowHeight(ROW_HEIGHT);
        
        selfReportingPanel.setSize(new Dimension(450, 54));
        
    }
    
    public void setReplies(JsonArray replies)
    {
        tableModel=new ReplyTableModel(replies);
        tblChoices.setModel(tableModel);
    }
    
    public JsonObject getSelectedReply()
    {
        int row=tblChoices.getSelectedRow();
        System.out.println(row);
        return tableModel.getValueOfSelectedRow(row);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bubblePanel = new Main.UI.Components.SpeechBubblePanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChoices = new javax.swing.JTable();
        selfReportingPanel = new Main.UI.Components.SelfReportingPanel();

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setSize(new java.awt.Dimension(448, 283));

        tblChoices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblChoices.setSize(new java.awt.Dimension(448, 64));
        jScrollPane1.setViewportView(tblChoices);

        org.jdesktop.layout.GroupLayout bubblePanelLayout = new org.jdesktop.layout.GroupLayout(bubblePanel);
        bubblePanel.setLayout(bubblePanelLayout);
        bubblePanelLayout.setHorizontalGroup(
            bubblePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(bubblePanelLayout.createSequentialGroup()
                .add(11, 11, 11)
                .add(bubblePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane1)
                    .add(selfReportingPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        bubblePanelLayout.setVerticalGroup(
            bubblePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(bubblePanelLayout.createSequentialGroup()
                .add(15, 15, 15)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 222, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(selfReportingPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(16, 16, 16))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(bubblePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(bubblePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Main.UI.Components.SpeechBubblePanel bubblePanel;
    private javax.swing.JScrollPane jScrollPane1;
    public Main.UI.Components.SelfReportingPanel selfReportingPanel;
    public javax.swing.JTable tblChoices;
    // End of variables declaration//GEN-END:variables
}
