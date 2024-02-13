package vn.loto.jsf04.jaas;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Named
@ViewScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;
    private String requete;
    @Inject
    FacesContext facesContext;
    @Inject
    ExternalContext externalContext;

    private String originalURI;
    @PostConstruct
    public void init(){
        originalURI = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);
        String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);
        originalURI = originalURI.substring(originalURI.indexOf("/faces"));
        if (originalQuery != null){
            originalURI += "?" + originalQuery;
        }
    }

    public String login(){
        try {
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            request.login(username, password);
            return originalURI;

            // If login is successful, redirect to the original URI if available, or to a default page
            //if (originalURI != null && !originalURI.isEmpty()) {
              //  return originalURI + "?faces-redirect=true";
          //  } else {
            //    return "/faces/Index.xhtml?faces-redirect=true"; // Change this to your desired landing page
          //  }
        }
        catch (ServletException servletException){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errors: Login failed", null));
            return "login/Error.xhtml";
        }
    }

    public String logout() {
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            request.logout();
            return "/login.xhtml?faces-redirect=true";
        } catch (ServletException e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logout failed", null));
            return null; // Stay on the same page
        }
    }
}
