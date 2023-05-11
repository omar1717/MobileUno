package Form;

import Service.ProduitService;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import entities.Produit;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Button;


import java.util.ArrayList;
import java.util.List;

public class AfficheProduitForm extends Form {
  
    private List<Produit> produits = new ArrayList<>();

    public AfficheProduitForm() {
        setTitle("All Products");
        setLayout(new BorderLayout());

        // Créer un bouton pour retourner à la page précédente
        Button btnBack = new Button("Back");
        btnBack.addActionListener(evt -> {
    // Créer une instance de la classe AjoutProduitForm
    AjoutProduitForm ajoutProduitForm = new AjoutProduitForm();
    
    // Afficher la page AjoutProduitForm
    ajoutProduitForm.show();
});

        // Créer un bouton pour mettre à jour les produits
        Button btnRefresh = new Button("Refresh");
        btnRefresh.addActionListener(evt -> {
            // Mettre à jour la liste des produits
            produits = ProduitService.getInstance().getAllProduits();
            // Effacer le modèle de liste existant
            DefaultListModel model = new DefaultListModel();
            // Ajouter les produits récupérés au modèle de liste
            for (Produit p : produits) {
                
               // Créer une chaîne de caractères contenant les informations à afficher
String produitString = "ID: " + p.getId_Prod() + " - " + p.getNom_Prod() + " - " + p.getType_Prod() + " - " + p.getQuantite_Prod() + " - " + p.getPrixU_Prod() + " - " + p.getCategorie_Prod() + " - " + p.getNom_Event();
// Ajouter la chaîne de caractères au modèle de liste
model.addItem(produitString);
            }
            // Créer une liste pour afficher les produits
            MultiList mlProduits = new MultiList();
            // Définir le nouveau modèle de liste pour afficher les produits
            mlProduits.setModel(model);
            // Ajouter la liste à la page
            removeAll();
            add(BorderLayout.NORTH, btnBack);
            add(BorderLayout.CENTER, mlProduits);
            revalidate();
        });

        // Créer une liste pour afficher les produits
        MultiList mlProduits = new MultiList();

        // Ajouter les boutons à la page
        add(BorderLayout.NORTH, btnBack);
        add(BorderLayout.SOUTH, btnRefresh);
        add(BorderLayout.CENTER, mlProduits);

    }
}
