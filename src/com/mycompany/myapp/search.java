/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import entity.artist;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import entity.artist;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import entity.artist;
import service.ArtServ;

/**
 *
 * @author yessine
 */
public class search extends Form{
    public search(Resources theme) {
    Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    //super(new BorderLayout());
    this.setTitle("House Update");
    TextField tfid = new TextField("","Enter House id to Update");
     Button btn = new Button("Valider");
     C.add(btn);
     C.add(tfid);
     this.add(C);
     
     
      btn.addActionListener((ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt1) {                     
             
             int id = Integer.parseInt((String)tfid.getText());
             artist l = new artist(id);
             System.out.println(" c :: "+l);
             System.out.println(id);
             try {
                
             //Check Existance 
            
                //Existed
                if (tfid.getText().isEmpty() ) 
                     {
                Dialog.show("Alert", "Please enter the id you want to update", new Command("OK"));
                // Display an error message 
                 
                
                HouseList HL1 = new HouseList(theme);
                HL1.show();
                }
                else
                {
                     
                     
                    ArtServ.getInstance().findByid(id);
                        
                        // Clear form fields
                        tfid.clear();
                      

                    
                    HouseList HL2 = new HouseList(theme);
                    HL2.show();
                } 
                
            }
            catch (Exception ex) {
                ex.printStackTrace();
                
                // Display an error message
                Label errorLabel = new Label("Error Removing row: " + ex.getMessage());
                add(errorLabel);
            }
        }
    });
    
    this.getToolbar().addCommandToLeftBar("", theme.getImage("icons8-back-arrow-90.png"), (ActionListener) (ActionEvent evt) -> {
        HouseList HL1 = new HouseList(theme);
        HL1.showBack();
        }); 
   
    
}

     search() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}