package vn.loto.jsf04.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.Continent;
import vn.loto.jsf04.metier.Pays;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class ContinentBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Continent> allContinents;

    private List<Continent> filteredContinents;


    private Continent selectedContinent;
    @Getter @Setter
    private Map<String, Double> continentMarqueData;
    private PieChartModel pieModel;



    @PostConstruct
    public void init(){
        initialize();
        fetchContinentMarqueData();
        createPieModel();

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

    private void fetchContinentMarqueData() {
        continentMarqueData = DAOFactory.getContinentDAO().getContinentMarqueData();
    }
    private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();

        List<Number> values = new ArrayList<>();


        List<String> bgColors = new ArrayList<>();
        // Iterate over continentMarqueData and add data to the chart
        for (Map.Entry<String, Double> entry : continentMarqueData.entrySet()) {
            data.setLabels(entry.getKey()); // Add label (continent name)
            values.add(entry.getValue()); // Add data value to values list
            bgColors.add(getRandomColor()); // Add random color for each data point
        }


        dataSet.setData(values);
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        data.setLabels(new ArrayList<>(continentMarqueData.keySet())); // Set labels to continent names

        pieModel.setData(data);
    }
    private String getRandomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return "rgb(" + r + ", " + g + ", " + b + ")";
    }

    // Getter for pieModel
    public PieChartModel getPieModel() {
        return pieModel;
    }
}
