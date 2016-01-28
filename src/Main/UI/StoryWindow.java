/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.UI;

import Main.Components.Page;
import Main.Components.Story;
import Main.ITSController;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author crimsiris
 */
public class StoryWindow extends javax.swing.JFrame {

    //private final int ICON_WIDTH=96;
    private final int LABEL_WIDTH=315;
    
    private final String HTML_HEADER="<html>" +
                                        "<p style=\"font-family:Edmondsans;font-size:12px;\">" +
                                            "%s" +
                                        "</p>" +
                                     "</html>";
    
    private final String HTML_CONTENT="<html>" +
                                        "<div style=\"width:%dpx;text-align:justify;font-family:Edmondsans;font-size:15px;\">" +
                                            "<p> %s" +
                                            "</p>" +
                                        "</div>" + 
                                     "</html>";
    
    private ITSController controller;
    
    private ArrayList<Page> pages;
    //private ArrayList<String> pageIllustrationIDs;
    private HashMap<String,String> pageIllustrations;
    
    private int currentPage=1;
    
    private boolean isFinished=false;
    
    
    /**
     * Creates new form StoryWindow
     */
    public StoryWindow(ITSController controller, Story story, HashMap<String, String> pageIllustrations) 
    {
        initComponents();
        
        initializeComponents(controller, story, pageIllustrations);
    }
    
    private void initializeComponents(ITSController controller, Story story, HashMap<String, String> storyIllustrations) 
    {
        this.controller=controller;
        
        this.pages=story.getPages();
        //this.pageIllustrationIDs=story.getPageIllustrationIDs();
        this.pageIllustrations=storyIllustrations;
        
        String titleText=String.format(HTML_HEADER, "You are reading <b>" + (story.getTitle()).toUpperCase() + "</b>");
        lblHeader.setText(titleText);
        
        updatePageNumber(); 
        
        illustrationPanel.setPreferredSize(new Dimension(410, 530));
        
        showContent();
        
        btnBack.setEnabled(false);
        
        addListeners();
    }
    
    public void addListeners()
    {
        btnBack.addActionListener(this.controller);
        btnNext.addActionListener(this.controller);
    }
    
