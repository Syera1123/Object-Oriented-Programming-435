package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LogInWindow extends JFrame implements ActionListener, MouseListener {
    private JPanel panel1;
    private JFormattedTextField fEmail;
    private JPasswordField jPassword;
    private JCheckBox jcbRememberMe;
    private JButton btnSubmit;
    private JLabel lblTitle; // ✅ Log In phrase
    private JPanel fullpanel;

    public LogInWindow() {
        setTitle("Zoo Booking - Log In");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // === Background Panel ===
        fullpanel = new JPanel(null) {
            private final Image bgImage = new ImageIcon(getClass().getResource("/Untitled design.png")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        fullpanel.setOpaque(false);
        fullpanel.setBounds(0, 0, 700, 500);
        setContentPane(fullpanel);

        // === Login Form Panel ===
        panel1 = new JPanel(null);
        panel1.setOpaque(false);
        panel1.setBounds(100, 60, 500, 300);

        // ✅ Title Label
        lblTitle = new JLabel("Log In");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(100, 0, 300, 40);

        fEmail = new JFormattedTextField("Email");
        fEmail.setBounds(100, 50, 300, 30);

        jPassword = new JPasswordField("Password");
        jPassword.setBounds(100, 90, 300, 30);
        jPassword.setEchoChar((char) 0);

        jcbRememberMe = new JCheckBox("Remember Me");
        jcbRememberMe.setBounds(100, 130, 150, 20);
        jcbRememberMe.setOpaque(false);

        btnSubmit = new JButton("Log In");
        btnSubmit.setBounds(100, 170, 300, 40);



        // === Add Components ===
        panel1.add(lblTitle); // ✅ add title
        panel1.add(fEmail);
        panel1.add(jPassword);
        panel1.add(jcbRememberMe);
        panel1.add(btnSubmit);
        fullpanel.add(panel1);

        // === Listeners ===
        btnSubmit.addActionListener(this);

        fEmail.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                if (fEmail.getText().equals("Email")) fEmail.setText("");
            }
            @Override public void focusLost(FocusEvent e) {
                if (fEmail.getText().isEmpty()) fEmail.setText("Email");
            }
        });

        jPassword.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                if (String.valueOf(jPassword.getPassword()).equals("Password")) {
                    jPassword.setText("");
                    jPassword.setEchoChar('•');
                }
            }
            @Override public void focusLost(FocusEvent e) {
                if (String.valueOf(jPassword.getPassword()).isEmpty()) {
                    jPassword.setText("Password");
                    jPassword.setEchoChar((char) 0);
                }
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = fEmail.getText();
        String password = String.valueOf(jPassword.getPassword());

        if (email.isEmpty() || email.equals("Email")) {
            JOptionPane.showMessageDialog(this, "Please enter your email");
            return;
        }

        if (password.isEmpty() || password.equals("Password")) {
            JOptionPane.showMessageDialog(this, "Please enter your password");
            return;
        }

        if (isLoginValid(email, password)) {
            new Price(); // your next window
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password");
        }
    }

    private boolean isLoginValid(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("password.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("Email: " + email)) {
                    String nextLine = reader.readLine();
                    return nextLine != null && nextLine.equals("Password: " + password);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
        }
        return false;
    }

    @Override public void mouseClicked(MouseEvent e) {
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LogInWindow::new);
    }
}
