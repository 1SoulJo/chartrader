package ui.frame;

import event.EventBusUtil;
import event.MainViewEvent;
import transaction.Transaction;

import javax.swing.*;
import java.awt.*;

/**
 * Trade frame.
 */
public class Trade extends JInternalFrame {
    private JTable table;

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

        GridBagConstraints c = new GridBagConstraints();

        // add label
        c.gridx = 0; c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        add(new JLabel("Orders"), c);

        // add table
        c.weightx = 1.0; c.weighty = 0.5;
        c.gridx = 0; c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        table = createTable();
        add(new JScrollPane(table), c);

        // add label
        c.weightx = 0.2; c.weighty = 0.1;
        c.gridwidth = 1;
        c.gridx = 0; c.gridy = 2;
        add(new JLabel("Place order"), c);

        // add text box
        c.weightx = 0.8; c.weighty = 0.1;
        c.gridx = 1; c.gridy = 2;
        JTextField tf = new JTextField();
        add(tf, c);

        // add buttons
        JButton b;

        c.gridx = 0; c.gridy = 3;
        b = new JButton("Buy");
        b.addActionListener((e) -> {
            MainViewEvent event = new MainViewEvent(MainViewEvent.TRADE_OPEN_ORDER);
            event.setIntParam(Transaction.TYPE_BUY);
            EventBusUtil.get().post(event);
        });
        add(b, c);

        c.gridx = 1; c.gridy = 3;
        b = new JButton("Sell");
        b.addActionListener((e) -> {
            MainViewEvent event = new MainViewEvent(MainViewEvent.TRADE_OPEN_ORDER);
            event.setIntParam(Transaction.TYPE_SELL);
            EventBusUtil.get().post(event);
        });
        add(b, c);
    }

    private JTable createTable() {
        String[] column = { "Name", "Position", "Price", "Quantity", "P/L"};
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
