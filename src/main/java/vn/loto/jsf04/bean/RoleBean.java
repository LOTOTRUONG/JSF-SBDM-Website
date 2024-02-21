package vn.loto.jsf04.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.Roles;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RoleBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Roles> allRoles;
    private Roles selectedRole;
    @PostConstruct
    public void init(){
        initialize();
    }

    public List<Roles> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(List<Roles> allRoles) {
        this.allRoles = allRoles;
    }

    public Roles getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Roles selectedRole) {
        this.selectedRole = selectedRole;
    }


    public void initialize(){
        if (allRoles == null) {
            allRoles = DAOFactory.getRolesDAO().getAll();
        }
        selectedRole = new Roles();
    }
}
