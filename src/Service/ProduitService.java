/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Produit;
import utils.statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author MSI
 */
public class ProduitService {
        public ArrayList<Produit> Produits;

    public static ProduitService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ProduitService() {
        req = new ConnectionRequest();
    }

    public static ProduitService getInstance() {
        if (instance == null) {
            instance = new ProduitService();
        }
        return instance;
    }
  
public boolean addProduit(Produit p) {
    String Nom_Prod = p.getNom_Prod();
    String Type_Prod = p.getType_Prod();
    int Quantite_Prod = p.getQuantite_Prod();
    Double PrixU_Prod = p.getPrixU_Prod();
    String Categorie_Prod = p.getCategorie_Prod();
    String Nom_Event = p.getNom_Event();

    String url = statics.BASE_URL + "/addp?nomProd=" + Nom_Prod + "&typeProd=" + Type_Prod + "&quantiteProd=" + Quantite_Prod;

    ConnectionRequest req = new ConnectionRequest();
    req.addRequestHeader("APP_ENV", "dev");
    req.setUrl(url);
    req.setPost(true);

    // Ajout des données du produit dans le corps de la requête POST
    String postData = "nomProd=" + Nom_Prod + "&typeProd=" + Type_Prod
            + "&quantiteProd=" + Quantite_Prod + "&prixuProd=" + PrixU_Prod
            + "&categorieProd=" + Categorie_Prod + "&nomEvent=" + Nom_Event;
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
public ArrayList<Produit> parseProduits(String jsonText) {
    ArrayList<Produit> produits = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> produitsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) produitsListJson.get("root");
        for (Map<String, Object> obj : list) {
            Produit p = new Produit();
            float id = Float.parseFloat(obj.get("idProd").toString());
            p.setId_Prod((int) id);
            p.setNom_Prod(obj.get("nomProd").toString());
            p.setType_Prod(obj.get("typeProd").toString());
            float quantite = Float.parseFloat(obj.get("quantiteProd").toString());
            p.setQuantite_Prod((int) quantite);
            double prix = Double.parseDouble(obj.get("prixuProd").toString());
            p.setPrixU_Prod(prix);
            p.setCategorie_Prod(obj.get("categorieProd").toString());
            p.setNom_Event(obj.get("nomEvent").toString());
            produits.add(p);
        }

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return produits;
}
public ArrayList<Produit> getAllProduits() {
    String url = statics.BASE_URL + "/afficheJ";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            Produits = parseProduits(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return Produits;
}
public boolean deleteProduit(int idProd) {
    String url = statics.BASE_URL + "/produit/" + idProd + "/deleteJ";
    ConnectionRequest req = new ConnectionRequest();
    req.addRequestHeader("APP_ENV", "dev");
    req.setUrl(url);
    req.setPost(false);
    req.setHttpMethod("DELETE");

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
public void editJ(Produit p) {
    String Nom_Prod = p.getNom_Prod();
    String Type_Prod = p.getType_Prod();
    int Quantite_Prod = p.getQuantite_Prod();
    Double PrixU_Prod = p.getPrixU_Prod();
    String Categorie_Prod = p.getCategorie_Prod();
    String Nom_Event = p.getNom_Event();

    ConnectionRequest request = new ConnectionRequest() {
        @Override
        protected void readResponse(InputStream input) throws IOException {
            // Lire la réponse du serveur
            JSONParser parser = new JSONParser();
            Map<String, Object> parsedData = parser.parseJSON(new InputStreamReader(input));
            String response = (String) parsedData.get("response");
            System.out.println(response);
        }
    };
            String idProd = null;
   request.setUrl("http://localhost:8000/"+idProd+"/editJ");

    request.setPost(true);
    request.addArgument("nomProd", Nom_Prod);
    request.addArgument("typeProd", Type_Prod);
    request.addArgument("quantiteProd", String.valueOf(Quantite_Prod));
    request.addArgument("prixuProd", String.valueOf(PrixU_Prod));
    request.addArgument("categorieProd", Categorie_Prod);
    request.addArgument("nomEvent", Nom_Event);
    NetworkManager.getInstance().addToQueueAndWait(request);
}









}
