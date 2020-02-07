package ui.frame;

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

    public Main() throws HeadlessException {
        init();
    }

    public void start() {
        // ToDo: Set constraints for size values
        setVisible(true);
        Dimension dim = getSize();
        dim.width *= 0.992;
        dim.height *= 0.96;

        Provider pv = new Provider();
        pv.setSize((int)(dim.width * 0.6), (int)(dim.height * 0.3));

        Trade tr = new Trade();
        tr.setSize(dim.width - pv.getWidth(), (int)(dim.height * 0.3));
        tr.setLocation(pv.getWidth(), 0);

        Chart ch = new Chart();
        ch.setSize(dim.width, dim.height - pv.getHeight());
        ch.setLocation(0, (int)(dim.height * 0.3));

        Container desktop = getContentPane();
        desktop.add(pv);
        desktop.add(tr);
        desktop.add(ch);
    }

    private void init() {
        setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(
                dim.width / 2 - this.getSize().width / 2,
                dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(TITLE);

        JDesktopPane desktop = new JDesktopPane();
        desktop.setBackground(Color.DARK_GRAY);
        setContentPane(desktop);

        setJMenuBar(new MenuBar());
    }
}
