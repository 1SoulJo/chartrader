package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Frame for main window.
 */
public class MainFrame extends JFrame {
    private static final String TITLE = "charTrader";
    private static final int INITIAL_WIDTH = 800;
    private static final int INITIAL_HEIGHT = 600;

    public MainFrame() throws HeadlessException {
        init();
    }

    public void start() {
        setVisible(true);
    }

    private void init() {
        setUndecorated(true);
        getContentPane().setBackground(Color.DARK_GRAY);
        setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(
                dim.width / 2 - this.getSize().width / 2,
                dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(TITLE);
    }
}
