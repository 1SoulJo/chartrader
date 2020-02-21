package ui.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Order extends JInternalFrame {
    private static final String TITLE = "Place Order";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;

    private JTextField quantity;
    private JTextField price;

    public Order() {
        init();
    }

    private void init() {
        Container pane = getContentPane();
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setClosable(true);
        setMaximizable(false);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pane.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        setVisible(true);

        JPanel p = new JPanel();

        JLabel l = new JLabel("Name");
        p.add(l);
        JTextField t = new JTextField("SP500");
        p.add(t);
        pane.add(p);

        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        l = new JLabel("Quantity");
        p.add(l);
        JPanel p2 = new JPanel();
        JButton b = new JButton("-");
        b.addActionListener((ActionEvent e) -> {
            quantity.setText(String.valueOf(Integer.parseInt(quantity.getText()) - 1));
        });
        p2.add(b);
        quantity = new JTextField("1");
        p2.add(quantity);
        b = new JButton("+");
        b.addActionListener((ActionEvent e) -> {
            quantity.setText(String.valueOf(Integer.parseInt(quantity.getText()) + 1));
        });
        p2.add(b);
        p.add(p2);
        pane.add(p);

        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        l = new JLabel("Price");
        p.add(l);
        p2 = new JPanel();
        b = new JButton("-");
        b.addActionListener((ActionEvent e) -> {
            price.setText(String.valueOf(Double.parseDouble(price.getText()) - 0.25));
        });
        p2.add(b);
        price = new JTextField("1513.25");
        p2.add(price);
        b = new JButton("+");
        b.addActionListener((ActionEvent e) -> {
            price.setText(String.valueOf(Double.parseDouble(price.getText()) + 0.25));
        });
        p2.add(b);
        p.add(p2);
        pane.add(p);

        p = new JPanel();
        b = new JButton("Place Order");
        p.add(b);
        pane.add(p);

        pack();
    }
}
