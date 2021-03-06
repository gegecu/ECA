/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.UI;

import Main.Components.StoryProfile;
import Main.Components.User;
import Main.ITSController;
import Main.UI.Components.StoryCell;
import Main.UI.Components.StoryTableModel;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author crimsiris
 */
public class LibraryWindow extends javax.swing.JFrame {

    private final int AVATAR_WIDTH=104;
    private final int AVATAR_HEIGHT=104;
    
    private final int ROW_HEIGHT=113;
    
    private ITSController controller;
    
    private User currentUser;
    
    private StoryTableModel tableModel;
    
    /**
     * Creates new form LibraryWindow
     */
    public LibraryWindow(ITSController controller, User currentUser, ArrayList<StoryProfile> stories) 
    {
        initComponents();
        
        initializeComponents(controller, currentUser, stories);
    }
    
    private void initializeComponents(ITSController controller, User currentUser, ArrayList<StoryProfile> stories)
    {
        this.controller=controller;
        
        this.currentUser=currentUser;
        
        paintAvatar();
        
        lblHello.setText("Hello " + currentUser.getName() + "!");
        lblQuestion.setText("What do you want to read today?");

        tableModel=new StoryTableModel(stories);
        tblStories.setModel(tableModel);
        
        tblStories.setDefaultRenderer(StoryProfile.class, new StoryCell());
	tblStories.setDefaultEditor(StoryProfile.class, new StoryCell());
        
        tblStories.getTableHeader().setPreferredSize(new Dimension(-1, 0));
        tblStories.setRowHeight(ROW_HEIGHT);
        
        addListeners();
        
    }
    
    private void addListeners()
    {
        tblStories.addMouseListener(this.controller);
    }
    
    private void paintAvatar()
    {
        avatar.setPreferredSize(new Dimension(AVATAR_WIDTH, AVATAR_HEIGHT));
        
        String avatarPath=currentUser.getAvatarPath();
        avatar.setIcon(avatarPath);
        avatar.repaint();
    }
    
    public void initialize()
    {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
    
    public StoryProfile getSelectedStory()
    {
        int selectedRow=tblStories.getSelectedRow();
        if (selectedRow > -1)
        {
            return (StoryProfile)tblStories.getValueAt(selectedRow, 0);
        }
        
        return null;
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

        jPanel2 = new javax.swing.JPanel();
        lblHello = new javax.swing.JLabel();
        lblQuestion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStories = new javax.swing.JTable();
        avatar = new Main.UI.Components.VectorIconPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblHello.setFont(new java.awt.Font("Edmondsans", 0, 48)); // NOI18N
        lblHello.setText("Hello Xyra!");

        lblQuestion.setFont(new java.awt.Font("Edmondsans", 0, 24)); // NOI18N
        lblQuestion.setText("What do you want to read today?");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Stories", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Edmondsans", 0, 15))); // NOI18N

        tblStories.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblStories);

        org.jdesktop.layout.GroupLayout avatarLayout = new org.jdesktop.layout.GroupLayout(avatar);
        avatar.setLayout(avatarLayout);
        avatarLayout.setHorizontalGroup(
            avatarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 104, Short.MAX_VALUE)
        );
        avatarLayout.setVerticalGroup(
            avatarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 104, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(45, 45, 45)
                        .add(avatar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(lblHello, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(lblQuestion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(30, 30, 30)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 625, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(30, 30, 30)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(lblHello, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lblQuestion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(avatar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 10, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 396, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(30, 30, 30))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Main.UI.Components.VectorIconPanel avatar;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHello;
    private javax.swing.JLabel lblQuestion;
    public javax.swing.JTable tblStories;
    // End of variables declaration//GEN-END:variables
}
