package com.project.client.object;

/**
 * Class used to send login information to the server in loginMenu UI application.
 *
 * @author Minh Duy
 */

public class userAuth {
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public userAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public userAuth() {
    }

    @Override
    public String toString() {
        return "userAuth{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof userAuth userAuth)) return false;

        if (getUsername() != null ? !getUsername().equals(userAuth.getUsername()) : userAuth.getUsername() != null)
            return false;
        return getPassword() != null ? getPassword().equals(userAuth.getPassword()) : userAuth.getPassword() == null;
    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }
}
