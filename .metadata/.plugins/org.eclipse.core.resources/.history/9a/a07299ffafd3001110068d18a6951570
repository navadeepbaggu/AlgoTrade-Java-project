package auth;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHelper {
    private static final int ITERATIONS = 120_000;
    private static final int KEY_LENGTH = 256;

    private final SecureRandom random = new SecureRandom();

    public String createSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

    public String hashPassword(String password, String salt) {
        try {
            byte[] saltBytes = Base64.getDecoder().decode(salt);

            PBEKeySpec keySpec = new PBEKeySpec(
                    password.toCharArray(),
                    saltBytes,
                    ITERATIONS,
                    KEY_LENGTH
            );

            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = factory.generateSecret(keySpec).getEncoded();
            keySpec.clearPassword();

            return Base64.getEncoder().encodeToString(hash);

        } catch (GeneralSecurityException exception) {
            throw new IllegalStateException("Could not hash the password.", exception);
        }
    }

    public boolean passwordMatches(
            String enteredPassword,
            String savedSalt,
            String savedHash) {

        String enteredHash = hashPassword(enteredPassword, savedSalt);
        return enteredHash.equals(savedHash);
    }
}