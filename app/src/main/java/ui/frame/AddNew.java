package ui.frame;

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

        // ToDo : Link available items with actual data
        String[] list = { "", "Hello 1", "Hello 2", "Hello 3", "Hello 4" };
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(list);
        JComboBox<String> combo = new JComboBox<>(model);
        combo.setEditable(true);
        combo.addActionListener((ActionEvent e) -> {
            System.out.println(e.getActionCommand());
        });

        combo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // ToDo: Auto complete
                String input = combo.getEditor().getItem().toString() + e.getKeyChar();
                combo.setPopupVisible(true);
            }
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
            // ToDo : add new element
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
