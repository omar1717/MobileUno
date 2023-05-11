package Form;

import Service.BoutiqueServices;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import entities.Boutique;

import java.util.ArrayList;
import java.util.List;

public class AfficheBoutiqueForm extends Form {

    // Liste de toutes les boutiques récupérées de la base de données
    private List<Boutique> boutiques = new ArrayList<>();

    public AfficheBoutiqueForm() {
        // Titre de la page
        setTitle("All Boutiques");
        // Définir la disposition de la page
        setLayout(new BorderLayout());

        // Bouton pour retourner à la page précédente
        Button btnBack = new Button("Back");
        btnBack.addActionListener(evt -> {
            // Créer une instance de la classe AjoutBoutiqueForm
            AjoutBoutiqueForm ajoutBoutiqueForm = new AjoutBoutiqueForm();
            // Afficher la page AjoutBoutiqueForm
            ajoutBoutiqueForm.show();
        });

        // Bouton pour mettre à jour les boutiques affichées
        Button btnRefresh = new Button("Refresh");
        btnRefresh.addActionListener(evt -> {
            // Mettre à jour la liste des boutiques
            boutiques = BoutiqueServices.getInstance().getAllBoutiques();
            // Effacer le modèle de liste existant
            DefaultListModel model = new DefaultListModel();
            // Ajouter les boutiques récupérées au modèle de liste
            for (Boutique b : boutiques) {
                // Créer une chaîne de caractères contenant les informations à afficher
                String boutiqueString = "ID: " + b.getIdBo() + " - " + b.getNomBo() + " - " + b.getAdresseBo() + " - " + b.getLatB() + " - " + b.getLongB();
                // Ajouter la chaîne de caractères au modèle de liste
                model.addItem(boutiqueString);
            }
            // Créer une liste pour afficher les boutiques
            MultiList mlBoutiques = new MultiList();
            // Définir le nouveau modèle de liste pour afficher les boutiques
            mlBoutiques.setModel(model);
            // Ajouter la liste à la page
            removeAll();
            add(BorderLayout.NORTH, btnBack);
            add(BorderLayout.CENTER, mlBoutiques);
            add(BorderLayout.SOUTH, btnRefresh);
            revalidate();
        });

        // Liste pour afficher les boutiques
        MultiList mlBoutiques = new MultiList();

        // Ajouter les boutons et la liste à la page
        add(BorderLayout.NORTH, btnBack);
        add(BorderLayout.CENTER, mlBoutiques);
        add(BorderLayout.SOUTH, btnRefresh);

        
    }
}
