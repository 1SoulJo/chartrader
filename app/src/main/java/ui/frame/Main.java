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
    private Chart chart;

    public Main(IMenuEventListener listener) throws HeadlessException {
        init(listener);
    }

    public void start() {
        // ToDo: Set constraints for size values
        setVisible(true);
    }

    private void init(IMenuEventListener listener) {
        setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(
                dim.width / 2 - this.getSize().width / 2,
                dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(TITLE);

        desktop = new JDesktopPane();
        desktop.setBackground(Color.DARK_GRAY);
        setContentPane(desktop);

        setJMenuBar(new MenuBar(listener));

        EventQueue.invokeLater(() -> {
            Dimension fullScreenDim = getSize();
            fullScreenDim.width *= 0.992;
            fullScreenDim.height *= 0.95;

            provider = new Provider();
            provider.setSize((int)(fullScreenDim.width * 0.6), (int)(fullScreenDim.height * 0.3));

            trade = new Trade();
            trade.setSize(fullScreenDim.width - provider.getWidth(), (int)(fullScreenDim.height * 0.3));
            trade.setLocation(provider.getWidth(), 0);

            chart = new Chart();
            chart.setSize(fullScreenDim.width, fullScreenDim.height - provider.getHeight());
            chart.setLocation(0, (int)(fullScreenDim.height * 0.3));

            desktop.add(provider);
            desktop.add(trade);
            desktop.add(chart);
        });
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