    public void initialize()
    {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private void updatePageNumber()
    {
        String pageNumberText=String.format(HTML_HEADER, "page " + currentPage + " of " + (pages.size()));
        lblPage.setText(pageNumberText);
    }
    
    private void showContent()
    {
        showIllustration();
        showText();
    }
    
    private void showIllustration()
    {
//        if (currentPage<=pageIllustrationIDs.size())
//        {
//            String id=pageIllustrationIDs.get(currentPage-1); 
//            System.out.println("id: " + id);
//            if (id!=null && !id.isEmpty())
//            {
//                String path=pageIllustrations.get(id);
//                if (path!=null && !id.isEmpty())
//                {
//                    System.out.println("path: " + path);
//                    illustrationPanel.setIllustration(path);
//                }
//            }
//            else
//                illustrationPanel.setIllustration(null);
//            
//            illustrationPanel.repaint();
//        }
        
        String id = pages.get(currentPage-1).getPageIllustrationID();
        
        System.out.println("id: " + id);
        if (id!=null && !id.isEmpty())
        {
            String path=pageIllustrations.get(id);
            if (path!=null && !id.isEmpty())
            {
                System.out.println("path: " + path);
                illustrationPanel.setIllustration(path);
            }
            
        }
        else
            illustrationPanel.setIllustration(null);
            
        illustrationPanel.repaint();
    }
    
    private void showText()
    {
//        StringBuilder sb=new StringBuilder();
//        for (int i=startIndex; i<endIndex;i++)
//        {
//            String line=pages.get(i);
//            
//            if ((i==startIndex || i==endIndex-1) && line.isEmpty())
//                continue;
//            
//            if (line.isEmpty())
//                line="</p><br><p>";
//            
//            sb.append(line + " ");
//            
//        }
//        
//        sb.append("</p>");
//            
//        String finalContents=String.format(HTML_CONTENT, LABEL_WIDTH, sb.toString());
        
        String finalContents=String.format(HTML_CONTENT, LABEL_WIDTH, pages.get(currentPage-1).getPageContent());
        lblContent.setText(finalContents);
    }
    
    public void showPreviousPage()
    {
        currentPage--;
        
        updatePageNumber();
        showContent();
        
        if (!btnNext.isEnabled())
            btnNext.setEnabled(true);
        
        if (currentPage==1)
            btnBack.setEnabled(false);
        
    }
    
    public void showNextPage()
    {
        currentPage++;        
        
        if (currentPage==pages.size()+1)
        {
            isFinished=true;
            btnNext.setEnabled(false);
        }
        else
        {
            updatePageNumber();
            showContent();

            if (!btnBack.isEnabled())
                btnBack.setEnabled(true);

    //        if (endIndex==pageBreaks.get(pageBreaks.size()-1))
    //            btnNext.setEnabled(false);
        }
        
    }
    
    public boolean isFinished()
    {
        return isFinished;
    }
    
    public void resetIsFinished()
    {
        isFinished=false;
        currentPage--;
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

        JPanel1 = new javax.swing.JPanel();
        btnNext = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblHeader = new javax.swing.JLabel();
        lblContent = new javax.swing.JLabel();
        lblPage = new javax.swing.JLabel();
        illustrationPanel = new Main.UI.Components.PageIllustrationPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnNext.setFont(new java.awt.Font("Edmondsans", 0, 18)); // NOI18N
        btnNext.setText(">");
        btnNext.setToolTipText("Next");

        btnBack.setFont(new java.awt.Font("Edmondsans", 0, 18)); // NOI18N
        btnBack.setText("<");
        btnBack.setToolTipText("Go back");

        lblHeader.setText("jLabel1");

        lblContent.setFont(new java.awt.Font("Edmondsans", 0, 19)); // NOI18N
        lblContent.setText("jLabel2");

        lblPage.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPage.setText("jLabel1");

        org.jdesktop.layout.GroupLayout illustrationPanelLayout = new org.jdesktop.layout.GroupLayout(illustrationPanel);
        illustrationPanel.setLayout(illustrationPanelLayout);
        illustrationPanelLayout.setHorizontalGroup(
            illustrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        illustrationPanelLayout.setVerticalGroup(
            illustrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout JPanel1Layout = new org.jdesktop.layout.GroupLayout(JPanel1);
        JPanel1.setLayout(JPanel1Layout);
        JPanel1Layout.setHorizontalGroup(
            JPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(JPanel1Layout.createSequentialGroup()
                .add(30, 30, 30)
                .add(JPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(lblHeader, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                    .add(illustrationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(18, 18, 18)
                .add(JPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, JPanel1Layout.createSequentialGroup()
                        .add(0, 362, Short.MAX_VALUE)
                        .add(btnNext))
                    .add(JPanel1Layout.createSequentialGroup()
                        .add(JPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(btnBack)
                            .add(JPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, lblPage, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, lblContent, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)))
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JPanel1Layout.setVerticalGroup(
            JPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(JPanel1Layout.createSequentialGroup()
                .add(30, 30, 30)
                .add(JPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblHeader)
                    .add(lblPage))
                .add(18, 18, 18)
                .add(JPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(JPanel1Layout.createSequentialGroup()
                        .add(lblContent, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 481, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(JPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btnNext)
                            .add(btnBack)))
                    .add(illustrationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(JPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(JPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel1;
    public javax.swing.JButton btnBack;
    public javax.swing.JButton btnNext;
    private Main.UI.Components.PageIllustrationPanel illustrationPanel;
    private javax.swing.JLabel lblContent;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblPage;
    // End of variables declaration//GEN-END:variables
}
