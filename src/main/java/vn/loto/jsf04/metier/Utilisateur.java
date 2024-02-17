package vn.loto.jsf04.metier;

import lombok.Getter;
import lombok.Setter;
import vn.loto.jsf04.security.HashPassword;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class Utilisateur {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private Roles roles;

    public Utilisateur(){

    }

    public Utilisateur( String username, String password, Roles roles){
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    public Utilisateur(String username, String password){
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.password = HashPassword.generate(password);
    }
    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }


}
