package Form;
import Service.ProduitService;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.io.NetworkManager;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Dialog;
import entities.Produit;
import utils.statics;
public class EditForm extends Form {

    private Produit produit;
    private int idProd;

    public EditForm(Produit p, int id) {
        super("Edit Form", BoxLayout.y());
     
        produit = p;
        idProd = id;

        TextField nomField = new TextField(p.getNom_Prod(), "Nom Produit", 20, TextField.ANY);
        TextField typeField = new TextField(p.getType_Prod(), "Type Produit", 20, TextField.ANY);
        TextField quantiteField = new TextField(String.valueOf(p.getQuantite_Prod()), "Quantité Produit", 20, TextField.NUMERIC);
        TextField prixUField = new TextField(String.valueOf(p.getPrixU_Prod()), "Prix unitaire Produit", 20, TextField.NUMERIC);
        TextField categorieField = new TextField(p.getCategorie_Prod(), "Catégorie Produit", 20, TextField.ANY);
        TextField nomEventField = new TextField(p.getNom_Event(), "Nom Evenement", 20, TextField.ANY);

        Button validerBtn = new Button("Valider");

        validerBtn.addActionListener((evt) -> {
            produit.setNom_Prod(nomField.getText());
            produit.setType_Prod(typeField.getText());
            produit.setQuantite_Prod(Integer.parseInt(quantiteField.getText()));
            produit.setPrixU_Prod(Double.parseDouble(prixUField.getText()));
            produit.setCategorie_Prod(categorieField.getText());
            produit.setNom_Event(nomEventField.getText());
            ProduitService.getInstance().editJ(p);
            Dialog.show("Succès", "Produit modifié avec succès", "OK", null);
           Display.getInstance().getCurrent().showBack();
        });

        addAll(nomField, typeField, quantiteField, prixUField, categorieField, nomEventField, validerBtn);
        
    }

    public EditForm(Produit produit) {
        this.produit = produit;
    }

  

    

 

 
}


  
