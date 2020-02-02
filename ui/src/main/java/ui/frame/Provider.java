package ui.frame;

import javax.swing.*;
import java.awt.*;

/**
 * Provider frame.
 */
public class Provider extends JInternalFrame {
    public Provider() {
        init();
    }

    private void init() {
        setTitle("Provider");
        setBackground(Color.GRAY);
        setSize(400, 300);

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        setVisible(true);
    }
}
