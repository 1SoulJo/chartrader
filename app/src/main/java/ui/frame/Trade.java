package ui.frame;

import javax.swing.*;
import javax.swing.border.MatteBorder;
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
        setBackground(Color.DARK_GRAY);

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridBagLayout());

        setVisible(true);

        String[] column = { "Name", "Position", "Price", "Amount", "P/L"};
        String[][] data = {
                { "S&P 500", "Long", "3123.25", "3", "$ 240" }
        };
        JTable t = new JTable(data, column);

        MatteBorder border = new MatteBorder(1, 1, 0, 0, Color.BLACK);
        t.setBorder(border);

        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1.0; c.weighty = 1.0;
        c.gridx = 0; c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(t), c);
    }
}
