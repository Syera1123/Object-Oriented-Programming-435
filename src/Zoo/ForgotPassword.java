package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public abstract class ForgotPassword extends JFrame implements ActionListener, FocusListener {
    private JPanel panel1;
    protected JPasswordField password;
    protected JPasswordField reconfirm;
    protected JLabel lblForgotPassword;
    protected JButton submit;
    protected JFormattedTextField email;

    public ForgotPassword() {
        super.setTitle("Sign Up"); // title
        super.setSize(700, 500);

        password.setEchoChar((char) 0); // Make text visible initially
        password.setText("Password");
        password.addFocusListener(this);
        password.addFocusListener(this);

        email.setText("Email");
        email.addFocusListener(this);

        reconfirm.setEchoChar((char) 0);
        reconfirm.setText("Reconfirm Password");
        reconfirm.addActionListener(this);
        reconfirm.addFocusListener(this);

        submit.addActionListener(this);
        //add designed to Content Pane
        Container cp = super.getContentPane();
        cp.add(panel1);

        ImageIcon image = new ImageIcon("Zoo.jfif");
        super.setIconImage(image.getImage());

        super.setVisible(true);
    }

    public static void main(String[] args) {
        new ForgotPassword() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == submit) {
                    // ✅ FIX: use getText() instead of String.valueOf(email)
                    if (isLoginValid(email.getText())) {
                        if (!password.equals(reconfirm)) {
                            new LogInWindow() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                }
                            };
                            dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Email not valid");
                    }
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (e.getSource() == email && email.getText().equals("Email")) {
                    email.setText("");
                }
                if (e.getSource() == password) {
                    if (String.valueOf(password.getPassword()).equals("Password") && password.getEchoChar() == 0) {
                        password.setText("");
                        password.setEchoChar('•');
                    }
                }
                if (e.getSource() == reconfirm) {
                    if (String.valueOf(reconfirm.getPassword()).equals("Reconfirm Password") && reconfirm.getEchoChar() == 0) {
                        reconfirm.setText("");
                        reconfirm.setEchoChar('•');
                    }
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (e.getSource() == email && email.getText().equals("")) {
                    email.setText("Email");
                }
                if (e.getSource() == password && String.valueOf(password.getPassword()).isEmpty()) {
                    password.setText("Password");
                    password.setEchoChar((char) 0);
                }
                if (e.getSource() == reconfirm && String.valueOf(reconfirm.getPassword()).isEmpty()) {
                    reconfirm.setText("Reconfirm Password");
                    reconfirm.setEchoChar((char) 0);
                }
            }

            // ✅ FIXED METHOD HERE
            private boolean isLoginValid(String inputEmail) {
                try {
                    File file = new File("password.txt");
                    if (!file.exists())
                        return false;

                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        if (line.equals("Email: " + inputEmail)) {
                            reader.close();
                            return true;
                        }
                        reader.readLine(); // Skip password
                        reader.readLine(); // Skip separator
                    }

                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("File error: " + e.getMessage());
                }

                return false;
            }
        };
    }
}
