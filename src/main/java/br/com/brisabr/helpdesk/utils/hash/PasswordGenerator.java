package br.com.brisabr.helpdesk.utils.hash;

import java.security.SecureRandom;
import java.util.UUID;

public class PasswordGenerator {
    public static String generateRandomPassword() {
        SecureRandom randomGenerator = new SecureRandom();
            String uuidStr = UUID.randomUUID().toString().replace("-", "");
            String randomLongAsHex = Long.toHexString(randomGenerator.nextLong());
        return uuidStr + randomLongAsHex;
    }
}
