package ui.frame;

import ui.menu.IMenuEventListener;
import ui.menu.MenuBar;
import ui.menu.ViewTypes;

import javax.swing.*;
import java.awt.*;

/**
 * Frame for main window.
 */
public class Main extends JFrame {
    private static final String TITLE = "charTrader";

    private static final int INITIAL_WIDTH = 800;
    private static final int INITIAL_HEIGHT = 600;

    private JDesktopPane desktop;
    private Provider provider;
    private Trade trade;
    private CandleChart chart;

    public Main(IMenuEventListener listener) throws HeadlessException {
        setVisible(true);

        init(listener);
    }

    public void start() {
        EventQueue.invokeLater(() -> {
            Dimension fullScreenDim = getSize();
            fullScreenDim.height -= 50;

            provider.setSize((int)(fullScreenDim.width * 0.6), (int)(fullScreenDim.height * 0.3));

            trade.setSize(fullScreenDim.width - provider.getWidth(), (int)(fullScreenDim.height * 0.3));
            trade.setLocation(provider.getWidth(), 0);

            chart.setSize(fullScreenDim.width, fullScreenDim.height - provider.getHeight());
            chart.setLocation(0, (int)(fullScreenDim.height * 0.3));
        });
    }

    private void init(IMenuEventListener listener) {
        setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(TITLE);

        desktop = new JDesktopPane();
        desktop.setBackground(Color.DARK_GRAY);
        setContentPane(desktop);

        setJMenuBar(new MenuBar(listener));
        provider = new Provider();
        trade = new Trade();
//        chart = new Chart();
        chart = new CandleChart();

        desktop.add(provider);
        desktop.add(trade);
        desktop.add(chart);
    }

    public void toggleView(String type) {
        switch (type) {
            case ViewTypes.PROVIDER:
                provider.setVisible(!provider.isVisible());
                break;
            case ViewTypes.TRADE:
                trade.setVisible(!trade.isVisible());
                break;
            case ViewTypes.CHART:
                chart.setVisible(!chart.isVisible());
                break;
            default:
                break;
        }
    }
}
