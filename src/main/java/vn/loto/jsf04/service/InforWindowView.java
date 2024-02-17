package vn.loto.jsf04.service;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@ViewScoped
public class InforWindowView implements Serializable {

    private MapModel<Marker> advancedModel;
    private Marker selectedMarker;
    private Map<Marker, String> markerInfo; // Store additional information for each marker


    @PostConstruct
    public void init() {
        advancedModel = new DefaultMapModel();
        markerInfo = new HashMap<>();


        LatLng coord1 = new LatLng(46.71109, 1.7191036);
        LatLng coord2 = new LatLng(51.1642292, 10.4541194);
        LatLng coord3 = new LatLng(35.8592948, 104.1361118);
        LatLng coord4 = new LatLng(50.5010789, 4.4764595);

        Marker marker1 = new Marker(coord1, "France");
        advancedModel.addOverlay(marker1);
        markerInfo.put(marker1, "Additional information for France");

        Marker marker2 = new Marker(coord2, "Allemande");
        advancedModel.addOverlay(marker2);
        markerInfo.put(marker2, "Additional information for Allemande");

        Marker marker3 = new Marker(coord3, "Chine");
        advancedModel.addOverlay(marker3);
        markerInfo.put(marker3, "Additional information for Chine");

        Marker marker4 = new Marker(coord4, "Belgique");
        advancedModel.addOverlay(marker4);
        markerInfo.put(marker4, "Additional information for Belgique");
    }

    public MapModel<Marker> getAdvancedModel() {
        return advancedModel;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        selectedMarker = (Marker) event.getOverlay();
    }

    public Marker getSelectedMarker() {
        return selectedMarker;
    }

    public String getMarkerInfo() {
        return markerInfo.get(selectedMarker);
    }
}
