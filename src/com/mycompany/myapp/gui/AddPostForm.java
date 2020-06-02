/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.util.StringUtil;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.Vars;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.ServicePost;
import rest.file.uploader.tn.FileUploader;
import java.util.*;


/**
 *
 * @author USER
 */
public class AddPostForm extends Form
{
//private FileUploader file;
//String fileNameInServer; 
//private String imgPath;
      String img;
         private String imgPath;
         String Imagecode ;
         private FileUploader file;
         String fileNameInServer;
Post pass=new Post();
    AddPostForm(Form current,fos_user u) {
        Label pathphoto = new Label(".");
        
        
        setTitle("addpost");
        setLayout(BoxLayout.y());
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> current.showBack());
         
         
         
         
         
         Style s = UIManager.getInstance().getComponentStyle("Title");
         Button  capturephoto = new Button("prenez une photo");
         Label picture = new Label("", "Container");
         
         
         
         Label lab1= new Label("saisir  un titre");
         TextField texttitre = new TextField();
         Label lab2 = new Label("saisir  une description");  
         TextField textdescription= new TextField();
         Label lab3 = new Label("saisir  rating de 1 à 5");
         TextField textrating = new TextField();
        
       Button tphoto = new Button("ajouter  photo");
         Button btn = new Button("add post");
          btn.getUnselectedStyle().setFgColor(5542241);
        addAll(lab1,texttitre,lab2,textdescription,lab3,textrating,pathphoto,tphoto,btn);
       
            //  addAll(lab1,texttitre,lab2,textdescription,lab3,textrating,btn);
        
       
//         
//tphoto.addActionListener((evt) -> {
//     String il =Capture.capturePhoto();
//     System.out.println("0: "+il);
//     
//     
//                String i =Capture.capturePhoto().toString();
//                System.out.println("1: "+i);
//                int num=i.indexOf("/",2);
//                String news=i.substring(num + 2,i.length());
//            //    System.out.println(""+news);
//                                System.out.println("aaaa"+i);
//                               String str = "how to do in java provides java tutorials";
//         
//         String k="file://C:/Users/USER/AppData/Local/Temp/";
//        
//         System.out.println("length: "+k.length());
//         imgPath=i.substring(40);
//        System.out.println(i.substring(40));
// 
//                FileUploader fu=new FileUploader("http://localhost/final_pi/web/upload");
//            try {
//                fileNameInServer=fu.upload(news);
//                System.out.println("aaa"+fileNameInServer);
//                pass.setPhoto(fileNameInServer);
//                System.out.println(pass.getPhoto()+"kkkk");
//                pass.setPhoto(imgPath);
//                
//            } catch (Exception ex) {
//            }
//});
//  
        
        
         
         btn.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
            if ((texttitre.getText().length()==0)||(textdescription.getText().length()==0)|| (textrating.getText().length()==0))
             Dialog.show("Alert", "Please fill all the fields","ok",null);    
           
            else
                {
                    try {
                         //  System.out.println("name  "+pass.getPhoto());
                        Post t = new Post(texttitre.getText(),textdescription.getText(),Integer.parseInt(textrating.getText()),fileNameInServer);
                   //       Post t = new Post(texttitre.getText(),textdescription.getText(),Integer.parseInt(textrating.getText()));
                         System.out.println(t);
                        if( ServicePost.getInstance().addPost(t,u))
                        {
                            System.out.println("t"+t+"utilisateur de l'id   "+u.getId());
                            Dialog.show("Success","Connection accepted","ok",null);
                        }
                        else
                            Dialog.show("ERROR", "Server error", "ok",null);
                    }
                    catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number","ok",null);
            }
                    
                }
                
                
            }
        });
         
//          capturephoto.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                
//              String i=  Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
//              if(i!=null)
//              {
//                  System.out.println(i);
//                  
//                    String str = "how to do in java provides java tutorials";
//         
//         String k="file://C:/Users/USER/AppData/Local/Temp/";
//        
//         System.out.println("length: "+k.length());
//         imgPath=i.substring(40);
//        System.out.println("devenir  "+i.substring(40));
//                  
//                  try {
//                      Image img = Image.createImage(i);
//                         picture.setIcon(img);
//                         current.revalidate();
//                  } catch (Exception e) {
//                  }
//                 }}} );
//          
          
          
          
         
          tphoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 try {
                    imgPath = Capture.capturePhoto();
                    System.out.println(imgPath);
                    String link=imgPath.toString();
                    int pod=link.indexOf("/",2);
                    String news=link.substring(pod + 2, link.length());
                    System.out.println(""+news);
                    FileUploader fu = new FileUploader("http://localhost/final_pi/web/uplods/");
                    fileNameInServer = fu.upload(news);
                    
                    pathphoto.setText(fileNameInServer);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
//            @Override
//            public void actionPerformed(ActionEvent evt) {}
              
          });
         
      
         
         
         
         
         
         
         
        
         
         
         
         
         
        
         
      
       
         
         
         
    }
        }

    
    
    

