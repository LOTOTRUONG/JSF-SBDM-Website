package vn.loto.jsf04.jaas;

import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.Roles;
import vn.loto.jsf04.metier.Identification;
import vn.loto.jsf04.security.HashPassword;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLoginModule implements LoginModule {
    private CallbackHandler callbackHandler;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private RolePrincipal rolePrincipal;
    private List<String> userGroups;
    private Map options;
    private Map sharedState;

    private boolean debug = false;
    private static final Logger logger = Logger.getLogger(MyLoginModule.class.getName());
    private String username = null;
    private String password = null;
    private String passwordHash;
    private boolean isAuthenticated = false;
    private boolean commitSucceeded = false;

    public MyLoginModule(){
        super();
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options){
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
        this.userGroups = new ArrayList<>();
        if ("true".equalsIgnoreCase((String) options.get("debug"))){
            ConsoleHandler consoleHandler = new ConsoleHandler();
            logger.addHandler(consoleHandler);
            debug = true;
        }
    }


    @Override
    public boolean login() throws LoginException{
        // If no handler is specified throw a error
        if (callbackHandler == null) {
            throw new LoginException("Error: no CallbackHandler available to recieve authentication information from the user");
        }
        // Declare the callbacks based on the JAAS spec
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);
        try {
            //Handle the callback and recieve the sent inforamation
            callbackHandler.handle(callbacks);
            username = ((NameCallback) callbacks[0]).getName();
            password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());
            // Debug the username / password
            if (debug) {
                logger.log(Level.INFO, "Username: {0}", username);
                logger.log(Level.INFO, "Password: {0}", password);
            }
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                throw new LoginException("Data specified had null values");
            }
            Identification utilisateur = new Identification(username, password);
            Identification users = DAOFactory.getUtilisateurDAO().getByUsername(username);

            if (utilisateur.getUsername().equals(users.getUsername()) && HashPassword.validate(utilisateur.getPassword(), users.getPassword())) {
                if ("admin".equals(users.getRoleUser().getName())) {
                    // Add all role names to userGroups if the user is an admin
                    List<Roles> roles = DAOFactory.getRolesDAO().getAll(); // Assuming you have a method to retrieve all roles
                    for (Roles role : roles) {
                        userGroups.add(role.getName());
                    }
                } else {
                    userGroups.add(users.getRoleUser().getName());
                }
                isAuthenticated = true;
                return true;
            }

            throw new LoginException("Authentication failed");

        } catch (IOException | UnsupportedCallbackException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new LoginException(e.getMessage());
        }


    }

    /*add username / roles to the principal*/


    @Override
    public boolean commit() throws LoginException{
        if (!isAuthenticated) {
            return false;
        } else {

            userPrincipal = new UserPrincipal(username);
            subject.getPrincipals().add(userPrincipal);

            if (userGroups != null && userGroups.size() > 0) {
                for (String groupName : userGroups) {
                    rolePrincipal = new RolePrincipal(groupName);
                    subject.getPrincipals().add(rolePrincipal);
                }
            }

            commitSucceeded = true;

            return true;
        }
    }


    @Override
    public boolean abort() throws LoginException {
        if (!isAuthenticated)
            return false;
        if (isAuthenticated && !commitSucceeded) {
            isAuthenticated = false;
            username = null;
            password = null;
            userPrincipal = null;
        } else {
            logout();
        }
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        isAuthenticated = false;
        isAuthenticated = commitSucceeded;
        subject.getPrincipals().clear();
        return true;
    }
}
