/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.UI;

import Main.Components.Story;
import Main.ITSController;

/**
 *
 * @author crimsiris
 */
public class ConfirmFinishedWindow extends javax.swing.JFrame {

    private final int ICON_WIDTH=80;
    
    private final int LABEL_WIDTH=180;
    
    ITSController controller;
    
    /**
     * Creates new form ConfirmFinishedWindow
     */
    public ConfirmFinishedWindow(ITSController controller, Story story) 
    {
        initComponents();
        
        initializeComponents(controller, story);
    }
    
    private void initializeComponents(ITSController controller, Story story) 
    {
        this.controller=controller;
        
        lblQuestion.setText("Are you finished reading this story?");
        storyPanel.updatePanel(story.getTitle(), story.getIconPath(), ICON_WIDTH, LABEL_WIDTH);
        
        addListeners();
    }
    
    private void addListeners()
    {
        btnYes.addActionListener(this.controller);
        btnNo.addActionListener(this.controller);
    }
    
    public void initialize()
    {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    } 

    
    public void close()
    {
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblQuestion = new javax.swing.JLabel();
        btnYes = new javax.swing.JButton();
        btnNo = new javax.swing.JButton();
        storyPanel = new Main.UI.Components.StoryInfoPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblQuestion.setFont(new java.awt.Font("Edmondsans", 0, 18)); // NOI18N
        lblQuestion.setText("jLabel1");

        btnYes.setFont(new java.awt.Font("Edmondsans", 0, 16)); // NOI18N
        btnYes.setText("Yes, I am finished.");
        btnYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYesActionPerformed(evt);
            }
        });

        btnNo.setFont(new java.awt.Font("Edmondsans", 0, 16)); // NOI18N
        btnNo.setText("No, I am not yet finished.");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(65, 65, 65)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(lblQuestion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(btnYes, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(btnNo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .add(storyPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(40, 40, 40)
                .add(lblQuestion)
                .add(10, 10, 10)
                .add(storyPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(8, 8, 8)
                .add(btnYes)
                .add(18, 18, 18)
                .add(btnNo)
                .add(40, 40, 40))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnYesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnNo;
    public javax.swing.JButton btnYes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblQuestion;
    private Main.UI.Components.StoryInfoPanel storyPanel;
    // End of variables declaration//GEN-END:variables
}
