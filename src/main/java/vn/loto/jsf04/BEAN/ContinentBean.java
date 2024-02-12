package vn.loto.jsf04.BEAN;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;
import vn.loto.jsf04.DAO.DAOFactory;
import vn.loto.jsf04.METIER.Continent;
import vn.loto.jsf04.METIER.Couleur;
import vn.loto.jsf04.METIER.Pays;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ContinentBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Continent> allContinents;

    private List<Continent> filteredContinents;


    private Continent selectedContinent;


    @PostConstruct
    public void init(){
        initialize();
    }

    public List<Continent> getAllContinents() {
        return allContinents;
    }

    public void setAllContinents(List<Continent> allContinents) {
        this.allContinents = allContinents;
    }

    public List<Continent> getFilteredContinents() {
        return filteredContinents;
    }

    public void setFilteredContinents(List<Continent> filteredContinents) {
        this.filteredContinents = filteredContinents;
    }

    public Continent getSelectedContinent() {
        return selectedContinent;
    }

    public void setSelectedContinent(Continent selectedContinent) {
        this.selectedContinent = selectedContinent;
    }
    public void initialize(){
        if (allContinents == null) {
            allContinents = DAOFactory.getContinentDAO().getAll();
            allContinents.add(0, new Continent(0, "Choisir un continent"));
        }
        filteredContinents = allContinents;
        selectedContinent = new Continent();
    }
}
