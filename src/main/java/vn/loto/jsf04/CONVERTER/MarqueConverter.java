package vn.loto.jsf04.CONVERTER;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import vn.loto.jsf04.BEAN.MarqueBean;
import vn.loto.jsf04.METIER.Marque;

@FacesConverter(value = "marqueConverter", managed = true)
public class MarqueConverter implements Converter {

    @Inject
    private MarqueBean marqueBean;

    public Marque getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            for (Marque marque : marqueBean.getAllMarques()) {
                if (marque.getId() == Integer.parseInt(value)) {
                    return marque;
                }
            }
        }
        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        Marque marque = (Marque) object;
        return marque != null ? String.valueOf(marque.getId()) : null;
    }
}