package vn.loto.jsf04.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import vn.loto.jsf04.bean.FabricantBean;
import vn.loto.jsf04.metier.Fabricant;

@FacesConverter(value = "fabricantConverter", managed = true)
public class FabricantConverter implements Converter {
    @Inject
    private FabricantBean fabricantBean;

    @Override
    public Fabricant getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            for (Fabricant fabricant : fabricantBean.getAllFabricants()){
                if (fabricant.getId() == Integer.parseInt(value)){
                    return fabricant;
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        Fabricant fabricant = (Fabricant) object;
        return fabricant != null ? String.valueOf(fabricant.getId()) : null;
    }
}
