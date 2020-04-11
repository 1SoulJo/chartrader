package ui.frame;

import event.EventBusUtil;
import event.TradeViewEvent;
import provider.entity.Position;
import provider.entity.Transaction;
import provider.dao.TransactionDao;
import app.TransactionManager;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Order popup view
 */
public class Order extends JInternalFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private int type;
    private JTextField name;
    private JTextField quantity;
    private JTextField price;

    public Order(int type) {
        init();
        this.type = type;
    }

    private void init() {
        Container pane = getContentPane();
        setTitle(type == 0 ? "Buy Order" : "Sell Order");
        setSize(WIDTH, HEIGHT);
        setClosable(true);
        setMaximizable(false);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pane.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        setVisible(true);

        TransactionManager tm = TransactionManager.get();

        JPanel p = new JPanel();

        JLabel l = new JLabel("Name");
        p.add(l);
        name = new JTextField(tm.getActiveInstrument());
        name.setEditable(false);
        p.add(name);
        pane.add(p);

        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        l = new JLabel("Quantity");
        p.add(l);
        JPanel p2 = new JPanel();
        JButton b = new JButton("-");
        b.addActionListener((e) -> quantity.setText(String.valueOf(Integer.parseInt(quantity.getText()) - 1)));
        p2.add(b);
        quantity = new JTextField("1");
        quantity.setPreferredSize(new Dimension(100, 30));
        p2.add(quantity);
        b = new JButton("+");
        b.addActionListener((e) -> quantity.setText(String.valueOf(Integer.parseInt(quantity.getText()) + 1)));
        p2.add(b);
        p.add(p2);
        pane.add(p);

        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        l = new JLabel("Price");
        p.add(l);
        p2 = new JPanel();
        b = new JButton("-");
        b.addActionListener(
                (e) -> price.setText(String.valueOf(Double.parseDouble(price.getText()) - 0.1)));
        p2.add(b);
        price = new JTextField("" + tm.getPrice());
        price.setPreferredSize(new Dimension(100, 30));
        p2.add(price);
        b = new JButton("+");
        b.addActionListener((e) -> price.setText(String.valueOf(Double.parseDouble(price.getText()) + 0.1)));
        p2.add(b);
        p.add(p2);
        pane.add(p);

        p = new JPanel();
        b = new JButton("Place Order");
        b.addActionListener((e) -> {
            Transaction t = new Transaction();
            t.setUserId("Hansol");
            t.setAccountId(1);
            t.setInstrumentId(name.getText());
            SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            t.setDate(dayTime.format(new Date(System.currentTimeMillis())));
            t.setPrice(Float.parseFloat(price.getText()));
            t.setQuantity(Integer.parseInt(quantity.getText()));
            t.setType(type);
            t.setCompleted(false);

            Position position = tm.getPositions().get(t.getInstrumentId());
            if (position != null && t.getType() == Transaction.TYPE_SELL) {
                if (position.getQuantity() < t.getQuantity()) {
                    JOptionPane.showMessageDialog(this, "Quantity exceeded current position quantity.");
                    return;
                }
            }

            // Save into trade log
            TransactionDao.getInstance().save(t);

            // Post event
            tm.addTransaction(t);
            EventBusUtil.get().post(new TradeViewEvent(TradeViewEvent.UPDATE_TABLE));

            Order.this.dispose();
        });
        p.add(b);
        pane.add(p);

        pack();
    }
}
