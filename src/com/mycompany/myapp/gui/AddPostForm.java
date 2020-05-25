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
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.services.ServicePost;
import rest.file.uploader.tn.FileUploader;



/**
 *
 * @author USER
 */
public class AddPostForm extends Form
{
private FileUploader file;
String fileNameInServer; 
private String imgPath;
Post pass=new Post();
    AddPostForm(Form current) {
        
        
        setTitle("addpost");
        setLayout(BoxLayout.y());
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> current.showBack());
         
         
         Label lab1= new Label("saisir  un titre");
         TextField texttitre = new TextField();
         Label lab2 = new Label("saisir  une description");  
         TextField textdescription= new TextField();
         Label lab3 = new Label("saisir  rating de 1 à 5");
         TextField textrating = new TextField();
         Button tphoto = new Button("télécharger photo");
         Button btn = new Button("add post");
         
        addAll(lab1,texttitre,lab2,textdescription,lab3,textrating,tphoto,btn);
         
            //  addAll(lab1,texttitre,lab2,textdescription,lab3,textrating,btn);
        
       
         
tphoto.addActionListener((evt) -> {
     String il =Capture.capturePhoto();
                String i =Capture.capturePhoto().toString();
                int num=i.indexOf("/",2);
                String news=i.substring(num + 2,i.length());
                System.out.println(""+news);
                                System.out.println(""+i);
                FileUploader fu=new FileUploader("http://localhost/final_pi/web/uploads/post");
            try {
                fileNameInServer=fu.upload(news);
                System.out.println("aaa"+fileNameInServer);
                pass.setPhoto(fileNameInServer);
            } catch (Exception ex) {
            }
});
         
         
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
                        Post t = new Post(texttitre.getText(),textdescription.getText(),Integer.parseInt(textrating.getText()),"/uploads/"+pass.getPhoto());
                   //       Post t = new Post(texttitre.getText(),textdescription.getText(),Integer.parseInt(textrating.getText()));
                         System.out.println(t);
                        if( ServicePost.getInstance().addPost(t))
                            Dialog.show("Success","Connection accepted","ok",null);
                        else
                            Dialog.show("ERROR", "Server error", "ok",null);
                    }
                    catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number","ok",null);
            }
                    
                }
                
                
            }
        });
         
         
         
         
         
        
         
         
         
         
         
         
         
        
         
         
         
         
         
        
         
      
       
         
         
         
    }

    
    
    
}
