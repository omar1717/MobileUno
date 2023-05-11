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
 * @author Yessine
 */
public class HouseUpdate extends Form{
     
    public HouseUpdate(Resources theme) {
    Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    //super(new BorderLayout());
    this.setTitle("House Update");
    TextField tfid = new TextField("","Enter House id to Update");
    TextField tfNom = new TextField("","Enter Name");
    TextField tfprenom = new TextField("","Enter prenom");
     Picker datePicker = new Picker();
    datePicker.setType(Display.PICKER_TYPE_DATE);
    TextField tfdetail = new TextField("","detail ");
    TextField tfgenre = new TextField("","genre");   
    TextField Kekw = new TextField("","Enter the description");
    TextField tfurl = new TextField("","url");
   
    Button btn = new Button("Valider");
    //this.add(tfNom);
    //this.add(tfNum);
    //this.add(btn);
    C.add(tfid);
    C.add(tfNom);
    C.add(tfprenom);
    C.add(Kekw);
    C.add(tfdetail);
    C.add(tfgenre);
    C.add(tfurl);
    C.add(datePicker);
    C.add(btn);
    this.add(C);
  
    //buttons
    btn.addActionListener((ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt1) {                     
             System.out.println("Kekw.getText()   :: "+Kekw.getText());
             System.out.println("");
             int id = Integer.parseInt((String)tfid.getText());
             artist l = new artist(id, tfNom.getText(), tfprenom.getText(), tfdetail.getText(), tfgenre.getText(), Kekw.getText());
             System.out.println(" c :: "+l);
             System.out.println(id);
             try {
                
             //Check Existance 
            
                //Existed
                if (tfid.getText().isEmpty() ) 
                     {
                Dialog.show("Alert", "Please enter the id you want to update", new Command("OK"));
                // Display an error message 
                Dialog.show("House","Exist" ,"Ok", null); 
                
                HouseList HL1 = new HouseList(theme);
                HL1.show();
                }
                else
                {
                     
                     
                    if (ArtServ.getInstance().updateART(l, id)) {
                        Dialog.show("Success", "Artist modified  successfully", new Command("OK"));
                        // Clear form fields
                        tfid.clear();
                        tfNom.clear();
                        tfprenom.clear();
                        tfdetail.clear();
                     
                        tfurl.clear();
                        tfgenre.clear();
                    // Clear the text fields
                    tfNom.setText("");
                    tfprenom.setText("");

                    
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
    
    
   HouseUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
