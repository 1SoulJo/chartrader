package ui.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Provider frame.
 */
public class Provider extends JInternalFrame {
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

        JButton button;

        button = new JButton("Add");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 2;
        button.addActionListener((ActionEvent e) -> {
            new AddNew().setVisible(true);
        });
        pane.add(button, c);

        button = new JButton("Remove");
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.1;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(button, c);

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.9;
        c.weightx = 1.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(new JScrollPane(createTable()), c);
    }

    private JTable createTable() {
        String column[] = {"Name", "Current Price", "Volume"};
        String data[][] = {
                {"S&P 500", "3012.30", "670000"},
                {"Crude Oil", "67.20", "20000"},
                {"Euro FX", "1211.30", "670000"},
                {"Gold", "1213.4", "23321"},
                {"Soybean", "31.12", "1210100"}
        };

        return new JTable(data, column);
    }
}
