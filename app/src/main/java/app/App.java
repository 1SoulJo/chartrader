/*
 * Main class of app.
 */
package app;

import ui.frame.Main;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * Main application
 */
public class App {
    public static void main(String[] args) {
        // Set LookAndFeel theme
        try {
            UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());
        } catch (ClassNotFoundException|InstantiationException
                |IllegalAccessException|UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Start main
        new Main().start();
    }
}
