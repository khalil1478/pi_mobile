/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.ServiceCom;
import com.mycompany.myapp.services.ServicePost;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class DetailsForm extends Form {
  Form current;
  
  
    public DetailsForm(Commentaire C, Post P,fos_user u) {
        
         current=this;
        setTitle("détails post");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ListPostForm(current).show());
     
        
         Label Title = new Label(P.getTitle()+"  ");
            Label Description = new Label(P.getDescription()+ "");
            Label b = new Label(P.getRating()+ "");
              
        
         Button Add = new Button("Ajouter Commentaire");
                     
        
      
                       // Form current;
                       // current=this;
                        Add.addActionListener(new ActionListener() {
                   @Override
         public void actionPerformed(ActionEvent evt) {
                
          new AddComFrom(u,P,C).show();
            }} );
                       // new AddPost(current,res).show();
                        add(Add);
                        
//    
//        for (Commentaire cv : ServiceCom.getInstance().getAllTasks(P)){
//   //     Label lbcontent=new Label();
//       // lbcontent.setText("Titre: "+cv.getContent());
//          Label lbcontent = new Label(cv.getContent()+ "");
//        
        
    
//         add(lbcontent);
        




  add(Title);
            add(Description);
            add(b);
    
      
    }
        
        
                     
         
        
      
    
}
