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

    private Roles roleUser = new Roles();

    public Utilisateur(){

    }

    public Utilisateur( String username, String password, Roles roleUser){
        this.username = username;
        this.password = password;
        this.roleUser = roleUser;
    }
    public Utilisateur(String username, String password){
        this.username = username;
        this.password = password;
        this.roleUser = new Roles();
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
    public Roles getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(Roles roleUser) {
        this.roleUser = roleUser;
    }
}
