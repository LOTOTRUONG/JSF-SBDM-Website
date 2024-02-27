package vn.loto.jsf04.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.User;
import vn.loto.jsf04.security.HashPassword;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class UserBean implements Serializable {
    private List<User> allUtilisateurs;
    @Getter
    @Setter
    private List<String> allRoles;

    @Getter
    @Setter
    private User selectedUtilisateur;

    @Getter
    @Setter
    private  List<User> selectedUtilisateurList;

    @Getter
    @Setter
    private String login;

    private String oldPassword;

    @Getter
    private String newPassword;

    @Getter
    @Setter
    private String confirmPassword;

    @PostConstruct
    public void init(){
        if (allUtilisateurs == null) {
            allUtilisateurs = DAOFactory.getUserDAO().getAll();
        }
        selectedUtilisateur = new User();
        this.selectedUtilisateurList = new ArrayList<>();
        allRoles = DAOFactory.getUserDAO().getAllRoles();
    }

    public List<User> getAllUtilisateurs() {
        return allUtilisateurs;
    }
    public void setNewPassword(String newPassword)throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.newPassword = HashPassword.generate(newPassword);
    }

    public void setAllUtilisateurs(List<User> allUtilisateurs) {
        this.allUtilisateurs = allUtilisateurs;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    public String getOldPassword(){
        return oldPassword;
    }
    public void openNew() {
        this.selectedUtilisateur = new User();
    }

    public void createNewUser(){
        User newUser = new User(this.login, this.newPassword);
        DAOFactory.getUserDAO().insert(newUser);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Member added successfully"));
        PrimeFaces.current().ajax().update("modifyUser:users" );
    }

    public String changePassword() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (!this.newPassword.equals(this.confirmPassword)) {
            context.addMessage(null, new FacesMessage("The new password doesn't match with the confirmation! Try again."));
            return null;
        }

        // Checking whether the new password is equal to the current one or not
        if (this.newPassword.equals(this.oldPassword)) {
            context.addMessage(null, new FacesMessage("The new password can't be the same as the old one! Try again."));
            return null;
        }

        try {
            User existingUser = DAOFactory.getUserDAO().getByUsername(this.login);
            if (existingUser == null) {
                context.addMessage(null, new FacesMessage("User not found!"));
                return null;
            }

            if (!HashPassword.validate(this.oldPassword, existingUser.getPassword())) {
                context.addMessage(null, new FacesMessage("Incorrect old password!"));
                return null;
            }

            // Password update in the database
            DAOFactory.getUserDAO().update(existingUser);

            // Clear the fields after successful password change
//            this.oldPassword = null;
//            this.newPassword = null;
//            this.confirmPassword = null;

            context.addMessage(null, new FacesMessage("Password successfully updated for '" + this.login + "'."));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // Handle exceptions
            context.addMessage(null, new FacesMessage("An error occurred while changing the password. Please try again later."));
            e.printStackTrace();
        }

        return null;
    }

    public void deleteUser(){
        if (selectedUtilisateur != null) {
            allUtilisateurs.remove(selectedUtilisateur);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User Removed"));
            //PrimeFaces.current().executeScript("PF('deleteColorDialog').hide()");
            PrimeFaces.current().ajax().update("modifyUser:message", "modifyUser:users");
            DAOFactory.getUserDAO().delete(selectedUtilisateur);
        }
    }

    public void updateUser() {
        if (selectedUtilisateur != null) {
            if (DAOFactory.getUserDAO().updateUsernameRole(selectedUtilisateur)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User updated successfully"));
            }
            else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed to update user"));
            }
        }
    }

}
