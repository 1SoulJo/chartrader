package ui.frame;

import com.google.common.eventbus.Subscribe;
import event.EventBusUtil;
import event.MainViewEvent;
import event.TradeViewEvent;
import provider.entity.Transaction;
import app.TransactionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.util.List;

/**
 * Trade frame.
 */
public class Trade extends JInternalFrame {
    private final String[] column = { "Instrument", "Date", "Price", "Type", "Quantity", "Completed"};
    private JTable table;
    DefaultTableModel tableModel = new DefaultTableModel(column, 0);

    public Trade() {
        init();

        EventBusUtil.get().register(this);
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
        c.weightx = 1.0; c.weighty = 0.9;
        c.gridx = 0; c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        table = createTable();
        add(new JScrollPane(table), c);

        // add buttons
        JButton b;
        c.weightx = 0.2; c.weighty = 0.01;
        c.gridwidth = 1;
        c.gridx = 0; c.gridy = 2;
        b = new JButton("Buy");
        b.addActionListener((e) -> {
            MainViewEvent event = new MainViewEvent(MainViewEvent.TRADE_OPEN_ORDER);
            event.setIntParam(Transaction.TYPE_BUY);
            EventBusUtil.get().post(event);
        });
        add(b, c);

        c.gridx = 1; c.gridy = 2;
        b = new JButton("Sell");
        b.addActionListener((e) -> {
            MainViewEvent event = new MainViewEvent(MainViewEvent.TRADE_OPEN_ORDER);
            event.setIntParam(Transaction.TYPE_SELL);
            EventBusUtil.get().post(event);
        });
        add(b, c);

        c.gridx = 0; c.gridy = 3;
        c.gridwidth = 2;
        b = new JButton("Cancel");
        b.addActionListener((e) -> {
            int id = table.getSelectedRow();
            if (id > -1) {
                if (TransactionManager.get().cancelTransaction(id)) {
                    tableModel.removeRow(id);
                }
            }
        });
        add(b, c);
    }

    private JTable createTable() {
        JTable t = new JTable(tableModel);
        t.setBackground(Color.GRAY);
        t.setForeground(Color.WHITE);
        t.setShowHorizontalLines(true);
        t.setShowVerticalLines(true);

        return t;
    }

    @Subscribe
    public void onTradeEvent(TradeViewEvent e) {
        switch (e.getType()) {
            case TradeViewEvent.UPDATE_TABLE:
                System.out.println("UPDATE_TABLE");
                List<Transaction> list = TransactionManager.get().getTransactions();
                if (list.size() > tableModel.getRowCount()) {
                    for (int i = tableModel.getRowCount(); i < list.size(); i++) {
                        Transaction t = list.get(i);
                        tableModel.addRow(new Object[]{
                                t.getInstrumentId(), t.getDate(),
                                t.getPrice(), t.getType() == 0 ? "Buy" : "Sell", t.getQuantity(), t.isCompleted()
                        });
                    }
                }
                table.repaint();
                break;

            case TradeViewEvent.UPDATE_ROW:
                System.out.println("UDPATE ROW");
                int index = e.getUpdateIndex();
                tableModel.setValueAt(true, index, 5);
                break;
            default:
                break;
        }
    }
}
