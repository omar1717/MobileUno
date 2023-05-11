package Form;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import entities.Boutique;

import Service.BoutiqueServices;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class AjoutBoutiqueForm extends Form {
        TextField tfId = new TextField("", "Product Id", 20, TextField.NUMERIC);
    public AjoutBoutiqueForm() {
        setTitle("Add a new boutique");
        setLayout(BoxLayout.y());
        
        
        TextField tfNom = new TextField("", "Boutique Name", 20, TextField.ANY);
        TextField tfAdresse = new TextField("", "Boutique Address", 20, TextField.ANY);
        TextField tfLatitude = new TextField("", "Boutique Latitude", 20, TextField.NUMERIC);
        TextField tfLongitude = new TextField("", "Boutique Longitude", 20, TextField.NUMERIC);
        Button btnValider = new Button("Add Boutique");
        
        btnValider.addActionListener(evt -> {
            if ( tfNom.getText().isEmpty() || 
                    tfAdresse.getText().isEmpty() || tfLatitude.getText().isEmpty() ||
                    tfLongitude.getText().isEmpty()) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    Boutique b = new Boutique(tfNom.getText(), 
                            tfAdresse.getText(), Double.parseDouble(tfLatitude.getText()), 
                            Double.parseDouble(tfLongitude.getText()));
                    
                    if (BoutiqueServices.getInstance().addBoutique(b)) {
                        Dialog.show("Success", "Boutique added successfully", new Command("OK"));
                        // Clear form fields
                      
                        tfNom.clear();
                        tfAdresse.clear();
                        tfLatitude.clear();
                        tfLongitude.clear();
                    } else {
                        Dialog.show("Error", "Failed to add boutique", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("Error", "Boutique Id, Latitude and Longitude must be numbers", new Command("OK"));
                }
            }
        });
        
        addAll(tfId, tfNom, tfAdresse, tfLatitude, tfLongitude, btnValider);
        
        Button btnAfficher = new Button("Show All Boutiques");
        btnAfficher.addActionListener(evt -> {
            AfficheBoutiqueForm afficheBoutiqueForm = new AfficheBoutiqueForm();
            afficheBoutiqueForm.show();
        });

        add(btnAfficher);
        // Ajouter la fonctionnalité de suppression de produit
        Button btnSupprimer = new Button("Delete boutique");
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                TextField tfId = new TextField("", "boutique id", 20, TextField.NUMERIC);
                Button btnDelete = new Button("Delete");

                Form deleteForm = new Form("Delete boutique", BoxLayout.y());
                deleteForm.addAll(tfId, btnDelete);
                deleteForm.show();

                btnDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            int id = Integer.parseInt(tfId.getText());
BoutiqueServices.getInstance().w(id);
     Dialog.show("Success", "Boutique deleted successfully", new Command("OK"));
} catch (NumberFormatException e) {
Dialog.show("Error", " Id must be a number", new Command("OK"));
}
}
                    
});
}
});
        Button modifierButton = new Button("Modifier");
modifierButton.addActionListener((evt) -> {
    if (tfId.getText().isEmpty()) {
        Dialog.show("Erreur", "Veuillez remplir l'identifiant de la boutique", "OK", null);
        return;
    }
    int id = Integer.parseInt(tfId.getText());
    String nomBo = tfNom.getText();
    String adresseBo = tfAdresse.getText();
    double latB = Double.parseDouble(tfLatitude.getText());
    double longB = Double.parseDouble(tfLongitude.getText());

    Boutique b = new Boutique(id, nomBo, adresseBo, latB, longB);
   
});
add(modifierButton);

        add(btnSupprimer);
    }
    

}
