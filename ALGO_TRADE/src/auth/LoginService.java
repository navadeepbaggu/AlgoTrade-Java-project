package auth;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import storage.AccountFile;

public class LoginService {
    private final AccountFile accountFile;
    private final PasswordHelper passwordHelper;

    public LoginService(AccountFile accountFile, PasswordHelper passwordHelper) {
        this.accountFile = accountFile;
        this.passwordHelper = passwordHelper;
    }

    public String register(String username, String password) throws IOException {
        if (username == null || !username.matches("[A-Za-z0-9_]{3,20}")) {
            return "Username must be 3–20 characters using letters, numbers, or underscores.";
        }

        if (password == null || password.length() < 8) {
            return "Password must be at least 8 characters.";
        }

        List<UserAccount> accounts = accountFile.loadAccounts();

        for (UserAccount account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                return "That username is already registered.";
            }
        }

        String salt = passwordHelper.createSalt();
        String hash = passwordHelper.hashPassword(password, salt);

        UserAccount newAccount = new UserAccount(username, salt, hash);
        accountFile.saveAccount(newAccount);

        return "Registration successful.";
    }

    public Optional<UserAccount> login(String username, String password) throws IOException {
        if (username == null || password == null) {
            return Optional.empty();
        }

        List<UserAccount> accounts = accountFile.loadAccounts();

        for (UserAccount account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                boolean correctPassword = passwordHelper.passwordMatches(
                        password,
                        account.getSalt(),
                        account.getPasswordHash()
                );

                if (correctPassword) {
                    return Optional.of(account);
                }

                return Optional.empty();
            }
        }

        return Optional.empty();
    }
}