package vn.loto.jsf04.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudItem;
import org.primefaces.model.tagcloud.TagCloudModel;
import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.Couleur;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CouleurBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Couleur> allCouleurs;
    private Couleur selectedCouleur;
    private TagCloudModel model;

    @PostConstruct
    public void init(){

        if(allCouleurs == null) {
            allCouleurs = DAOFactory.getCouleurDAO().getAll();
            allCouleurs.add(0, new Couleur(0, "Choisir un couleur"));
        }

        model = new DefaultTagCloudModel();

        int[] strengths = {2,3,5,4,1};
        int index = 0;
        for(Couleur couleur  : allCouleurs) {
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
}
