package ui.menu;

import event.EventBusUtil;
import event.MainViewEvent;

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
    private JMenu tools = new JMenu("Tools");

    // MenuItem
    private JMenuItem fSignIn = new JMenuItem("Sign In");
    private JMenuItem fExit = new JMenuItem("Exit");

    private JMenuItem vProvider = new JMenuItem("Provider");
    private JMenuItem vTrade = new JMenuItem("Trade");
    private JMenuItem vChart = new JMenuItem("Chart");
    private JMenuItem vPosition = new JMenuItem("Position");

    private JMenuItem tTradeHistory = new JMenuItem("Trade History");

    public MenuBar() {
        init();
    }

    private void init() {
        EventQueue.invokeLater(() -> {
            // Init MenuBar
            setVisible(true);
            add(file);
            add(view);
            add(tools);

            // Init File menu
            fSignIn.addActionListener((e) -> {
                EventBusUtil.get().post(new MainViewEvent(MainViewEvent.SHOW_SIGN_IN));
            });
            fExit.addActionListener((e) -> System.exit(0));
            fExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.ALT_DOWN_MASK));

            file.setMnemonic(KeyEvent.VK_F);
            file.add(fSignIn);
            file.add(fExit);

            // Init View menu
            vProvider.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.ALT_DOWN_MASK));
            vProvider.addActionListener(
                    (e) -> EventBusUtil.get().post(new MainViewEvent(MainViewEvent.TOGGLE_VIEW_PROVIDER)));

            vTrade.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.ALT_DOWN_MASK));
            vTrade.addActionListener(
                    (e) -> EventBusUtil.get().post(new MainViewEvent(MainViewEvent.TOGGLE_VIEW_TRADE)));

            vChart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.ALT_DOWN_MASK));
            vChart.addActionListener(
                    (e) -> EventBusUtil.get().post(new MainViewEvent(MainViewEvent.TOGGLE_VIEW_CHART)));

            vPosition.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.ALT_DOWN_MASK));
            vPosition.addActionListener(
                    (e) -> EventBusUtil.get().post(new MainViewEvent(MainViewEvent.TOGGLE_VIEW_POSITION)));

            view.setMnemonic(KeyEvent.VK_V);

            view.add(vTrade);
            view.add(vProvider);
            view.add(vPosition);
            view.add(vChart);

            tTradeHistory.addActionListener(
                    (e) -> EventBusUtil.get().post(new MainViewEvent(MainViewEvent.TOOLS_TRADE_HISTORY)));
            tools.setMnemonic(KeyEvent.VK_T);
            tools.add(tTradeHistory);
        });
    }
}
