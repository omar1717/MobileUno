package Service;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Boutique;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.statics;

public class BoutiqueServices {
    private ConnectionRequest req;
    public static BoutiqueServices instance = null; 
    public ArrayList<Boutique> boutiques;

    public BoutiqueServices() {
        req = new ConnectionRequest();
    }

    public static BoutiqueServices getInstance() {
        if (instance == null) {
            instance = new BoutiqueServices();
        }
        return instance;
    }

    public boolean addBoutique(Boutique b) {
        String nomBo = b.getNomBo();
        String adresseBo = b.getAdresseBo();
        double latB = b.getLatB();
        double longB = b.getLongB();

        String url = statics.BASE_URL + "/addb";

        ConnectionRequest req = new ConnectionRequest();
        req.addRequestHeader("APP_ENV", "dev");
        req.setUrl(url);
        req.setPost(true);

        // Ajout des données de la boutique dans le corps de la requête POST
        String postData = "nomBo=" + nomBo + "&adresseBo=" + adresseBo+  "&latB="+latB+"&longB=" +longB ;
        req.setRequestBody(postData);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (evt.getResponseCode() == 200) {
                    // La requête s'est bien déroulée
                    boolean resultOK = true;
                } else {
                    // Il y a eu une erreur
                    boolean resultOK = false;
                    System.err.println("Error - ");
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }

public ArrayList<Boutique> parseBoutiques(String jsonText) {
    try {
        boutiques = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> boutiquesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) boutiquesListJson.get("root");
        if (list != null) {
            System.out.println("List size: " + list.size());
            for (Map<String, Object> obj : list) {
                Boutique b = new Boutique();
                float id = Float.parseFloat(obj.get("idBo").toString());
                b.setIdBo((int) id);
                b.setNomBo(obj.get("nomBo").toString());
                b.setAdresseBo(obj.get("adresseBo").toString());
            
                boutiques.add(b);
            }
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return boutiques;
}





  public ArrayList<Boutique> getAllBoutiques() {
    req = new ConnectionRequest();
    String url = statics.BASE_URL + "/affichejb";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            String jsonText = new String(req.getResponseData());
            boutiques = parseBoutiques(jsonText);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return boutiques;
}
  
  
  public int w(int idBo) {
        req = new ConnectionRequest();
      req.setUrl("http://127.0.0.1:8000/" + idBo + "/deletejb");
        req.setHttpMethod("DELETE");
        req.addArgument("id", String.valueOf(idBo));

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });

        try {
            req.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return req.getResponseCode();
    }
  public void editB(Boutique b) {
    int id = b.getIdBo();
    String nomBo = b.getNomBo();
    String adresseBo = b.getAdresseBo();
    double latB = b.getLatB();
    double longB = b.getLongB();

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
    request.setUrl("http://localhost:8000/" + id + "/editjb");
    request.setPost(true);
    request.addArgument("nomBo", nomBo);
    request.addArgument("adresseBo", adresseBo);
    request.addArgument("latB", String.valueOf(latB));
    request.addArgument("longB", String.valueOf(longB));
    NetworkManager.getInstance().addToQueueAndWait(request);
}



}
