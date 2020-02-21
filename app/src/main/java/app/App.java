/*
 * Main class of app.
 */
package app;

import event.EventBusUtil;
import event.MenuEventSubscriber;
import event.ProviderEventSubscriber;
import event.TradeEventSubscriber;
import ui.frame.Main;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

/**
 * Main application
 */
public class App {
    private static Main mainFrame;

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
        mainFrame = new Main();
        mainFrame.start();

        // EventBut init
        EventBusUtil.get().register(new MenuEventSubscriber(mainFrame));
        EventBusUtil.get().register(new ProviderEventSubscriber(mainFrame));
        EventBusUtil.get().register(new TradeEventSubscriber(mainFrame));
    }
}
