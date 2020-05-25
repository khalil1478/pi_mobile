/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.*;
import com.codename1.l10n.DateFormat;
import com.codename1.ui.*;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.ServicePost;
import java.util.Date;

/**
 *
 * @author USER
 */
public class ListPostForm extends Form{
    

    public ListPostForm(Form previous)
    {
   
     
        setTitle("List post");
        for (Post ev : ServicePost.getInstance().getAllPosts()){
            int IdE=ev.getId();
             Label lbDateE=new Label();
            Label lbTitre=new Label();
            Label lbRating=new Label();
            Label lbDescription=new Label();
           
            Label lbNbr=new Label();
         
            Container cnt1 = new Container(BoxLayout.x());
            Container cnt2 = new Container(BoxLayout.y());
            
            lbTitre.setText("Titre: "+ev.getTitle());
            lbDateE.setText("Date de : "+ev.getPostdate());
            lbDescription.setText("Description: "+ev.getDescription());
            lbRating.setText("rating: "+ev.getRating());
            lbNbr.setText("nbr post: "+ev.getNbrpost());
        
       //     Button participer=new Button("Participer");
            Button modifier=new Button("Modifier");
            Button details=new Button("Details");
            Button supprimer=new Button("Supprimer");
            cnt1.addAll(modifier,details,supprimer);
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            //methode modifier 
            modifier.addActionListener(a->{
               Form me=new Form("Modifier post ",BoxLayout.y());



   
                 TextField mtfTitre = new TextField(ev.getTitle(),"Titre");
           //     Picker    mdpDateE= new Picker();
                TextField mtfDescription=new TextField(ev.getDescription(),"Description");
                TextField mtfRating=new TextField(Integer.toString(ev.getRating()),"rating ");
                Container mcnt = new Container(BoxLayout.y());
               Label labelid = new Label (Integer.toString(ev.getId()));
        Label nbrating = new Label("Nombre de Rating : "+Integer.toString(ev.getRating()));
                Slider mnbrating = new Slider();
                mcnt.add(nbrating);
                mcnt.add(mnbrating);
                mcnt.add(labelid);
                mnbrating.setEditable(true);
                mnbrating.setMinValue(0);
                mnbrating.setMaxValue(5);
                
                
                Button btnModifier = new Button("Modifier post");
        
                btnModifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                if ((mtfTitre.getText().length()==0)||(mtfDescription.getText().length()==0) )
                     Dialog.show("Alert", "Please fill all the fields","ok",null);  
               
                else 
                {
                    
                    Post ev = new Post(IdE,mtfTitre.getText(),mtfDescription.getText(),mnbrating.getProgress());
                    
                    if( ServicePost.getInstance().modifier(ev)){
                        
                        
                         Dialog.show("Success", "Vous avez modifié post "+ev.getTitle(),"ok",null); 
                   
                        }
                    else{
                        Dialog.show("ERROR","Veuillez réessayer","ok",null); 
                 
                        }
                  new ListPostForm(previous).show();

                  }                                                 
                }   
                });
                 
       
       me.addAll(mtfTitre,mtfDescription,mcnt,btnModifier);
           me.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
             me.show();        
                
            });
            supprimer.addActionListener(b->{
                    if( ServicePost.getInstance().deletePost(ev)){
                         Dialog.show("Success", "Vous avez modifié post "+ev.getTitle(),"ok",null); 
                        }
                    else{
                           Dialog.show("ERROR","Veuillez réessayer","ok",null); 
                        }
                      new ListPostForm(previous).show();
            });
            cnt2.addAll(lbTitre,lbDateE,lbDescription,lbNbr,lbRating,cnt1);
            add(cnt2);
            show();
        









Commentaire Cmm =new Commentaire();            
  
fos_user  Us = new fos_user();
          
            details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new DetailsForm(Cmm,ev,Us).show();
                }
            });
            
           
            
            
            
            
            
            
            
            
            
            
            }
  
     
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    
    }
    
}
