package vn.loto.jsf04.metier;

import lombok.Getter;
import lombok.Setter;
import vn.loto.jsf04.security.HashPassword;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class Identification {
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String password;

    private Roles roleUser = new Roles();

    public Identification(){

    }

    public Identification(String login, String password, Roles roleUser){
        this.login = login;
        this.password = password;
        this.roleUser = roleUser;
    }
    public Identification(String login, String password){
        this.login = login;
        this.password = password;
        this.roleUser = new Roles();
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
