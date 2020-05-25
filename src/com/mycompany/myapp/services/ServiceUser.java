/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class ServiceUser{
    
  public ArrayList<Post> posts;
    
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public boolean addUser(fos_user t)
    {
        
     String url = Statics.BASE_URL + "/users/" +t.getUsername()+"/"+ t.getUsername_canonical()+ "/" + t.getEmail()+"/"+t.getEmail_canonical()+"/"+ t.getEnabled()+"/"+t.getPassword()+"/"+t.getRoles()+"/"+t.getFirstname()+"/"+t.getAge()+"/"+t.getTelephone()+"/"+t.getPhoto();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() 
        {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;   
    }
    
    
    
    public ArrayList<Post> parseTasks(String jsonText){
        try {
            posts=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Post t = new Post();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setRating(((int)Float.parseFloat(obj.get("rating").toString())));
                t.setDescription(obj.get("description").toString());
            posts.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return posts;
    }
    
    
     public ArrayList<Post> getAllPosts(){
        String url = Statics.BASE_URL+"/posts/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                posts = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return posts;
    }
    
    
    
    
    
}
