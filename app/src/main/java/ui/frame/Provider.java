package ui.frame;

import com.google.common.eventbus.Subscribe;
import event.ChartViewEvent;
import event.EventBusUtil;
import event.MainViewEvent;
import event.ProviderViewEvent;
import app.TransactionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Provider frame.
 */
public class Provider extends JInternalFrame {
    private JTable table;

    public Provider() {
        init();

        // Register event listener
        EventBusUtil.get().register(this);
    }

    private void init() {
        Container pane = getContentPane();
        pane.setLayout(new GridBagLayout());

        setTitle("Provider");
        setBackground(Color.DARK_GRAY);

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        setVisible(true);

        GridBagConstraints c = new GridBagConstraints();

        JButton b;

        // buttons
        b = new JButton("Load Instruments");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5; c.weighty = 0.1;
        c.gridx = 0; c.gridy = 2;
        b.addActionListener((ActionEvent e) -> {
            EventBusUtil.get().post(new MainViewEvent(MainViewEvent.PROVIDER_ADD_NEW));
        });
        pane.add(b, c);

        // table
        table = createTable();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.9; c.weightx = 1.0;
        c.gridwidth = 3;
        c.gridx = 0; c.gridy = 1;
        pane.add(new JScrollPane(table), c);
    }

    private JTable createTable() {
        String column[] = {"Name", "Current Price", "Volume"};
        String data[][] = {};

        JTable t = new JTable(new DefaultTableModel(column, 0));
        t.setBackground(Color.GRAY);
        t.setForeground(Color.WHITE);
        t.setShowVerticalLines(true);
        t.setShowVerticalLines(true);
        t.setShowHorizontalLines(true);

        t.getSelectionModel().addListSelectionListener((e) -> {
            System.out.println(e.getValueIsAdjusting());
            if (!e.getValueIsAdjusting()) {
                String inst = t.getValueAt(t.getSelectedRow(), 0).toString();
                ChartViewEvent ev = new ChartViewEvent(ChartViewEvent.SHOW_INSTRUMENT);
                ev.setInstrument(inst);
                EventBusUtil.get().post(ev);
            }
        });

        return t;
    }

    /**
     * Event Listener for any event that main view is responsible
     * @param e ProviderViewEvent
     */
    @Subscribe
    public void onMainViewEvent(ProviderViewEvent e) {
        String inst = e.getInstrument();
        DefaultTableModel m = (DefaultTableModel) table.getModel();

        switch (e.getType()) {
            case ProviderViewEvent.ADD_INSTRUMENT:
                boolean found = false;
                for (int i = 0; i < m.getRowCount(); i++) {
                    if (m.getValueAt(i, 0).equals(inst)) {
                        found = true;
                    }
                }
                if (!found) {
                    m.addRow(new Object[] { inst, "N/A", "N/A" } );
                }
                break;

            case ProviderViewEvent.CANDLE_ADDED:
                TransactionManager tm = TransactionManager.get();

                for (int i = 0; i < m.getRowCount(); i++) {
                    if (m.getValueAt(i, 0).equals(inst)) {
                        m.setValueAt(tm.getPrice(), i, 1);
                        m.setValueAt(tm.getVolume(), i, 2);
                        break;
                    }
                }
                break;

            default:
                break;
        }
    }
}
