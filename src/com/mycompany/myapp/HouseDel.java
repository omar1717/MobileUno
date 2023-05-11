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
 * @author Walid Jlassi
 */
public class HouseDel extends Form{
    Database db;
    public HouseDel(Resources theme) {
    Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    //super(new BorderLayout());
    this.setTitle("House Remove");
    TextField tfNom = new TextField("","Enter House ID to Delete");
    Button btn = new Button("Valider");
    C.add(tfNom);
    C.add(btn);
    this.add(C);
  
    //buttons
    btn.addActionListener((ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt1) {
            int id = Integer.parseInt((String)tfNom.getText());
            artist l = new artist(id);
            System.out.println(" c :: "+l);
            System.out.println(id);
                            
                try {
                    
              //Check Existance 
               
                //Existed
                if (tfNom.getText().isEmpty() ) 
                     {
                Dialog.show("Alert", "Please enter the id you want to update", new Command("OK"));
                // Display an error message 
                Dialog.show("House","Exist" ,"Ok", null); 
                
                HouseList HL = new HouseList(theme);
                     HL.showBack();
                }
                
                    
                 
                  else
                    {
                     
                     
                    if (ArtServ.getInstance().deleteTicket(id)) {
                        Dialog.show("Success", "Artist delted  successfully", new Command("OK"));
                        // Clear form fields
                       
                        tfNom.clear();
                      
                    // Clear the text fields
                    tfNom.setText("");
                    

                    
                    HouseList HL2 = new HouseList(theme);
                    HL2.show();
                } else {
                        Dialog.show("Error", "Failed to add product", new Command("OK"));
                    }
                
            }}
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
}
