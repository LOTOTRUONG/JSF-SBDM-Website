package vn.loto.jsf04.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudItem;
import org.primefaces.model.tagcloud.TagCloudModel;
import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.Couleur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CouleurBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Couleur> allCouleurs;

    @Getter
    @Setter
    private List<Couleur> filteredCouleur;
    @Getter
    @Setter
    private List<Couleur>selectedColeurs;
    private Couleur selectedCouleur;
    @Getter
    @Setter
    private String nomCouleur;
    private TagCloudModel model;

    @PostConstruct
    public void init(){

        if(allCouleurs == null) {
            allCouleurs = DAOFactory.getCouleurDAO().getAll();
            allCouleurs.add(0, new Couleur(0, "Choisir un couleur"));
        }

        selectedCouleur = new Couleur();

        if (filteredCouleur == null){
            filteredCouleur = DAOFactory.getCouleurDAO().getAll();
        }

        this.selectedColeurs = new ArrayList<>();

        model = new DefaultTagCloudModel();

        int[] strengths = {2,3,5,4,1};
        int index = 0;
        for(Couleur couleur  : filteredCouleur) {
            model.addTag(new DefaultTagCloudItem(couleur.getNomCouleur(),strengths[index++]));
            if(index == strengths.length)
                index = 0;
        }
    }



    public List<Couleur> getAllCouleur() {
        return allCouleurs;
    }

    public void setAllCouleur(List<Couleur> allCouleurs) {
        this.allCouleurs = allCouleurs;
    }

    public Couleur getSelectedCouleur() {
        return selectedCouleur;
    }

    public void setSelectedCouleur(Couleur selectedCouleur) {
        this.selectedCouleur = selectedCouleur;
    }
    public TagCloudModel getModel() {
        return model;
    }


    public void couleurChanged(SelectEvent<TagCloudItem> event) {
        TagCloudItem item = event.getObject();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Couleur Choisie:", item.getLabel());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    public void openNew() {
        this.selectedCouleur = new Couleur();
    }
    public boolean hasSelectedColor() {
        return this.selectedColeurs != null && !this.selectedColeurs.isEmpty();
    }
    public String getDeleteButtonMessage() {

        if (hasSelectedColor()) {
            int size = this.selectedColeurs.size();
            return size > 1 ? size + " Couleurs selected" : "1 couleur selected";
        }

        return "Delete";
    }
    public void deleteColor(){
        if (selectedCouleur != null) {
            filteredCouleur.remove(selectedCouleur);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Color Removed"));
            //PrimeFaces.current().executeScript("PF('deleteColorDialog').hide()");
            PrimeFaces.current().ajax().update("modifyCouleur:messages", "modifyCouleur:couleurs");
            DAOFactory.getCouleurDAO().delete(selectedCouleur);
        }
    }

    public void deleteSelectedColor(){
        selectedColeurs.add(selectedCouleur); // Add the single selected color to the list
        if (selectedColeurs.size() > 0) {
        boolean success = DAOFactory.getCouleurDAO().deleteMultiple(selectedColeurs);
        if (success) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Colors Removed"));
            // Update the list of colors after deletion
            this.filteredCouleur.removeAll(this.selectedColeurs);
            selectedColeurs = null;
            PrimeFaces.current().executeScript("PF('dtCouleurs').clearFilteres()");
           PrimeFaces.current().ajax().update("modifyCouleur:messages", "modifyCouleur:couleurs");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed to delete colors"));
        }
        selectedCouleur = null;
    }
}



    public void updateColor() {
        if (selectedCouleur != null) {
            if (DAOFactory.getCouleurDAO().update(selectedCouleur)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Color updated successfully"));
               PrimeFaces.current().executeScript("PF('manageColorDialog').hide()");
               PrimeFaces.current().ajax().update("modifyCouleur:messages", "modifyCouleur:couleurs");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed to update color"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No color selected for update"));
        }
    }


    public void addNewColor() {
        if (nomCouleur != null && !nomCouleur.isEmpty()) {
            // Check if the color name already exists
            boolean colorExists = allCouleurs.stream()
                    .anyMatch(couleur -> couleur.getNomCouleur().equalsIgnoreCase(nomCouleur));

            if (colorExists) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Color name already exists"));
                PrimeFaces.current().ajax().update("modifyCouleur:messages");
                return; // Exit the method if color name already exists
            }
            Couleur newCouleur = new Couleur(this.nomCouleur);
            DAOFactory.getCouleurDAO().insert(newCouleur);

            // Update the list of colors after adding the new color
            allCouleurs = DAOFactory.getCouleurDAO().getAll();
            allCouleurs.add(0, new Couleur(0, "Choisir une couleur"));

            // Clear the input field after adding the color
            nomCouleur = null;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Color added successfully"));
           PrimeFaces.current().executeScript("PF('addColorDialog').hide()");
           PrimeFaces.current().ajax().update("modifyCouleur:messages", "modifyCouleur:couleurs");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Color name cannot be empty"));
        }

    }


}
