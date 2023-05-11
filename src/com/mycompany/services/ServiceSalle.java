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
import com.mycompany.entities.Salle;
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
public class ServiceSalle {
    public boolean resultOK = true;

    public static ServiceSalle instance = null;

    public static NetworkManager instances;

    private ConnectionRequest req;

    public static ServiceSalle getInstance() {
        if (instance == null) {
            instance = new ServiceSalle();
        }
        return instance;
    }

    public ServiceSalle() {
        req = new ConnectionRequest();
    }

    public void ajouterSalle(Salle rec) {

        ConnectionRequest req1 = new ConnectionRequest();
        String url = statics.BASE_URL + "/mobile/addsalle?id="+rec.getNbs()+"&type=" + rec.getType()+ "&cin="+rec.getCin();

        req1.setUrl(url);
        req1.addResponseListener((e) -> {
            String str = new String(req1.getResponseData());
            System.out.println("data = " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req1);

    }

    public ArrayList<Salle> affichageSalle() {

        ConnectionRequest req1 = new ConnectionRequest();
        ArrayList<Salle> result = new ArrayList<>();
        String url = statics.BASE_URL + "/mobile/displaysalle";
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
                        Salle c = new Salle();
                        String id = obj.get("nbreSalle").toString();
                        String type = obj.get("typeStuff").toString();
                        float cin = Float.parseFloat(obj.get("cinStuff").toString());
                        
                        
                        c.setNbs(id);
                        c.setType(type);
                        c.setCin((int)cin);
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

    public boolean deleteSalle(String id) {

        ConnectionRequest req1 = new ConnectionRequest();
        String url = statics.BASE_URL + "/mobile/deletesalle?id=" + id;

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

    public boolean editSalle(Salle rec) {

        ConnectionRequest req1 = new ConnectionRequest();
        String url = statics.BASE_URL + "/mobile/updatesalle?id=" + rec.getNbs() + "&type=" + rec.getType()+ "&cin="+rec.getCin();

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
    public Salle getSalle(String id){
        Salle q = new Salle();
        ArrayList<Salle> qs = ServiceSalle.getInstance().affichageSalle();
        for(Salle s : qs){
            if(s.getNbs().equals(id)){
                q=s;
            }
        }
        return q;
    }
    
}
