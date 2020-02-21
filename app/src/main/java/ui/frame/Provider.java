package ui.frame;

import event.EventBusUtil;
import event.ProviderEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Provider frame.
 */
public class Provider extends JInternalFrame {
    private JTable table;

    public Provider() {
        init();
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
        b = new JButton("Add");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5; c.weighty = 0.1;
        c.gridx = 0; c.gridy = 2;
        b.addActionListener((ActionEvent e) -> {
            EventBusUtil.get().post(new ProviderEvent(ProviderEvent.SHOW_ADD_NEW));
        });
        pane.add(b, c);

        b = new JButton("Remove");
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.1;
        c.gridx = 1; c.gridy = 2;
        b.addActionListener((ActionEvent e) -> {
            int selected = table.getSelectedRow();
            if (selected < 0) {
                return;
            }

            String selectedItem = (String) table.getModel().getValueAt(selected, 0);

            String[] options = { "Cancel", "Ok" };
            JOptionPane.showOptionDialog(this,
                    "Removing " + selectedItem,
                    "Remove item",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
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
        // ToDo : Link data from DB
        String column[] = {"Name", "Current Price", "Volume"};
        String data[][] = {
                {"S&P 500", "3012.30", "670000"},
                {"Crude Oil", "67.20", "20000"},
                {"Euro FX", "1211.30", "670000"},
                {"Gold", "1213.4", "23321"},
                {"Soybean", "31.12", "1210100"}
        };

        JTable t = new JTable(data, column);
        t.setBackground(Color.GRAY);
        t.setForeground(Color.WHITE);
        t.setShowVerticalLines(true);
        t.setShowVerticalLines(true);
        t.setShowHorizontalLines(true);

        return t;
    }
}
