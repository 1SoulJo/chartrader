package ui.frame;

import event.EventBusUtil;
import event.MainViewEvent;
import transaction.Transaction;
import transaction.TransactionDAO;
import ui.menu.MenuBar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Trade history administration view
 */
public class TradeHistory extends JInternalFrame {
    private static final String TITLE = "Trade History";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final String[] column = { "User", "Account Id", "Instrument", "Date", "Price", "Type", "Quantity"};
    private JTable table;

    public TradeHistory() {
        init();
    }

    private void init() {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
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
        table = createTable();
        add(new JScrollPane(table), c);

        // add buttons
        JButton b;

        c.gridx = 0; c.gridy = 3;
        b = new JButton("Import file");
        b.addActionListener((e) -> {
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(TradeHistory.this);
            File f = fc.getSelectedFile();
            updateData(TransactionDAO.getInstance().getTransaction(f.getPath()));
        });
        add(b, c);
    }

    private void updateData(ArrayList<Transaction> list) {
        DefaultTableModel tableModel = new DefaultTableModel(column, 0);
        for (Transaction t : list) {
            tableModel.addRow(new Object[]{t.getUserId(), t.getAccountId(), t.getInstrumentId(), t.getDate(),
                    t.getPrice(), t.getType() == 0 ? "Buy" : "Sell", t.getQuantity()});
        }
        table.setModel(tableModel);
        table.repaint();
    }

    private JTable createTable() {
        DefaultTableModel tableModel = new DefaultTableModel(column, 0);

        // default data
        for (Transaction t : TransactionDAO.getInstance().getTransaction()) {
            tableModel.addRow(new Object[]{t.getUserId(), t.getAccountId(), t.getInstrumentId(), t.getDate(),
            t.getPrice(), t.getType() == 0 ? "Buy" : "Sell", t.getQuantity()});
        }

        JTable t = new JTable(tableModel);
        t.setBackground(Color.GRAY);
        t.setForeground(Color.WHITE);
        t.setShowHorizontalLines(true);
        t.setShowVerticalLines(true);

        return t;
    }
}
