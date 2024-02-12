package vn.loto.jsf04.SERVICE;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import java.io.Serializable;

@Named
@ViewScoped
public class InforWindowView implements Serializable {

    private MapModel<String> advancedModel;
    private Marker<String> marker;

    @PostConstruct
    public void init() {
        advancedModel = new DefaultMapModel();
        //Shared coordinates
        LatLng coord1 = new LatLng(46.71109, 1.7191036);
        LatLng coord2 = new LatLng(51.1642292, 10.4541194);
        LatLng coord3 = new LatLng(35.8592948, 104.1361118);
        LatLng coord4 = new LatLng(50.5010789, 4.4764595);

        advancedModel.addOverlay(new Marker<>(coord1, "France"));
        advancedModel.addOverlay(new Marker<>(coord2, "Allemande"));
        advancedModel.addOverlay(new Marker<>(coord3, "Chine"));
        advancedModel.addOverlay(new Marker<>(coord4, "Belgique"));



    }

    public MapModel<String> getAdvancedModel() {
        return advancedModel;
    }

    public void onMarkerSelect(OverlaySelectEvent<String> event) {
        marker = (Marker) event.getOverlay();
    }

    public Marker<String> getMarker() {
        return marker;
    }
}
