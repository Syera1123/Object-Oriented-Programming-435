package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public abstract class LogInWindow extends JFrame implements ActionListener {
    protected JPanel panel1;
    protected JFormattedTextField fEmail;
    protected JPasswordField jPassword;
    protected JCheckBox jcbRememberMe;
    protected JButton btnSubmit;
    protected JLabel lblLogIn;
    protected JLabel lblPassword;
    protected JLabel lblForgotPass;

    public LogInWindow() {
        super.setTitle("Zoo Booking");
        super.setSize(400, 300);

        // Listener
        btnSubmit.addActionListener(this);
        fEmail.addActionListener(this);
        jPassword.addActionListener(this);

        // Add GUI panel
        Container cp = super.getContentPane();
        cp.add(panel1);

        super.setVisible(true);
    }

    public static void main(String[] args) {
        LogInWindow log = new LogInWindow()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = fEmail.getText();
                String password = String.valueOf(jPassword.getPassword());

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter your email");
                    return;
                }

                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter your password");
                    return;
                }

                // ✅ Check email-password match
                if (isLoginValid(email, password)) {
                    new Price();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid email or password");
                }
            }

            private boolean isLoginValid(String email, String password) {
                try {
                    File file = new File("password.txt");
                    if (!file.exists())
                        return false;

                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    boolean emailFound = false;

                    while ((line = reader.readLine()) != null) {
                        if (line.equals("Email: " + email)) {
                            emailFound = true;
                            String nextLine = reader.readLine(); // should be password
                            if (nextLine != null && nextLine.equals("Password: " + password)) {
                                reader.close();
                                return true; // Match found
                            } else {
                                reader.close();
                                return false; // Password mismatch
                            }
                        }
                    }

                    reader.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
                }
                return false;
            }
        };
    }
}