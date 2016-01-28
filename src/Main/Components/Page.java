/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.Components;

/**
 *
 * @author crimsiris
 */
public class Page {
    
    private String pageContent;
    private String pageIllustrationID;
    
    public Page()
    {
        pageContent="";
        pageIllustrationID="";
    }
    
    public Page(String pageContent, String pageIllustrationID)
    {
        this.pageContent=pageContent;
        this.pageIllustrationID=pageIllustrationID;
    }
    
    public String getPageContent()
    {
        return pageContent;
    }
    
    public void setPageContent(String pageContent)
    {
        this.pageContent=pageContent;
    }
    
    public String getPageIllustrationID()
    {
        return pageIllustrationID;
    }
    
    public void setPageIllustrationID(String pageIllustrationID)
    {
        this.pageIllustrationID=pageIllustrationID;
    }

}
