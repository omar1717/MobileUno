/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.SessionManager;
import com.mycompany.utils.Statics;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Lenovo
 */
public class ServiceUtilisateur {
    
    
  //singleton 
    public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiceUtilisateur();
        return instance ;
    }
    
    
    
    public ServiceUtilisateur() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField nom, TextField prenom ,TextField age, TextField email,TextField password  ) {
        
     
        
        String url = Statics.BASE_URL+"/add_register?Nom="+nom.getText().toString()+"&Prenom="+prenom.getText().toString()+
                "&age="+age.getText().toString()+"&email="+email.getText().toString()+"&password="+password.getText().toString();
        
        req.setUrl(url);
       
     
     
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
    }
    
    
    
    
     public void signin(TextField username,TextField password ) {
        
        
        String url = Statics.BASE_URL+"/SignIn_User_Mobile?email="+username.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("User not found")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setNom(user.get("nom").toString());
                SessionManager.setPrenom(user.get("prenom").toString());
                SessionManager.setEmail(user.get("email").toString());
                SessionManager.setage(user.get("age").toString());
                //photo 
                if(user.get("photo") != null)
                { SessionManager.setPhoto(user.get("photo").toString());}
             
                
                 Dialog.show("Success", "User Logged successfully", "OK", null);
                
                
                
                
                
                System.out.println("Current user " + SessionManager.getEmail()+" "+ SessionManager.getPassowrd()+" "+SessionManager.getPhoto() );
                
                
                    
                    }
            
            }catch(IOException | NumberFormatException ex) {
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
     
        public boolean deleteUser(int id ) {
        String url = Statics.BASE_URL +"/delete_User_Mobile/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
     try {
        NetworkManager.getInstance().addToQueueAndWait(req);
    } catch (NullPointerException ex) {
        // Log the error message to the console
        System.out.println("Error: " + ex.getMessage());
    }
        return  resultOk;
    }
     
//Signup
public void update(int id, TextField nom, TextField prenom, TextField age, TextField email) {

    String url = Statics.BASE_URL + "/mod_User_Mobile?id=" + id + "&Nom=" + nom.getText().toString() + "&Prenom=" + prenom.getText().toString() +
            "&age=" + age.getText().toString() + "&email=" + email.getText().toString();

    req.setUrl(url);

    req.addResponseListener((e) -> {
        byte[] data = (byte[]) e.getMetaData();
        String responseData = new String(data);
        System.out.println("data ===>" + responseData);
    });

    try {
        NetworkManager.getInstance().addToQueueAndWait(req);
    } catch (NullPointerException ex) {
        // Log the error message to the console
        System.out.println("Error: " + ex.getMessage());

        // Display a user-friendly error message to the user
 
    }
}

    
    public static boolean isValidEmail(String email) {
    if (email == null || email.trim().length() == 0) {
        return false;
    }
    int atIdx = email.indexOf("@");
    int dotIdx = email.lastIndexOf(".");
    if (atIdx < 1 || dotIdx < atIdx + 2 || dotIdx + 2 >= email.length()) {
        return false;
    }
    return true;
} 
}