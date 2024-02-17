package vn.loto.jsf04.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import java.io.Serializable;
@Named
@SessionScoped
public class FirstPageBean implements Serializable {
    private String pathToFaces;
    @Inject
    FacesContext facesContext;
    @Inject
    ExternalContext externalContext;

    @PostConstruct
    public void init(){
        pathToFaces = externalContext.getApplicationContextPath();
        pathToFaces += "/faces/";
    }

    public String getPathToFaces() {
        return pathToFaces;
    }
    public String logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            request.logout();
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            // Redirect to the login page
            return "/faces/Index.xhtml?faces-redirect=true";
        } catch (ServletException e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logout failed", null));
            return null; // Stay on the same page
        }
    }
}
