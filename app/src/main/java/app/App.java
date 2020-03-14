/*
 * Main class of app.
 */
package app;

import com.google.common.eventbus.Subscribe;
import event.*;
import ui.frame.Main;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

/**
 * Main application
 */
public class App {

    public static void main(String[] args) {
        // Set LookAndFeel theme
        UIManager.put("nimbusBase", new Color(50, 50, 50));
        UIManager.put("nimbusBlueGrey", new Color(100, 100, 100));
        UIManager.put("control", new Color(100, 100, 100));
        try {
            UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());
        } catch (ClassNotFoundException|InstantiationException
                |IllegalAccessException|UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Start main
        Main mainFrame = new Main();
        mainFrame.start();
    }
}
