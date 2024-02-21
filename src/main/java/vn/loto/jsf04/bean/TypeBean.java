package vn.loto.jsf04.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.Type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class TypeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Type> allTypes;
    @Getter
    @Setter
    private List<Type> filteredType;
    @Getter
    @Setter
    private List<Type> selectedTypeList;
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
        selectedType = new Type();
        if (filteredType == null){
            filteredType = DAOFactory.getTypeDAO().getAll();
        }

        this.selectedTypeList = new ArrayList<>();
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

    public void openNew(){
        this.selectedType = new Type();
    }
    public boolean hasSelectedType() {
        return this.selectedTypeList != null && !this.selectedTypeList.isEmpty();
    }
    public String getDeleteButtonMessage() {

        if (hasSelectedType()) {
            int size = this.selectedTypeList.size();
            return size > 1 ? size + " Type selected" : "1 type selected";
        }

        return "Delete";
    }
    public void deteleType(){
        if (selectedType != null) {
            filteredType.remove(selectedType);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Type supprimé"));
            //PrimeFaces.current().executeScript("PF('deleteColorDialog').hide()");
            PrimeFaces.current().ajax().update("modifyType:messages", "modifyType:types");
            DAOFactory.getTypeDAO().delete(selectedType);
        }
    }

    public void deleteSelectedType(){
        selectedTypeList.add(selectedType); // Add the single selected color to the list
        if (selectedTypeList.size() > 0) {
            boolean success = DAOFactory.getTypeDAO().deleteMultiple(selectedTypeList);
            if (success) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Types supprimés"));
                // Update the list of colors after deletion
                filteredType.removeAll(selectedTypeList);
                PrimeFaces.current().executeScript("PF('deleteTypeDialog').hide()");
                PrimeFaces.current().ajax().update("modifyType:messages", "modifyType:types");

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error de la suppression des types"));
            }
            selectedType = null;
        }
    }



    public void updateType() {
        if (selectedType != null) {
            if (DAOFactory.getTypeDAO().update(selectedType)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Type mis à jour avec succès"));
                PrimeFaces.current().executeScript("PF('manageTypeDialog').hide()");
                PrimeFaces.current().ajax().update("modifyType:messages", "modifyType:types");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error de la mise à jour du type"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aucun type sélectionné pour la mise à jour"));
        }
    }

    public void addNewType() {
        if (libelle != null && !libelle.isEmpty()) {
            boolean typeExists = allTypes.stream()
                    .anyMatch(type -> type.getLibelle().equalsIgnoreCase(libelle));

            if (typeExists) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Type name already exists"));
                PrimeFaces.current().ajax().update("modifyType:messages");
                return; // Exit the method if color name already exists
            }
            Type newType = new Type(this.libelle);
            DAOFactory.getTypeDAO().insert(newType);

            // Update the list of colors after adding the new color
            allTypes = DAOFactory.getTypeDAO().getAll();
            allTypes.add(0, new Type(0, "Choisir une Type"));

            // Clear the input field after adding the color
            libelle = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Type added successfully"));
            PrimeFaces.current().executeScript("PF('addTypeDialog').hide()");
            PrimeFaces.current().ajax().update("modifyType:messages", "modifyType:types");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Type name cannot be empty"));
        }

    }
}
