/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.ServicePost;

/**
 *
 * @author USER
 */
public class ListUserForm extends Form{

    public ListUserForm(Form current) {
        
         setTitle("List des utilisateur");
        
        SpanLabel sp = new SpanLabel();
       
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> current.showBack());
    }
    
}
