/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
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
public class acc extends Form{
    public acc(Resources theme)
    {
        super("acceuil", BoxLayout.y());
        
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
            this.getToolbar().addCommandToLeftBar("Artist", null, (ActionListener) (ActionEvent evt) -> {
        Menu m=new Menu(theme);
        m.showBack();
        });
      this.getToolbar().addCommandToSideMenu("Display", null, (ActionListener) (ActionEvent evt) -> {
        HouseList HL = new HouseList(theme);
        HL.show();
        });
         this.getToolbar().addCommandToSideMenu("addddd", null, (ActionListener) (ActionEvent evt) -> {
        HouseAdd Hq = new HouseAdd(theme);
        Hq.show();
        });
        this.getToolbar().addCommandToSideMenu("update", null, (ActionListener) (ActionEvent evt) -> {
        HouseUpdate Hu = new HouseUpdate(theme);
        Hu.show();
        });
        this.getToolbar().addCommandToSideMenu("Delete", null, (ActionListener) (ActionEvent evt) -> {
        HouseDel HD = new HouseDel(theme);
        HD.show();
        });
        this.getToolbar().addCommandToSideMenu("search", null, (ActionListener) (ActionEvent evt) -> {
        search Hh = new search(theme);
        Hh.show();
        });
        this.getToolbar().addCommandToSideMenu(new Command("About"));
        this.getToolbar().addCommandToSideMenu(new Command("Exit"));
    }
       
    
}
