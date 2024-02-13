package vn.loto.jsf04.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import vn.loto.jsf04.bean.TypeBean;
import vn.loto.jsf04.metier.Type;


@FacesConverter(value = "typeConverter", managed = true)

public class TypeConverter implements Converter {
    @Inject
    private TypeBean typeBean;
    public Type getAsObject(FacesContext facesContext, UIComponent uiComponent, String value){
        if(value!=null && value.trim().length()>0){
            for (Type type : typeBean.getAllTypes()){
                if (type.getId() == Integer.parseInt(value)){
                    return type;
                }
            }
        }
        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        Type type = (Type) object;
        return type != null ? String.valueOf(type.getId()) : null;
    }
}
