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
import com.mycompany.entities.Event;
import com.mycompany.services.ServiceEvent;
import java.util.ArrayList;

/**
 *
 * @author malek guemri
 */
public class EventForm extends SideMenuBaseForm {

    public EventForm(Resources res) {

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

        add(new Label("Events", "TodayTitle"));
        ArrayList<Event> questions = ServiceEvent.getInstance().affichageEvent();

        for (Event r : questions) {
            TextField nbp = new TextField(String.valueOf(r.getNbp()), "", 20, TextField.EMAILADDR);
            TextField type = new TextField(r.getType(), "", 20, TextField.USERNAME);
            TextField salle = new TextField(String.valueOf(r.getSalle()), "", 20, TextField.USERNAME);
            TextField dd = new TextField(r.getDd(), "", 20, TextField.EMAILADDR);
            TextField df = new TextField(r.getDf(), "", 20, TextField.USERNAME);
            TextField nom = new TextField(r.getNom(), "", 20, TextField.USERNAME);
            nbp.setEnabled(false);
            type.setEnabled(false);
            salle.setEnabled(false);
            dd.setEnabled(false);
            df.setEnabled(false);
            nom.setEnabled(false);
            System.out.println(r.getNom());
            Button delButton = new Button("Delete");
            delButton.addActionListener(e -> {
                if (Dialog.show("Warning", "The Event will be permanently deleted, Are you sure ?", "Ok", "Cancel")) {
                    ServiceEvent.getInstance().deleteEvent(r.getNom());
                    new EventForm(res).show();
                }
            });
            Button modButton = new Button("Edit");
            modButton.addActionListener(e -> {
                System.out.print(r.getNom());
                new EventEditForm(r.getNom(),res).show();
            });
            nbp.getAllStyles().setMargin(LEFT, 0);
            type.getAllStyles().setMargin(LEFT, 0);
            salle.getAllStyles().setMargin(LEFT, 0);
            dd.getAllStyles().setMargin(LEFT, 0);
            df.getAllStyles().setMargin(LEFT, 0);
            Label typeIcon = new Label("Type :", "TextField");
            Label nbpIcon = new Label("Nombre de place :", "TextField");
            Label salleIcon = new Label("Salle :", "TextField");
            Label ddIcon = new Label("Date de debut :", "TextField");
            Label dfIcon = new Label("Date de fin :", "TextField");
            Label nomIcon = new Label("Event name :", "TextField");
            Container by = BoxLayout.encloseY(
                    BorderLayout.center(nom).
                            add(BorderLayout.WEST, nomIcon),
                    BorderLayout.center(type).
                            add(BorderLayout.WEST, typeIcon),
                    BorderLayout.center(salle).
                            add(BorderLayout.WEST, salleIcon),
                    BorderLayout.center(nbp).
                            add(BorderLayout.WEST, nbpIcon),
                    BorderLayout.center(dd).
                            add(BorderLayout.WEST, ddIcon),
                    BorderLayout.center(df).
                            add(BorderLayout.WEST, dfIcon),
                    BoxLayout.encloseX(delButton, modButton)
            );
            add(by);
        }

        setupSideMenu(res);
    }

}
