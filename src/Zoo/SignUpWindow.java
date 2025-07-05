package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public abstract class SignUpWindow extends JFrame implements ActionListener, FocusListener, MouseListener {
    protected JPanel pnlSignUp;
    protected JFormattedTextField fName;
    protected JFormattedTextField fEmail;
    protected JPasswordField pPassword;
    protected JPasswordField pReconfirmPass;
    protected JButton bSubmit;
    private JLabel lblBack;
    private JLabel lblSignUp;

    private JPanel backgroundPanel;

    public SignUpWindow() {
        setTitle("Zoo Sign Up");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // === Background Panel ===
        backgroundPanel = new JPanel(null) {
            Image bg = new ImageIcon(getClass().getResource("/Untitled design.png")).getImage();
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setPreferredSize(new Dimension(700, 500));
        pnlSignUp.setOpaque(false);
        pnlSignUp.setBounds(80, 40, 540, 420);
        backgroundPanel.add(pnlSignUp);

        setContentPane(backgroundPanel);

        // === Field Placeholder ===
        pPassword.setEchoChar((char) 0);
        pPassword.setText("Password");

        pReconfirmPass.setEchoChar((char) 0);
        pReconfirmPass.setText("Reconfirm Password");

        fName.addFocusListener(this);
        fEmail.addFocusListener(this);
        pPassword.addFocusListener(this);
        pReconfirmPass.addFocusListener(this);

        bSubmit.addActionListener(this);
        lblBack.addMouseListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = fName.getText();
        String email = fEmail.getText();
        String pass = String.valueOf(pPassword.getPassword());
        String confirm = String.valueOf(pReconfirmPass.getPassword());

        if (name.isEmpty() || name.equals("Name")) {
            JOptionPane.showMessageDialog(this, "Please enter your name");
            return;
        } else if (email.isEmpty() || email.equals("Email")) {
            JOptionPane.showMessageDialog(this, "Please enter your email");
            return;
        } else if (!email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Gmail address");
            return;
        } else if (pass.equals("Password")) {
            JOptionPane.showMessageDialog(this, "Please enter your password");
            return;
        } else if (confirm.equals("Reconfirm Password")) {
            JOptionPane.showMessageDialog(this, "Please enter your reconfirm password");
            return;
        } else if (!pass.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match");
            return;
        } else if (isEmailAlreadyUsed(email)) {
            JOptionPane.showMessageDialog(this, "This email is already registered");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("password.txt", true))) {
            bw.write("Email: " + email);
            bw.newLine();
            bw.write("Password: " + pass);
            bw.newLine();
            bw.write("------------------------");
            bw.newLine();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving: " + ex.getMessage());
        }

        // Open LogInWindow after signup
        new LogInWindow() {
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
                    new Price();
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
                    JOptionPane.showMessageDialog(this, "Error reading file");
                }
                return false;
            }
        };
        dispose();
    }

    private boolean isEmailAlreadyUsed(String email) {
        File file = new File("password.txt");
        if (!file.exists()) return false;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                if (sc.nextLine().equals("Email: " + email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file");
        }
        return false;
    }

    // === Focus Handling ===
    @Override public void focusGained(FocusEvent e) {
        if (e.getSource() == fName && fName.getText().equals("Name")) fName.setText("");
        if (e.getSource() == fEmail && fEmail.getText().equals("Email")) fEmail.setText("");
        if (e.getSource() == pPassword && String.valueOf(pPassword.getPassword()).equals("Password")) {
            pPassword.setText(""); pPassword.setEchoChar('•');
        }
        if (e.getSource() == pReconfirmPass && String.valueOf(pReconfirmPass.getPassword()).equals("Reconfirm Password")) {
            pReconfirmPass.setText(""); pReconfirmPass.setEchoChar('•');
        }
    }
    @Override public void focusLost(FocusEvent e) {
        if (e.getSource() == fName && fName.getText().isEmpty()) fName.setText("Name");
        if (e.getSource() == fEmail && fEmail.getText().isEmpty()) fEmail.setText("Email");
        if (e.getSource() == pPassword && String.valueOf(pPassword.getPassword()).isEmpty()) {
            pPassword.setText("Password"); pPassword.setEchoChar((char) 0);
        }
        if (e.getSource() == pReconfirmPass && String.valueOf(pReconfirmPass.getPassword()).isEmpty()) {
            pReconfirmPass.setText("Reconfirm Password"); pReconfirmPass.setEchoChar((char) 0);
        }
    }

    // === MouseListener ===
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        new SignUpWindow() { };
    }
}
