package ui.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Custom MenuBar
 */
public class MenuBar extends JMenuBar {
    // Menu
    private JMenu file = new JMenu("File");
    private JMenu view = new JMenu("View");

    // MenuItem
    private JMenuItem fOpen = new JMenuItem("Open");
    private JMenuItem fExit = new JMenuItem("Exit");
    private JMenuItem vProvider = new JMenuItem("Provider");
    private JMenuItem vTrader = new JMenuItem("Trader");
    private JMenuItem vChart = new JMenuItem("Chart");

    public MenuBar() {
        init();
    }

    private void init() {
        EventQueue.invokeLater(() -> {
            // Init MenuBar
            setVisible(true);
            add(file);
            add(view);

            // Init File menu
            fOpen.addActionListener((e) -> {});
            fOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));

            fExit.addActionListener((e) -> System.exit(0));
            fExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));

            file.setMnemonic(KeyEvent.VK_F);
            file.add(fOpen);
            file.add(fExit);

            // Init View menu
            vProvider.addActionListener((e) -> {});
            vTrader.addActionListener((e) -> {});
            vChart.addActionListener((e) -> {});

            view.setMnemonic(KeyEvent.VK_V);
            view.add(vProvider);
            view.add(vTrader);
            view.add(vChart);
        });
    }
}
