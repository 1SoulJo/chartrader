package ui.frame;

import app.Credential;
import event.EventBusUtil;
import event.MainViewEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class SignInSignUp extends JInternalFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;

    private JTextField user;
    private JPasswordField pw;

    public SignInSignUp() {
        init();
    }

    private void init() {
        setTitle("Sign In / Sign Up");
        setSize(WIDTH, HEIGHT);
        setClosable(true);
        setMaximizable(false);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridBagLayout());

        setVisible(true);

        Container pane = getContentPane();
        pane.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        GridBagConstraints c = new GridBagConstraints();

        JPanel p = new JPanel();
        JLabel l = new JLabel("Username");
        p.add(l);
        user = new JTextField();
        user.setPreferredSize(new Dimension(100, 30));
        p.add(user);
        pane.add(p);

        p = new JPanel();
        l = new JLabel("Password");
        p.add(l);
        pw = new JPasswordField();
        pw.setPreferredSize(new Dimension(100, 30));
        p.add(pw);
        pane.add(p);

        p = new JPanel();
        JButton b = new JButton("Sign In");
        b.addActionListener((ActionEvent e) -> {
            String username = user.getText();
            StringBuilder pass = new StringBuilder();
            for (char ch : pw.getPassword()) {
                pass.append(ch);
            }
            if (Credential.get().signIn(username, pass.toString())) {
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to sign in.");
            }
        });
        p.add(b);

        b = new JButton("Sign Up");
        b.addActionListener((ActionEvent e) -> {
            String username = user.getText();
            StringBuilder pass = new StringBuilder();
            for (char ch : pw.getPassword()) {
                pass.append(ch);
            }
            if (Credential.get().signUp(username, pass.toString())) {
                JOptionPane.showMessageDialog(this, "Registered.");
            } else {
                JOptionPane.showMessageDialog(this, "Fail to sign up.");
            }
        });
        p.add(b);

        b = new JButton("Cancel");
        b.addActionListener((ActionEvent e) -> {
            if (!Credential.get().isLoggedIn()) {
                System.exit(0);
            } else {
                dispose();
            }
        });
        p.add(b);
        pane.add(p);
    }
}
