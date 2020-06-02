/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.ui.*;
import static com.codename1.ui.CN.CENTER;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.fos_user;

/**
 *
 * @author USER
 */
public class HomeForm  extends Form{
    
    Form current;
    public HomeForm(fos_user u)
          
    {
        
     
        current=this;
        setTitle("Home");
        setLayout(new FlowLayout(CENTER,CENTER));
        Container cnt4 = new Container(BoxLayout.y());
        
        Label lb = new Label("Choose an option");
        
        
     Style s = UIManager.getInstance().getComponentStyle("Title");
//FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_MIC, s);
Label picture = new Label("", "Container");


        Button addpost  = new Button("Add post");
        Button listpost = new Button("List post");
       // Button  listcommentaire = new Button("List commentaire");
        Button  listutilisateur = new Button("List utilisateur");
        Button  capturephoto = new Button("prenez une photo");
        addpost.addActionListener(e-> new AddPostForm(current,u).show() );
        listpost.addActionListener(e-> new ListPostForm(current,u).show() );
      //  listcommentaire.addActionListener(e-> new ListCommentForm(current).show() );
        listutilisateur.addActionListener(e-> new ListUserForm(current).show() );
        cnt4.addAll(lb,addpost,listpost,listutilisateur,capturephoto,picture);
        add(cnt4);
        
        
        capturephoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
              String i=  Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
              if(i!=null)
              {
                  
                  try {
                      Image img = Image.createImage(i);
                         picture.setIcon(img);
                         current.revalidate();
                  } catch (Exception e) {
                  }
                 }}} );
        
        
        
        
        
        
        
        
        
    
    
    }
    
}
