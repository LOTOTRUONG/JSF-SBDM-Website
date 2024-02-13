package vn.loto.jsf04.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.Fabricant;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class FabricantBean implements Serializable {
    private List<Fabricant> allFabricants;
    private Fabricant selectedFabricant;

    @PostConstruct
    public void init(){
        allFabricants = DAOFactory.getFabricantDAO().getAll();
    }

    public List<Fabricant> getAllFabricant(){
        return allFabricants;
    }
    public void setAllFabricant(List<Fabricant> allFabricant){
        this.allFabricants = allFabricant;
    }

    public Fabricant getSelectedFabricant(){
        return selectedFabricant;
    }

    public void setSelectedFabricant(Fabricant selectedFabricant){
        this.selectedFabricant = selectedFabricant;
    }
}
