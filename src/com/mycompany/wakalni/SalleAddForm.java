/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wakalni;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
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


/**
 *
 * @author malek guemri
 */
public class SalleAddForm extends SideMenuBaseForm {

    public SalleAddForm(Resources res) {

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
        TextField type = new TextField("", "", 20, TextField.EMAILADDR);
        TextField cin = new TextField("", "", 20, TextField.EMAILADDR);
        TextField nsalle = new TextField("", "", 20, TextField.EMAILADDR);

        Button addButton = new Button("ADD");
        addButton.addActionListener(e -> {
            if (Dialog.show("Succes", "The salle will be added", "Ok", "Cancel")) {
                ServiceSalle.getInstance().ajouterSalle(new Salle(nsalle.getText(),type.getText(),Integer.parseInt(cin.getText())));
                new SalleForm(res).show();
            } else {
                new SalleForm(res).show();
            }
        });
        type.getAllStyles().setMargin(LEFT, 0);
        Label nomIcon = new Label("Salle :", "TextField");
        Label cinIcon = new Label("CIN :", "TextField");
        Label typeIcon = new Label("Numero salle :", "TextField");
        Container by = BoxLayout.encloseY(
                BorderLayout.center(type).
                        add(BorderLayout.WEST, typeIcon),
                BorderLayout.center(nsalle).
                        add(BorderLayout.WEST, nomIcon),
                 BorderLayout.center(cin).
                        add(BorderLayout.WEST, cinIcon),
                addButton
        );
        add(by);

        setupSideMenu(res);

    }
    
}
