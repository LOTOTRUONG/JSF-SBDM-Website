package vn.loto.jsf04.security;

public class Main {

    public static void main(String[] args) {
        try {
            String password = "password123";
            String hashedPassword = Argon2Util.getHashedPassword(password); // Corrected usage of hash method
            System.out.println("Hashed Password: " + hashedPassword);

            boolean isVerified = Argon2Util.validate(password, hashedPassword); // Corrected usage of verify method
            System.out.println("Password Verified: " + isVerified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
