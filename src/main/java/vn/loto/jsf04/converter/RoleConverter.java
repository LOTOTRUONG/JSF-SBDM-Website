package vn.loto.jsf04.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import vn.loto.jsf04.bean.RoleBean;
import vn.loto.jsf04.metier.Roles;


@FacesConverter(value = "roleConverter", managed = true)

public class RoleConverter implements Converter {
    @Inject
    private RoleBean roleBean;

        public Roles getAsObject(FacesContext context, UIComponent component, String value) {
            if (value != null && !value.isEmpty()) {
                for (Roles roles : roleBean.getAllRoles()){
                    if (roles.getId() == Integer.parseInt(value)){
                        return roles;
                    }
                }
            }
            return null;
        }

        public String getAsString(FacesContext context, UIComponent component,  Object object) {
            Roles roles = (Roles) object;
            return roles != null ? String.valueOf(roles.getId()) : null;
        }


}
