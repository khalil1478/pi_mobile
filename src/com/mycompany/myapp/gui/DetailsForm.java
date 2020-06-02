/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Log;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.fos_user;
import static com.mycompany.myapp.pi_mobile.theme;
import com.mycompany.myapp.services.ServiceCom;
import com.mycompany.myapp.services.ServicePost;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author USER
 */
public class DetailsForm extends Form {
  Form current;
   private EncodedImage placeHolder ;
           Hashtable themeData = theme.getTheme("Theme");
  
  
    public DetailsForm(Commentaire C, Post P,fos_user u) {
        System.out.println(u);
         current=this;
        setTitle("détails post");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ListPostForm(current,u).show());
       setLayout(BoxLayout.y());
        Container ctnpost= new Container(BoxLayout.y());
        
        
        placeHolder = EncodedImage.createFromImage(theme.getImage("Point_d_interrogation.jpg"), false);   
          String url="http://localhost/final_pi/web/uplods/post/"+P.getPhoto();
      Image image1=URLImage.createToStorage(placeHolder, url, url,URLImage.RESIZE_SCALE);
       ImageViewer img=new ImageViewer(image1);   
       img.setPreferredSize(new Dimension(400,800));
       
         Label Title = new Label("titre est   : "+P.getTitle());
            Label Description = new Label("description  est   : "+P.getDescription());
            Label b = new Label("rating   est   : "+P.getRating());
              ctnpost.addAll(img,Title,Description,b);
              
        
         Button Add = new Button("Ajouter Commentaire");
                     
        
      
                       // Form current;
                       // current=this;
                        Add.addActionListener(new ActionListener() {
                   @Override
         public void actionPerformed(ActionEvent evt) {
                       System.out.println(u);
          new AddComFrom(u,P,C).show();
            }} );
                       // new AddPost(current,res).show();
                       
      
     
         Label lbcc=new Label("    les commentaires  : \n");      
      Container ctncomment = new Container(BoxLayout.y());
      Container ctncomment1 = new Container(BoxLayout.x());
        for (Commentaire cv : ServiceCom.getInstance().getAllTasks(P)){
       int idcomment = cv.getId();
       Label lbcontent=new Label();
        Label lbidcomment=new Label();
       Button modcomment = new Button("Modifier  ");
       Button suppcomment = new Button("supprimer    ");
       

     
       //  ctncomment1.addAll(modcomment,suppcomment);
      
      
      
    
        
       
     
       
        lbcontent.setText(cv.getContent());
    //    lbidcomment.setText("id   :"+idcomment);
       
        
        ctncomment.addAll(lbcontent,modcomment,suppcomment);
        //  Label lbcontent = new Label(cv.getContent()+ "");
        
        
    modcomment.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
           
               
               Form me=new Form("Modifier commentaire ",BoxLayout.y());

                  Button btnModifierComment = new Button("Modifier commentaire");

   
                 TextField mtfcontenu = new TextField(cv.getContent(),"contenu");
                 
                 
                 
          btnModifierComment.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent evt) {
                       
                  if ((mtfcontenu.getText().length()==0) )
                     Dialog.show("Alert", "Please fill all the fields","ok",null);  
               
                else 
                {
                    
                    Commentaire ev = new Commentaire(idcomment,mtfcontenu.getText());
                    
                    if(  ServiceCom.getInstance().ModCom(ev)){
                        
                        
                         Dialog.show("Success", "Vous avez modifié commentaire "+ev.getContent(),"ok",null); 
                   
                        }
                    else{
                        Dialog.show("Success", "Vous avez modifié commentaire "+ev.getContent(),"ok",null); 
                 
                        }
                  new DetailsForm(cv,P,u).show();

                  }                                                 
                }   
                });
                 
             me.addAll(mtfcontenu,btnModifierComment);
           me.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> current.showBack());
             me.show();      
           }
       });
    
    
    suppcomment.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               
               
                if( ServiceCom.getInstance().SuppCom(idcomment))
                {
                         Dialog.show("Success", "Vous avez supprimé   commentaire ","ok",null); 
                        }
                    else{
                            Dialog.show("Success", "Vous avez supprimé  commentaire ","ok",null); 
                        }
                       new DetailsForm(cv,P,u).show();
              
           }
       });
    
    
    
    
    
    
    
       
        




        
      
    }
        
        addAll(ctnpost,lbcc,ctncomment,Add);
        
           
    
        
        
                     
         
        
      
    
}
}