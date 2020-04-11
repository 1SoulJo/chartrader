package ui.frame;

import com.google.common.eventbus.Subscribe;
import event.EventBusUtil;
import event.MainViewEvent;
import ui.menu.MenuBar;

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
    private PositionView position;

    public Main() throws HeadlessException {
        setVisible(true);

        init();

        // Register event listener
        EventBusUtil.get().register(this);
    }

    public void start() {
        EventQueue.invokeLater(() -> {
            Dimension fullScreenDim = getSize();
            fullScreenDim.height -= 60;
            fullScreenDim.width -= 15;

            trade.setSize((int)(fullScreenDim.width * 0.3), (int)(fullScreenDim.height * 0.3));
            trade.setLocation(0, 0);

            position.setSize((int)(fullScreenDim.width * 0.3), (int)(fullScreenDim.height * 0.2));
            position.setLocation(0, trade.getHeight());

            chart.setSize(fullScreenDim.width - trade.getWidth(), fullScreenDim.height);
            chart.setLocation(trade.getWidth(), 0);

            provider.setSize(fullScreenDim.width - chart.getWidth(), (int)(fullScreenDim.height * 0.5));
            provider.setLocation(0, trade.getHeight() + position.getHeight());

            showCenter(new SignInSignUp());
        });
    }

    private void init() {
        setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(TITLE);

        desktop = new JDesktopPane();
        desktop.setBackground(Color.DARK_GRAY);
        setContentPane(desktop);

        setJMenuBar(new MenuBar());
        provider = new Provider();
        trade = new Trade();
        chart = new Chart();
        position = new PositionView();

        desktop.add(provider);
        desktop.add(trade);
        desktop.add(chart);
        desktop.add(position);
    }

    public void showCenter(JInternalFrame component) {
        desktop.add(component);

        component.setLocation(
                (desktop.getWidth() - component.getWidth()) / 2,
                (desktop.getHeight() - component.getHeight()) / 2);
        component.moveToFront();
        try {
            component.setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Event Listener for any event that main view is responsible
     * @param e MainViewEvent
     */
    @Subscribe
    public void onMainViewEvent(MainViewEvent e) {
        switch (e.getType()) {
            case MainViewEvent.TOGGLE_VIEW_PROVIDER:
                provider.setVisible(!provider.isVisible());
                break;
            case MainViewEvent.TOGGLE_VIEW_TRADE:
                trade.setVisible(!trade.isVisible());
                break;
            case MainViewEvent.TOGGLE_VIEW_CHART:
                chart.setVisible(!chart.isVisible());
                break;
            case MainViewEvent.TOGGLE_VIEW_POSITION:
                position.setVisible(!position.isVisible());
                break;
            case MainViewEvent.PROVIDER_ADD_NEW:
                showCenter(new AddNew());
                break;
            case MainViewEvent.TRADE_OPEN_ORDER:
                showCenter(new Order(e.getIntParam()));
                break;
            case MainViewEvent.TOOLS_TRADE_HISTORY:
                showCenter(new TradeHistory());
                break;
            case MainViewEvent.SHOW_SIGN_IN:
                showCenter(new SignInSignUp());
                break;
            default:
                break;
        }
    }
}
