/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wakalni;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Salle;
import com.mycompany.services.ServiceSalle;
import java.util.ArrayList;

/**
 *
 * @author malek guemri
 */
public class SalleForm extends SideMenuBaseForm {

    public SalleForm(Resources res) {

        super(BoxLayout.y());
        setUIID("Toolbar");
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("01.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("John", "Title"),
                                new Label("John@esprit.tn", "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel)
        );

        tb.setTitleComponent(titleCmp);

        add(new Label("Salles", "TodayTitle"));
        ArrayList<Salle> salle = ServiceSalle.getInstance().affichageSalle();

        
        for (Salle r : salle) {
            r.toString();
            TextField type = new TextField(r.getType(), "", 20, TextField.EMAILADDR);
            TextField cin = new TextField(String.valueOf(r.getCin()), "", 20, TextField.EMAILADDR);
            type.setEnabled(false);
            cin.setEnabled(false);
            Button delButton = new Button("Delete");
            delButton.addActionListener(e -> {
                if (Dialog.show("Warning", "The Salle will be permanently deleted, Are you sure ?", "Ok", "Cancel")) {
                    ServiceSalle.getInstance().deleteSalle(r.getNbs());
                    new SalleForm(res).show();
                } 
            });
            Button modButton = new Button("Modify");
            modButton.addActionListener(e -> {
                new SalleEditForm(r.getNbs(),res).show();
            });
            type.getAllStyles().setMargin(LEFT, 0);
            cin.getAllStyles().setMargin(LEFT, 0);
            Label nomIcon = new Label("Type :", "TextField");
            Label cinIcon = new Label("CIN :", "TextField");
            nomIcon.getAllStyles().setMargin(RIGHT, 0);
            Container by = BoxLayout.encloseY(
                    BorderLayout.center(type).
                    add(BorderLayout.WEST, nomIcon),
                    BorderLayout.center(cin).
                    add(BorderLayout.WEST, cinIcon),
                    BoxLayout.encloseX(delButton,modButton)
            );
            add(by);
        }

        setupSideMenu(res);
    }
    
}
