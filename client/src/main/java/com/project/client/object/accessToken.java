package com.project.client.object;

public class accessToken {
    private static String token;

    private static Long userID;

    public static Long getUserID() {
        return userID;
    }

    public static void setUserID(Long userID) {
        accessToken.userID = userID;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        accessToken.token = token;
    }

    public accessToken() {
    }
}
