package ui.frame;

import event.EventBusUtil;
import event.ProviderViewEvent;
import provider.dao.PriceDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddNew extends JInternalFrame {
    private static final String TITLE = "Add new";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 100;

    public AddNew() {
        init();
    }

    private void init() {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setClosable(true);
        setMaximizable(false);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridBagLayout());

        setVisible(true);

        GridBagConstraints c = new GridBagConstraints();

        PriceDao dao = PriceDao.get();
        String[] arr = dao.getSymbols().toArray(new String[0]);
        JComboBox<String> combo = new JComboBox<>(arr);
        combo.setEditable(true);
        combo.addActionListener((ActionEvent e) -> {
            System.out.println(e.getActionCommand());
        });

        c.gridx = 0; c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        add(combo, c);

        JButton b = new JButton("Add");
        c.gridx = 0; c.gridy = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        b.addActionListener((ActionEvent e) -> {
            ProviderViewEvent ev = new ProviderViewEvent(ProviderViewEvent.ADD_INSTRUMENT);
            ev.setInstrument((String) combo.getSelectedItem());
            EventBusUtil.get().post(ev);

            dispose();
        });
        add(b, c);

        b = new JButton("Cancel");
        c.gridx = 1; c.gridy = 1;
        b.addActionListener((ActionEvent e) -> {
            dispose();
        });
        add(b, c);
    }
}
