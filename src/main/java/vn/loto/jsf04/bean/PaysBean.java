package vn.loto.jsf04.bean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.Continent;
import vn.loto.jsf04.metier.Pays;
import vn.loto.jsf04.service.lazyview.LazyPaysDataModel;
import vn.loto.jsf04.service.lazyview.LazyPaysSorter;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class PaysBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Pays> allPays;
    @Getter @Setter
    private Pays selectedPay;
    @Getter @Setter
    private List<Pays> filteredPays;
    @Getter @Setter
    private LazyDataModel<Pays> paysLazyDataModel;


    @PostConstruct
    public void init(){
        initialize();

        paysLazyDataModel = new LazyPaysDataModel(allPays);

    }
    public List<Pays> getAllPays() {
        return allPays; }

    public void setAllPays(List<Pays> allPays) {
        this.allPays = allPays;
    }


    public void onContinentChange(Continent continent) {
        if (continent != null && continent.getId() != 0) {
            filteredPays = DAOFactory.getPaysDAO().getLike(continent);
            filteredPays.add(0, new Pays(0, "Choisir un pays", continent));
            selectedPay = new Pays(); // Reset selectedPay when changing continent
        } else {
            initialize();
        }
    }

    public void initialize(){
        if(allPays == null) {
            allPays = DAOFactory.getPaysDAO().getAll();
            allPays.add(0, new Pays(0, "Choisir un pays",new Continent()));
        }

        selectedPay = new Pays();
        filteredPays = allPays; // Initialize filteredPays with all countries

    }
}
