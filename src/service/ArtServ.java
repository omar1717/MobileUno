/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entity.artist;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.statics;

/**
 *
 * @author yessine
 */
public class ArtServ {
    public ArrayList<artist> Artist;

    public static ArtServ instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ArtServ() {
        req = new ConnectionRequest();
    }

    public static ArtServ getInstance() {
        if (instance == null) {
            instance = new ArtServ();
        }
        return instance;
    }
    
    
    
    public boolean addART(artist p) {
   int idAr = p.getId_Ar();
        String NomAr = p.getNom_Ar();
    String PrenomAr = p.getPrenom_Ar();
    String dateAr = p.getDateStr();
    String detailAr = p.getDetail_Ar();
    String genrAr = p.getGenre_Ar();
    String desAr = p.getDesc_ar();
    String urlAr = p.getUrl_ar();
    
    

    String url = statics.BASE_URL + "addArtistJson/new?nom_Ar=" + NomAr + "&prenom_ar=" + PrenomAr + "&date_nesAr=" + dateAr + "&detail_ar=" + detailAr + "&genre_ar=" + genrAr + "&desc_ar=" + desAr + "&url=" + urlAr;

    ConnectionRequest req = new ConnectionRequest();
    req.addRequestHeader("APP_ENV", "dev");
    req.setUrl(url);
    req.setPost(true);

    // Ajout des données du Artist dans le corps de la requête POST
    String postData = "nom_Ar=" + NomAr + "&prenom_ar=" + PrenomAr
            + "&date_nesAr=" + dateAr + "&detail_ar=" + detailAr
            + "&genre_ar=" + genrAr + "&desc_ar=" + desAr + "url" + urlAr;
    req.setRequestBody(postData);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            if (evt.getResponseCode() == 200) {
                resultOK = true;
            } else {
                resultOK = false;
                System.err.println("Error - ");
            }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
    
    
    
    public boolean updateART(artist p, int id) {
    String NomAr = p.getNom_Ar();
    String PrenomAr = p.getPrenom_Ar();
    String dateAr = p.getDateStr();
    String detailAr = p.getDetail_Ar();
    String genrAr = p.getGenre_Ar();
    String desAr = p.getDesc_ar();
    String urlAr = p.getUrl_ar();
    
    

    String url = statics.BASE_URL + "UpdateArtistJson/" + id + "?nom_Ar=" + NomAr + "&prenom_ar=" + PrenomAr + "&date_nesAr=" + dateAr + "&detail_ar=" + detailAr + "&genre_ar=" + genrAr + "&desc_ar=" + desAr + "&url=" + urlAr;

    ConnectionRequest req = new ConnectionRequest();
    req.addRequestHeader("APP_ENV", "dev");
    req.setUrl(url);
    req.setPost(true);

    // Ajout des données du Artist dans le corps de la requête POST
    String postData ="nom_Ar=" + NomAr + "&prenom_ar=" + PrenomAr
            + "&date_nesAr=" + dateAr + "&detail_ar=" + detailAr
            + "&genre_ar=" + genrAr + "&desc_ar=" + desAr + "url" + urlAr;
    req.setRequestBody(postData);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            if (evt.getResponseCode() == 200) {
                resultOK = true;
            } else {
                resultOK = false;
                System.err.println("Error - ");
            }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
    
    public ArrayList<artist>affichageArtist() {
        ArrayList<artist> result = new ArrayList<>();
        
        String url = statics.BASE_URL + "AllArtistts/";
        
        ConnectionRequest req = new ConnectionRequest();
    req.addRequestHeader("APP_ENV", "dev");
        req.setUrl(url);
        req.setPost(false);

         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                 try {
                    Map<String,Object>mapTickets = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapTickets.get("root");
                    System.out.print("");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        artist t = new artist();
                        
                        //dima id fi codename one float 5outhouha
                        float idA = Float.parseFloat(obj.get("id_Ar").toString());
                      //  float id_event = Float.parseFloat(obj.get("idEvent").toString());

                        String nomA = obj.get("nom_Ar").toString();
                        String prenomA = obj.get("prenom_Ar").toString();
                        String detA = obj.get("detail_Ar").toString();
                        String genA = obj.get("genre_Ar").toString();
                        String desA = obj.get("desc_ar").toString();
                        String urlA = obj.get("url_ar").toString();
                        String dateA = obj.get("dateStr").toString();
                        
                        
                        t.setId_Ar((int)idA);
                       
                        
                        t.setNom_Ar(nomA);
                        t.setPrenom_Ar(prenomA);
                        t.setDetail_Ar(detA);
                        t.setGenre_Ar(genA);
                        t.setDesc_ar(desA);
                       t.setUrl_ar(urlA);
                        t.setDateStr(dateA);
                        //insert data into ArrayList result
                        result.add(t);
                        
                                }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
        
    }
 
     public boolean deleteTicket(int id ) {
        String url = statics.BASE_URL +"DelArtistJson/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
      public String findByid(int id ) {
        String url = statics.BASE_URL +"artttts/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  url;
    }
}
