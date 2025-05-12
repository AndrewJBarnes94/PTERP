package com.brocodesoftware.pterp_client;

public class LoginSession {
    private static boolean loggedIn = false;

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean value) {
        loggedIn = value;
    }
}
