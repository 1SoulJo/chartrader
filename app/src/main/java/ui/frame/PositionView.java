package ui.frame;

import com.google.common.eventbus.Subscribe;
import event.EventBusUtil;
import event.PositionViewEvent;
import provider.entity.Position;
import app.TransactionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

public class PositionView extends JInternalFrame {
    private final String[] column = { "Instrument", "Price", "Quantity", "Incomplete P&L", "Complete P&L"};
    private JTable table;
    DefaultTableModel tableModel = new DefaultTableModel(column, 0);

    public PositionView() {
        init();

        EventBusUtil.get().register(this);
    }

    private void init() {
        setTitle("Position");
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
        add(new JLabel("Current Positions"), c);

        // add table
        c.weightx = 1.0; c.weighty = 0.9;
        c.gridx = 0; c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        table = createTable();
        add(new JScrollPane(table), c);
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
    public void onPositionChangeEvent(PositionViewEvent e) {
        switch (e.getType()) {
            case PositionViewEvent.UPDATE_TABLE:
                tableModel.setRowCount(0);
                HashMap<String, Position> map = TransactionManager.get().getPositions();
                for (Position p : map.values()) {
                    tableModel.addRow(new Object[]{
                            p.getInstrument(),
                            String.format("%.02f", p.getAveragePrice()),
                            p.getQuantity(),
                            String.format("%.02f", p.getIncompletePL()),
                            String.format("%.02f", p.getCompletePL())
                    });
                }
                table.repaint();
                break;

            default:
                break;
        }
    }
}
