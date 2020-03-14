package ui.frame;

import event.EventBusUtil;
import event.TradeEvent;

import javax.swing.*;
import java.awt.*;

/**
 * Trade history administration view
 */
public class TradeHistory extends JInternalFrame {
    public TradeHistory() {
        init();
    }

    private void init() {
        setTitle("Trade History");
        setBackground(Color.DARK_GRAY);

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridBagLayout());

        setVisible(true);

        GridBagConstraints c = new GridBagConstraints();

        // add table
        c.weightx = 1.0; c.weighty = 0.5;
        c.gridx = 0; c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(createTable()), c);

        // add buttons
        JButton b;

        c.gridx = 0; c.gridy = 3;
        b = new JButton("Import file");
        b.addActionListener((e) -> {
            EventBusUtil.get().post(new TradeEvent(TradeEvent.OPEN_ORDER));
        });
        add(b, c);
    }

    private JTable createTable() {
        String[] column = { "Name", "Position", "Price", "Amount", "P/L"};
        String[][] data = {
                { "S&P 500", "Long", "3123.25", "3", "$ 240" }
        };

        JTable t = new JTable(data, column);
        t.setBackground(Color.GRAY);
        t.setForeground(Color.WHITE);
        t.setShowHorizontalLines(true);
        t.setShowVerticalLines(true);

        return t;
    }
}
