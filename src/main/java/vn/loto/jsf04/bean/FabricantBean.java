package vn.loto.jsf04.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.Fabricant;
import vn.loto.jsf04.metier.Marque;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FabricantBean implements Serializable {
    @Getter @Setter
    private List<Fabricant> allFabricants;
    @Getter @Setter
    private Fabricant selectedFabricant;
    @Getter @Setter
    private List<Fabricant> filteredFabricant;

    @PostConstruct
    public void init(){
        initialize();
    }

    public void initialize(){
        if (allFabricants == null) {
            allFabricants = DAOFactory.getFabricantDAO().getAll();
            allFabricants.add(0, new Fabricant(0,"Choisir un fabricant"));
        }
        filteredFabricant = allFabricants;
        selectedFabricant = new Fabricant();
    }
}
