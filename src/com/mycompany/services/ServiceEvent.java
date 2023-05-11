/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Event;
import com.mycompany.statics.statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author malek guemri
 */
public class ServiceEvent {
    public boolean resultOK = true;

    public static ServiceEvent instance = null;

    public static NetworkManager instances;

    private ConnectionRequest req;

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }

    public ServiceEvent() {
        req = new ConnectionRequest();
    }

    public void ajouterEvent(Event rec) {

        ConnectionRequest req1 = new ConnectionRequest();
        String url = statics.BASE_URL + "/mobile/addevent?nom="+rec.getNom()+"&type=" + rec.getType()+ "&nbs=" + rec.getSalle()+"&dd="+rec.getDd()+"&df="+rec.getDf()+"&nbp="+rec.getNbp();

        req1.setUrl(url);
        req1.addResponseListener((e) -> {
            String str = new String(req1.getResponseData());
            System.out.println("data = " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req1);

    }

    public ArrayList<Event> affichageEvent() {

        ConnectionRequest req1 = new ConnectionRequest();
        ArrayList<Event> result = new ArrayList<>();
        String url = statics.BASE_URL + "/mobile/displayevent";
        req1.setUrl(url);

        req1.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    JSONParser jsonp;
                    jsonp = new JSONParser();
                    Map<String, Object> mapCategorie = jsonp.parseJSON(new CharArrayReader(new String(req1.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapCategorie.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Event c = new Event();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String id = obj.get("nomEvent").toString();
                        String type = obj.get("typeEvent").toString();
                        float nbp = Float.parseFloat(obj.get("nbrParticipants").toString());
                      
                        float ids = Float.parseFloat(obj.get("nbreSalle").toString());
                        LinkedHashMap<String, Object> dd = (LinkedHashMap<String, Object>) obj.get("dateDeb");
                        String dated = dd.get("timestamp").toString();
                        LinkedHashMap<String, Object> df = (LinkedHashMap<String, Object>) obj.get("dateFin");
                        String datef = df.get("timestamp").toString();
                        
                        Date ddebut = new Date(Double.valueOf(dated).longValue() * 1000);
                        String datedebut = formatter.format(ddebut);
                        
                        Date dfin = new Date(Double.valueOf(datef).longValue() * 1000);
                        String datefin = formatter.format(dfin);
                        
                        c.setNom(id);
                        c.setType(type);
                        c.setDd(datedebut);
                        c.setDf(datefin);
                        c.setNbp((int)nbp);
                        c.setSalle((int)ids);
                        result.add(c);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req1);
        return result;
    }

    public boolean deleteEvent(String id) {

        ConnectionRequest req1 = new ConnectionRequest();
        String url = statics.BASE_URL + "/mobile/deleteevent?id=" + id;

        req1.setUrl(url);

        req1.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req1.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req1);
        return resultOK;

    }

    public boolean editEvent(Event rec) {

        ConnectionRequest req1 = new ConnectionRequest();
        String url = statics.BASE_URL + "/mobile/updateevent?id="+rec.getNom()+"&type=" + rec.getType()+ "&salle=" + rec.getSalle()+"&dd="+rec.getDd()+"&df="+rec.getDf()+"&nbp="+rec.getNbp();

        req1.setUrl(url);

        req1.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req1.getResponseCode() == 200;
                req1.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req1);

        System.out.println(resultOK);
        return resultOK;
    }
    
    public Event getEvent(String id){
        Event q = new Event();
        ArrayList<Event> qs = ServiceEvent.getInstance().affichageEvent();
        for(Event s : qs){
            if(s.getNom().equals(id)){
                q=s;
            }
        }
        return q;
    }
}
