package Form;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import Service.ProduitService;
import com.codename1.io.ConnectionRequest;
import static com.codename1.io.Log.p;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.layouts.BorderLayout;
import entities.Produit;
import utils.statics;

public class AjoutProduitForm extends Form {
      TextField tfId = new TextField("", "Product Id", 20, TextField.NUMERIC);
    public AjoutProduitForm() {
          super("Edit Form", BoxLayout.y());
        setTitle("Add a new product");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("", "Product Name", 20, TextField.ANY);
        TextField tfType = new TextField("", "Type", 20, TextField.ANY);
        TextField tfQuantity = new TextField("", "Quantity", 20, TextField.NUMERIC);
        TextField tfPrice = new TextField("", "Price", 20, TextField.NUMERIC);
        TextField tfCategory = new TextField("", "Category", 20, TextField.ANY);
        TextField tfEventName = new TextField("", "Event Name", 20, TextField.ANY);
        Button btnValider = new Button("Add Product");
        
        btnValider.addActionListener(evt -> {
            if (tfName.getText().isEmpty() || tfType.getText().isEmpty() || 
                    tfQuantity.getText().isEmpty() || tfPrice.getText().isEmpty() ||
                    tfCategory.getText().isEmpty() || tfEventName.getText().isEmpty()) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    Produit p = new Produit(tfName.getText(), tfType.getText(), 
                            Integer.parseInt(tfQuantity.getText()), 
                            Double.parseDouble(tfPrice.getText()), tfEventName.getText(), 
                            tfCategory.getText());
                    
                    if (ProduitService.getInstance().addProduit(p)) {
                        Dialog.show("Success", "Product added successfully", new Command("OK"));
                        // Clear form fields
                        tfName.clear();
                        tfType.clear();
                        tfQuantity.clear();
                        tfPrice.clear();
                        tfCategory.clear();
                        tfEventName.clear();
                    } else {
                        Dialog.show("Error", "Failed to add product", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("Error", "Quantity and Price must be numbers", new Command("OK"));
                }
            }
        });
        
        addAll(tfId,tfName, tfType, tfQuantity, tfPrice, tfCategory, tfEventName, btnValider);
        
        Button btnAfficher = new Button("Show All Products");
        btnAfficher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AfficheProduitForm afficheProduitForm = new AfficheProduitForm();
                afficheProduitForm.show();
            }
        });

        add(btnAfficher);
        
        // Ajouter la fonctionnalité de suppression de produit
        Button btnSupprimer = new Button("Delete Product");
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                TextField tfId = new TextField("", "Product Id", 20, TextField.NUMERIC);
                Button btnDelete = new Button("Delete");

                Form deleteForm = new Form("Delete Product", BoxLayout.y());
                deleteForm.addAll(tfId, btnDelete);
                deleteForm.show();

                btnDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            int id = Integer.parseInt(tfId.getText());
if (ProduitService.getInstance().deleteProduit(id)) {
Dialog.show("Success", "Product deleted successfully", new Command("OK"));
} else {
Dialog.show("Error", "Failed to delete product", new Command("OK"));
}
} catch (NumberFormatException e) {
Dialog.show("Error", "Product Id must be a number", new Command("OK"));
}
}
                    
});
}
});
        add(btnSupprimer);
        


// Ajouter l'écouteur d'événements

  Button modifierButton = new Button("Modifier");
modifierButton.addActionListener((evt) -> {
  
    if (tfId.getText().isEmpty()) {
        Dialog.show("Erreur", "Veuillez remplir l'identifiant du produit", "OK", null);
        return;
    }
    int id = Integer.parseInt(tfId.getText());
    String url = "http://localhost:8000/" + tfId.getText() + "/editJ?nomProd=" + tfName.getText() + "&typeProd=" + tfType.getText() + "&quantiteProd=" + tfQuantity.getText() + "&prixuProd=" + tfPrice.getText() + "&categorieProd=" + tfCategory.getText() + "&nomEvent=" + tfEventName.getText();
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);

    request.addResponseListener((NetworkEvent evtt) -> {
        String response = new String(request.getResponseData());
        System.out.println("Produit Modifié: " + response);
    });
    NetworkManager.getInstance().addToQueue(request);
});
add(modifierButton);


        
// Créer un bouton pour ouvrir le formulaire AfficheBoutiqueForm
Button btnBoutiques = new Button("Boutiques");
btnBoutiques.addActionListener(evt -> {
    // Créer une instance de la classe AfficheBoutiqueForm
    AjoutBoutiqueForm afficheBoutiqueForm = new AjoutBoutiqueForm();
    // Afficher le formulaire AfficheBoutiqueForm
    afficheBoutiqueForm.show();
});

// Ajouter le bouton à la page
add(btnBoutiques);

    }
    


}