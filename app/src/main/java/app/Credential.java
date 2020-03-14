package app;

import lombok.Getter;

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
    public static Credential getInstance() {
        if (Instance == null) {
            Instance = new Credential();
        }
        return Instance;
    }

    public void logIn(String userId) {
        isLoggedIn = true;
        this.userId = userId;
    }

    public void logOut() {
        isLoggedIn = false;
        userId = null;
    }
}
