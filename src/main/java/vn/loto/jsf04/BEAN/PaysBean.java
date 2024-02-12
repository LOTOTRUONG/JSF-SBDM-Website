package vn.loto.jsf04.BEAN;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.MatchMode;
import vn.loto.jsf04.DAO.DAOFactory;
import vn.loto.jsf04.METIER.Continent;
import vn.loto.jsf04.METIER.Pays;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class PaysBean implements Serializable {

    private List<Pays> allPays;
    private Pays selectedPay;

    private List<Pays> filteredPays;


    @PostConstruct
    public void init(){
        initialize();
    }
    public List<Pays> getAllPays() {
        return allPays;
    }

    public void setAllPays(List<Pays> allPays) {
        this.allPays = allPays;
    }

    public Pays getSelectedPay() {
        return selectedPay;
    }

    public void setSelectedPay(Pays selectedPay) {
        this.selectedPay = selectedPay;
    }

    public List<Pays> getFilteredPays() {
        return filteredPays;
    }

    public void setFilteredPays(List<Pays> filteredPays) {
        this.filteredPays = filteredPays;
    }

    public void onContinentChange(Continent continent) {
        if(continent.getId() == 0) {
            filteredPays = allPays;
            return;
        }
       filteredPays = DAOFactory.getPaysDAO().getLike(continent);

    }

    public void initialize(){
        if(allPays == null) {
            allPays = DAOFactory.getPaysDAO().getAll();
            allPays.add(0, new Pays(0, "Choisir un pays",new Continent()));
        }

        filteredPays = allPays;
        selectedPay = new Pays();
    }
}
