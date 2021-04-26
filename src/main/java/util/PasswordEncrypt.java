package util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

public class PasswordEncrypt {

    public static String encrypt(String before) {
        return BCrypt.hashpw(before, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }
}
