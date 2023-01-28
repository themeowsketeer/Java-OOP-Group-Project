package com.project.client.object;

public class accessToken {
    private static String token;

    private static Long userID;

    private static long roleID;

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

    public static long getRoleID() {
        return roleID;
    }

    public static void setRoleID(long roleID) {
        accessToken.roleID = roleID;
    }

    public accessToken() {
    }
}
