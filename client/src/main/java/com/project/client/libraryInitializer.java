package com.project.client;

import com.project.client.ui.loginMenu.LoginApplication;


/**
 * A wrapper class that's declared as main class inside the executable jar file.
 * The class that is wrapped and will be executed in this block is loginApplication.
 * <p>
 * Direct execution cannot be applied because for JavaFX
 * in Maven compiler, main class must not extend Application.
 */
public class libraryInitializer {
    public static void main(String[] args) {
        LoginApplication.main(args);
    }
}
