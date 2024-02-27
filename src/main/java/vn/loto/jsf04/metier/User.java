package vn.loto.jsf04.metier;

import lombok.Getter;
import lombok.Setter;
import vn.loto.jsf04.security.HashPassword;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class User {
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String roles;

    public User(){

    }

    public User(String login, String password, String roles){
        this.login = login;
        this.password = password;
        this.roles = roles;
    }
    public User(String login, String password){
        this.login = login;
        this.password = password;
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
}
