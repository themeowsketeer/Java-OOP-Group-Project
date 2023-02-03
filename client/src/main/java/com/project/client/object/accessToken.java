package com.project.client.object;

/**
 * Class used to store access token, generated upon a successful login and used in
 * almost every request calls to the main server
 * </p>
 * Class also includes userId and roleId for specific functionality, such as distinguishing
 * UI accessibility between User and Admin and calling information that must be owned and viewed by
 * a specific client only.
 *
 * @author Minh Duy
 */

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
