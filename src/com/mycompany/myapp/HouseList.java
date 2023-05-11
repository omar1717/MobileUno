/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import entity.artist;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.util.Resources;
import entity.artist;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import service.ArtServ;

/**
 *
 * @author Yessine
 */
public class HouseList extends Form{
  
    ArrayList<artist> houses = new ArrayList<>();
    // Create a new DefaultListModel for the MultiList
     //It provides a simple way to store and manage a list of objects
    public HouseList(Resources theme) {
        super("Database Rows", BoxLayout.y());
        this.setTitle("House List");
        
        
        
        
         this.RenderHouses();
        //getToolbar
        
        //Add
        this.getToolbar().addCommandToRightBar("Add", null, (ActionListener) (ActionEvent evt) -> {
        HouseAdd HA = new HouseAdd(theme);
        HA.show();
        });
        
        //Update
        this.getToolbar().addCommandToSideMenu("Update", null, (ActionListener) (ActionEvent evt) -> {
        HouseUpdate HU = new HouseUpdate(theme);
        HU.show();                
        });        
        
        //Delete
        this.getToolbar().addCommandToSideMenu("Delete", null, (ActionListener) (ActionEvent evt) -> {
        HouseDel HD = new HouseDel(theme);
        HD.show();                
        });  
        
        //Back
        this.getToolbar().addCommandToLeftBar("", theme.getImage("icons8-back-arrow-90.png"), (ActionListener) (ActionEvent evt) -> {
        acc c= new acc(theme);
        c.showBack();
        }); 
    }
    //Methods
     public void RenderHouses()
     {
                     ArrayList<artist> list = ArtServ.getInstance().affichageArtist();
       
                     for (artist rec : list ) {
                         
                         
                         //String Name = "Name: " + row.getString(0);
                         //String Price = "Price: " + row.getString(1);
                         
                         artist h = new artist(rec.getNom_Ar(),rec.getPrenom_Ar());
                         if (!(houses.contains(h)))
                         {
                              System.out.println("Artist Name: " + h.getNom_Ar());
                             houses.add(h);
                             String str1 = String.valueOf(h.getId_Ar());
                             Label labelid = new Label(str1);
                             Label labelName = new Label(h.getNom_Ar());
                             Label labelSurname = new Label(h.getPrenom_Ar());
                             Label labeldetail = new Label(h.getDetail_Ar());
                             Label labelgenre = new Label(h.getGenre_Ar());
                             Label labeldesc = new Label(h.getDesc_ar());
                             Label labelurl = new Label(h.getUrl_ar());
                             Label labeldate = new Label(h.getDateStr());
                             
                            /* labelName.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
                                 Dialog.show("Artist","Id : "+ labelid.getText() +"\n Nom : " + labelName.getText() + " \n prenom : " + labelSurname.getText() +  "\n detail : " + labeldetail.getText() + " \n genre : " + labelgenre.getText() + "\n description : " + labeldesc.getText(), "Ok", null);
                             });*/
                             
                             this.add(labelName);
                             this.add(labelSurname);
                             this.add(labeldetail);
                             this.add(labelgenre);
                             this.add(labeldesc);
                            
                             
                             
                             
                         }
                         else {
                             Dialog.show("no artist yet","OK",null);
                         }
                         
                         
                         
                     }
                     // Close the cursor and the database
     }
     
}
