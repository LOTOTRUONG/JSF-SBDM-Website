package vn.loto.jsf04.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.Continent;
import vn.loto.jsf04.metier.Marque;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class MarqueBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter @Setter
    private List<Marque> allMarques;
    @Getter @Setter
    private List<Marque> filteredMarques;
    @Getter @Setter
    private Marque selectedMarques;


    @PostConstruct
    public void init(){
        initialize();
    }

    public void initialize(){
        if (allMarques == null) {
            allMarques = DAOFactory.getMarqueDAO().getAll();
            allMarques.add(0, new Marque(0,"Choisir une marque"));
        }
         filteredMarques = allMarques;
        selectedMarques = new Marque();
    }


}
