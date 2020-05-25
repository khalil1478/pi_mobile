/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author USER
 */
public class LoginForm  extends Form{
 Form current;
    public LoginForm() {
        current=this;
         setTitle("login");
        setLayout(new FlowLayout(CENTER,CENTER));
        Container cnt4 = new Container(BoxLayout.y());
        TextField text1 = new TextField();
         TextField text2 = new TextField();
         Button login  = new Button("Login");
          Button loginfc  = new Button("connectez avec facebook");
          
          cnt4.addAll(text1,text2,login,loginfc);
          add(cnt4);
          show();
          
        
         
         
 
}
}