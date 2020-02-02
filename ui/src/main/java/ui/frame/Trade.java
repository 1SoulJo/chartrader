package ui.frame;

import javax.swing.*;
import java.awt.*;

/**
 * Trade frame.
 */
public class Trade extends JInternalFrame {
    public Trade() {
        init();
    }

    private void init() {
        setTitle("Trade");
        setBackground(Color.GRAY);
        setSize(400, 300);

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        setVisible(true);
    }
}