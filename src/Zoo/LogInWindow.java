package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public abstract class LogInWindow extends JFrame implements ActionListener {
    protected JPanel panel1;               // from IntelliJ GUI Designer
    protected JFormattedTextField fEmail;
    protected JPasswordField jPassword;
    protected JCheckBox jcbRememberMe;
    protected JButton btnSubmit;
    protected JLabel lblLogIn;
    protected JLabel lblPassword;
    protected JLabel lblForgotPass;

    private JPanel fullpanel;

    public LogInWindow() {
        super.setTitle("Zoo Booking");
        super.setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // === Listener setup ===
        btnSubmit.addActionListener(this);
        fEmail.addActionListener(this);
        jPassword.addActionListener(this);

        // === Background Image ===
        JLabel backgroundLabel = new JLabel();
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/Untitled design.png")); // Make sure path is correct
        backgroundLabel.setIcon(new ImageIcon(bgIcon.getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, 700, 500);

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

        panel1.setOpaque(false);
        panel1.setBounds(80, 40, 540, 420);
        fullpanel.add(panel1);

// Layered Pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 500));

        layeredPane.add(fullpanel, Integer.valueOf(0)); // now fullpanel has background + form
        setContentPane(layeredPane);

        setContentPane(layeredPane);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        LogInWindow log = new LogInWindow() {
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

                if (isLoginValid(email, password)) {
                    new Price(); // Move to Price window
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
                            String nextLine = reader.readLine(); // Password line
                            return nextLine != null && nextLine.equals("Password: " + password);
                        }
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
                }
                return false;
            }
        };
    }
}
