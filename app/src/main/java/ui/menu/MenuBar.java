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
    private JMenuItem fExit = new JMenuItem("Exit");
    private JMenuItem vProvider = new JMenuItem("Provider");
    private JMenuItem vTrade = new JMenuItem("Trade");
    private JMenuItem vChart = new JMenuItem("Chart");

    // Event listener
    private final IMenuEventListener listener;

    public MenuBar(IMenuEventListener listener) {
        init();
        this.listener = listener;
    }

    private void init() {
        EventQueue.invokeLater(() -> {
            // Init MenuBar
            setVisible(true);
            add(file);
            add(view);

            // Init File menu
            fExit.addActionListener((e) -> System.exit(0));
            fExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));

            file.setMnemonic(KeyEvent.VK_F);
            file.add(fExit);

            // Init View menu
            vProvider.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.ALT_DOWN_MASK));
            vProvider.addActionListener((e) -> {
                listener.onToggleView(ViewTypes.PROVIDER);
            });
            vTrade.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.ALT_DOWN_MASK));
            vTrade.addActionListener((e) -> {
                listener.onToggleView(ViewTypes.TRADE);
            });
            vChart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.ALT_DOWN_MASK));
            vChart.addActionListener((e) -> {
                listener.onToggleView(ViewTypes.CHART);
            });

            view.setMnemonic(KeyEvent.VK_V);
            view.add(vProvider);
            view.add(vTrade);
            view.add(vChart);
        });
    }
}
