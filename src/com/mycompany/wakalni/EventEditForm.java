/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wakalni;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Event;
import com.mycompany.entities.Salle;
import com.mycompany.services.ServiceEvent;
import com.mycompany.services.ServiceSalle;

/**
 *
 * @author malek guemri
 */
public class EventEditForm extends SideMenuBaseForm {

    public EventEditForm(String id, Resources res) {

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
        
        ComboBox<String> salle = new ComboBox();
        for (Salle s : ServiceSalle.getInstance().affichageSalle()) {
            salle.addItem(s.getNbs());
        }
        Event ev = ServiceEvent.getInstance().getEvent(id);
        
        Picker dated = new Picker();
        dated.setType(Display.PICKER_TYPE_DATE);
        
        Picker datef = new Picker();
        dated.setType(Display.PICKER_TYPE_DATE);
        
        tb.setTitleComponent(titleCmp);
        TextField nbp = new TextField(String.valueOf(ev.getNbp()), "", 20, TextField.EMAILADDR);
        TextField typr = new TextField(ev.getType(), "", 20, TextField.USERNAME);
        TextField nom = new TextField(ev.getNom(), "", 20, TextField.USERNAME);

        Button addButton = new Button("Modify");
        addButton.addActionListener(e -> {
            if (Dialog.show("Succes", "The Event will be added", "Ok", "Cancel")) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                ServiceEvent.getInstance().editEvent(new Event(id,typr.getText(), Integer.parseInt(salle.getSelectedItem()),format.format(dated.getDate()), format.format(datef.getDate()),Integer.parseInt(nbp.getText())));
                new EventForm(res).show();
            } 
        });
        nbp.getAllStyles().setMargin(LEFT, 0);
        typr.getAllStyles().setMargin(LEFT, 0);
        dated.getAllStyles().setMargin(LEFT, 0);
        datef.getAllStyles().setMargin(LEFT, 0);
        salle.getAllStyles().setMargin(LEFT, 0);
        nom.getAllStyles().setMargin(LEFT, 0);
        Label typeIcon = new Label("Event type :", "TextField");
        Label salleIcon = new Label("Salle :", "TextField");
        Label ddIcon = new Label("Start date :", "TextField");
        Label dfIcon = new Label("End date :", "TextField");
        Label nbpIcon = new Label("Number of places :", "TextField");
        Label nomIcon = new Label("Nom :", "TextField");
        Container by = BoxLayout.encloseY(
                BorderLayout.center(nom).
                        add(BorderLayout.WEST, nomIcon),
                BorderLayout.center(typr).
                        add(BorderLayout.WEST, typeIcon),
                BorderLayout.center(salle).
                        add(BorderLayout.WEST, salleIcon),
                BorderLayout.center(dated).
                        add(BorderLayout.WEST, ddIcon),
                BorderLayout.center(datef).
                        add(BorderLayout.WEST, dfIcon),
                BorderLayout.center(nbp).
                        add(BorderLayout.WEST, nbpIcon),
                addButton
        );
        add(by);

        setupSideMenu(res);

    }
}
