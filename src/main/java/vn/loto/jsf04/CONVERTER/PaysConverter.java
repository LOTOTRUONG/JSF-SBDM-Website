package vn.loto.jsf04.CONVERTER;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import vn.loto.jsf04.BEAN.PaysBean;
import vn.loto.jsf04.METIER.Pays;

@FacesConverter(value = "paysConverter", managed = true)
public class PaysConverter implements Converter {

    @Inject
    private PaysBean paysBean;

    public Pays getAsObject(FacesContext facesContext, UIComponent uiComponent, String value){
        if (value != null && !value.isEmpty()) {
            for (Pays pay : paysBean.getAllPays()){
                if (pay.getId() == Integer.parseInt(value)){
                    return pay;
                }
            }
        }
        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        Pays pays = (Pays) object;
        return pays != null ? String.valueOf(pays.getId()) : null;
    }
}
