package app;

import lombok.Getter;
import provider.dao.AccountDao;

import javax.swing.*;

/**
 * Singleton manager class for user credential information
 */
public class Credential {
    private static Credential Instance;

    @Getter
    private boolean isLoggedIn = false;

    @Getter
    private String userId = null;

    private Credential() { }

    // Singleton getter
    public static Credential get() {
        if (Instance == null) {
            Instance = new Credential();
        }
        return Instance;
    }

    public boolean signIn(String user, String password) {
        if (AccountDao.get().getUser(user, password) != null) {
            isLoggedIn = true;
            this.userId = user;

            return true;
        } else {
            return false;
        }
    }

    public boolean signUp(String user, String password) {
        return AccountDao.get().save(user, password);
    }

    public void signOut() {
        isLoggedIn = false;
        userId = null;
    }
}
