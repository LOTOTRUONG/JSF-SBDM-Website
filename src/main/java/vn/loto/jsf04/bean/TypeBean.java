package vn.loto.jsf04.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.Type;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TypeBean implements Serializable {
    private List<Type> allTypes;
    private Type selectedType;
    @Getter
    @Setter
    private String libelle;
    @PostConstruct
    public void init(){
        if (allTypes == null){
            allTypes = DAOFactory.getTypeDAO().getAll();
            allTypes.add(0, new Type(0, "Choise une type"));
        }
    }
    public List<Type> getAllTypes() {
        return allTypes;
    }

    public void setAllTypes(List<Type> allTypes) {
        this.allTypes = allTypes;
    }

    public Type getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(Type selectedType) {
        this.selectedType = selectedType;
    }
    public void addNewType() {
        if (libelle != null && !libelle.isEmpty()) {
            Type newType = new Type(this.libelle);
            DAOFactory.getTypeDAO().insert(newType);

            // Update the list of colors after adding the new color
            allTypes = DAOFactory.getTypeDAO().getAll();
            allTypes.add(0, new Type(0, "Choisir une Type"));

            // Clear the input field after adding the color
            libelle = null;

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Type added successfully");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Type name cannot be empty");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
}
