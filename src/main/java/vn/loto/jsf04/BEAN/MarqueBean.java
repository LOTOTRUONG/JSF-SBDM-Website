package vn.loto.jsf04.BEAN;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import vn.loto.jsf04.DAO.DAOFactory;
import vn.loto.jsf04.METIER.Marque;
import vn.loto.jsf04.SERVICE.SearchService;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class MarqueBean implements Serializable {
    private List<Marque> allMarques;
    private Marque selectedMarques;


    @PostConstruct
    public void init(){
        if (allMarques == null){
            allMarques = DAOFactory.getMarqueDAO().getAll();
            allMarques.add(0, new Marque(0,"Choisir une marque"));
        }
    }
    public List<Marque> getAllMarques() {
        return allMarques;
    }

    public void setAllMarques(List<Marque> allMarques) {
        this.allMarques = allMarques;
    }

    public Marque getSelectedMarques() {
        return selectedMarques;
    }

    public void setSelectedMarques(Marque selectedMarques) {
        this.selectedMarques = selectedMarques;
    }




}
