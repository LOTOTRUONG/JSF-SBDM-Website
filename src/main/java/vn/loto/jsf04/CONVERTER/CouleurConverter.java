package vn.loto.jsf04.CONVERTER;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import vn.loto.jsf04.BEAN.CouleurBean;
import vn.loto.jsf04.METIER.Couleur;


@FacesConverter(value = "couleurConverter", managed = true)

public class CouleurConverter implements Converter {
    @Inject
    private CouleurBean couleurBean;
    public Couleur getAsObject(FacesContext facesContext, UIComponent uiComponent, String value){
        if(value!=null && value.trim().length()>0){
            for (Couleur couleur : couleurBean.getAllCouleur()){
                if (couleur.getId() == Integer.parseInt(value)){
                    return couleur;
                }
            }
        }
        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        Couleur couleur = (Couleur) object;
        return couleur != null ? String.valueOf(couleur.getId()) : null;
    }
}
