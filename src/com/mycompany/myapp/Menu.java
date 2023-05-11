/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.HashMap;

/**
 *
 * @author Walid Jlassi
 */
public class Menu extends Form{
    public Menu(Resources theme)
    {
        super("Menu", BoxLayout.y());
        
        this.setTitle("Menu");
        this.add(new Label("Welcome To Uno App"));
        
        ImageViewer img = new ImageViewer(theme.getImage("un.jpg"));
        
        //---- Containers (Classify our image )------
        //Declaration
        Container cn1 = new Container (new FlowLayout(CENTER)); // Align items ( center )
        //Input ( Add images to container )
        cn1.add(img);       
        this.add(cn1);
                         
        //Add Commands To Side Menu 
            
         this.getToolbar().addCommandToSideMenu("Artist", null, (ActionListener) (ActionEvent evt) -> {
       acc c= new acc(theme);
       c.show();
       
        }); 
        this.getToolbar().addCommandToSideMenu(new Command("User"));
        this.getToolbar().addCommandToSideMenu(new Command("Produit"));
        this.getToolbar().addCommandToSideMenu(new Command("Journaliste"));
        this.getToolbar().addCommandToSideMenu(new Command("event"));
        
        
    }
       
    
}
