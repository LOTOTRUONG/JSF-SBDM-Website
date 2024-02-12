package vn.loto.jsf04.BEAN;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import vn.loto.jsf04.DAO.DAOFactory;
import vn.loto.jsf04.METIER.Type;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TypeBean implements Serializable {
    private List<Type> allTypes;
    private Type selectedType;
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

}
