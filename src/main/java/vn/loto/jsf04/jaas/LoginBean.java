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
import vn.loto.jsf04.bean.IdentificationBean;

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
    @Inject
    FacesContext facesContext;
    @Inject
    ExternalContext externalContext;

    private String originalURI;
    @Inject
    private IdentificationBean identificationBean;


    @PostConstruct
    public void init(){
        originalURI = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);
        if (originalURI != null) {
            int indexOfFaces = originalURI.indexOf("/faces");
            if (indexOfFaces != -1) { // Check if "/faces" exists in the originalURI
                originalURI = originalURI.substring(indexOfFaces); // Only substring if "/faces" exists
                if (originalQuery != null){
                    originalURI += "?" + originalQuery;
                }
            }
        }

        /*
        originalURI = originalURI.substring(originalURI.indexOf("/faces"));

        originalURI += "?faces-redirect=true";
        */

    }
    public IdentificationBean getIdentificationBean() {
        return identificationBean;
    }

    public void setIdentificationBean(IdentificationBean identificationBean) {
        this.identificationBean = identificationBean;
    }


    public String login(){
        try {
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            request.login(username, password);
            //return originalURI;
            // Set username in UtilisateurBean
            identificationBean.setLogin(username);
            identificationBean.setOldPassword(password);
            // If login is successful, redirect to the original URI if available, or to a default page
            if (originalURI != null && !originalURI.isEmpty()) {
                return originalURI + "?faces-redirect=true";
            } else {
               return "/faces/Index.xhtml?faces-redirect=true"; // Change this to your desired landing page
            }

        }
        catch (ServletException servletException){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errors: Login failed", null));
            return "/faces/login/Error.xhtml";
        }
    }



}
