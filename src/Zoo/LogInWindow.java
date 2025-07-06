package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LogInWindow extends JFrame implements ActionListener, MouseListener {
    protected JPanel panel1; // GUI form content panel (from IntelliJ GUI Designer)
    protected JFormattedTextField fEmail;
    protected JPasswordField jPassword;
    protected JCheckBox jcbRememberMe;
    protected JButton btnSubmit;
    protected JLabel lblLogIn;
    protected JLabel lblPassword;


    private JPanel fullpanel;

    public LogInWindow() {
        setTitle("Zoo Booking");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // === Background panel ===
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

        // === Login form panel from IntelliJ GUI Designer ===
        panel1.setOpaque(false);
        panel1.setBounds(80, 40, 540, 420); // Adjust position if needed
        fullpanel.add(panel1);

        // === Event listeners ===
        btnSubmit.addActionListener(this);
        fEmail.addActionListener(this);
        jPassword.addActionListener(this);


        // === Layered Pane ===
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 500));
        layeredPane.setLayout(null);

        layeredPane.add(fullpanel, Integer.valueOf(0));
        setContentPane(layeredPane);
        pack();
        setVisible(true);
    }

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
            new Price(); // proceed to Price window
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

    // ===== MouseListener methods (required) =====
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LogInWindow());
    }
}
