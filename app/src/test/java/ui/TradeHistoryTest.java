package ui;

import org.junit.Test;
import ui.frame.TradeHistory;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class TradeHistoryTest {
    @Test
    public void testTradeHistoryView() {
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

        JFrame frame = new JFrame("Test Frame");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new TradeHistory());
        frame.setVisible(true);

        // Wait until closed
        while(frame.isVisible()) {}
    }
}
