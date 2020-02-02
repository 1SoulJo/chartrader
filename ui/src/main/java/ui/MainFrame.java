package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final String TITLE = "charTrader";

    public MainFrame() throws HeadlessException {
        init();
    }

    public void start() {
        setVisible(true);
    }

    private void init() {
        getContentPane().setBackground(Color.DARK_GRAY);
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(
                dim.width / 2 - this.getSize().width / 2,
                dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(TITLE);
    }
}
