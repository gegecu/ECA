/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.Reader;

import Main.Components.Page;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author crimsiris
 */
public class Reader {
    
    private static String START_PAGE_TAG="page";
    private static String END_PAGE_TAG="/page";
    private static String IMG_TAG = "img";

        
    public static ArrayList<Page> readStoryFile(String path)
    {        
        BufferedReader br=null;
        ArrayList<Page> story = new ArrayList<Page>();

        try
        {
            System.out.println(path);
            
            br=new BufferedReader(new FileReader(path));
            
            boolean isOpenTag=false;
            boolean isNewPage=false;
            boolean isIllustrationID=false;
            boolean isNewline=false;

            StringBuilder consumer=new StringBuilder();
            String temp="";
            
            String pageIllustrationID="";
            
            int n;
            while ((n=br.read())!=-1)
            {
                char c=(char)n;
//                System.out.print(c + " (" + n + ")");
                
                if (c=='<')
                {
                    isOpenTag=true;
                    
                    temp=consumer.toString();
                    consumer=new StringBuilder();
//                    System.out.println(" - is open tag");
                }
                else if (c=='\'')
                {
                    if (isOpenTag)
                    {
                        if (consumer.length()>0)
                        {
                            isIllustrationID=false;
                        
                            pageIllustrationID=consumer.toString();
                            consumer=new StringBuilder();

//                            System.out.println(" - is end of page illustration ID");
                        }
//                        else
//                            System.out.println(" - is start of page illustration ID");
                    }
                    else
                    {
                        consumer.append(c);
//                        System.out.println(" - is content");
                    }

                }
                else if (isIllustrationID && isNewPage)
                {
                    consumer.append(c);
//                    System.out.println(" - is page illustration ID");
                    
                }     
                else if (c==' ')
                {
                    if (isOpenTag)
                    {
                        if (consumer.length()>0)
                        {
                            String s=consumer.toString();
                            if (s.equalsIgnoreCase(START_PAGE_TAG))
                            {                             
                                isNewPage=true;
                                
                                consumer = new StringBuilder(); 
//                                System.out.println(" - is new page with page illustration ID");
                            }
                        } 

                    }
                    else
                    {
                        consumer.append(c);
//                        System.out.println(" - is content");
                    }    
                }
                else if (c=='=')
                {
                    if (isOpenTag)
                    {
                        if (consumer.length()>0)
                        {
                            String s=consumer.toString();
                            if (s.equalsIgnoreCase(IMG_TAG))
                            {
                                isIllustrationID=true; 
                                
                                consumer=new StringBuilder();
//                                System.out.println(" - is assigning page illustration ID");
                            } 
                        }
                    }
                    else
                    {
                        consumer.append(c);
//                        System.out.println(" - is content");
                    }
                }
                else if (c=='>')
                {
                    if (isOpenTag)
                    {
                        if (consumer.length()>0)
                        {
                            String s=consumer.toString();
//                            System.out.println("close: " + s);
                            if (s.equalsIgnoreCase(END_PAGE_TAG))
                            {                                  
                                story.add(new Page(temp.concat("</p>"), pageIllustrationID));
                                
//                                System.out.println(" - is end of closing page tag");
//                                                
//                                System.out.println("page: " + temp);
//                                System.out.println("illustration ID: " + pageIllustrationID);
                                
                                consumer = new StringBuilder();
                                pageIllustrationID="";
                                
                                temp="";
                                
                                                             
                            }
                            else if (s.equalsIgnoreCase(START_PAGE_TAG) || isNewPage)
                            {    
                                consumer = new StringBuilder(); 
                                consumer.append("<p>");
                                
                                isNewPage=false;
//                                System.out.println(" - is end of new page tag");
                                
                            }
                            
                        } 
                        
                        isOpenTag=false;
                    }
                }
                else if (c=='\n')
                {
                    
                    if (isNewline)
                    {
                        consumer.append("</p><br><p>");
                        isNewline=false;
//                        System.out.println(" - is second newline");
                    }
                    else
                    {
                        isNewline=true;
//                        System.out.println(" - is first newline");
                    }
                    
                }
                else
                {
                    if (isNewline)
                    {
                        isNewline=false;
//                        System.out.print(" - is end of newline chain");
                    }
                    
                    consumer.append(c);
//                    System.out.println(" - is content");
                }
            }

        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                if (br!=null)
                    br.close();
            }
            catch (IOException ioe)
            {
                
            }
            
        }
        
        return story;
    }

        
}
