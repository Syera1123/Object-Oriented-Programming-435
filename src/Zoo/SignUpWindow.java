package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class SignUpWindow extends JFrame implements ActionListener, FocusListener, MouseListener {
    private JPanel pnlSignUp;
    private JFormattedTextField fName;
    private JFormattedTextField fEmail;
    private JPasswordField pPassword;
    private JPasswordField pReconfirmPass;
    private JButton bSubmit;
    private JLabel lblTitle; // ✅ Title added


    public SignUpWindow() {
        setTitle("Zoo Sign Up");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // === Background Panel ===
        JPanel backgroundPanel = new JPanel(null) {
            Image bg = new ImageIcon(getClass().getResource("/Untitled design.png")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(backgroundPanel);

        // === SignUp Panel ===
        pnlSignUp = new JPanel(null);
        pnlSignUp.setOpaque(false);
        pnlSignUp.setBounds(80, 40, 540, 400);
        backgroundPanel.add(pnlSignUp);

        // === Components ===
        lblTitle = new JLabel("Sign Up"); // ✅ Title
        lblTitle.setFont(new Font("Juice ITC", Font.BOLD, 26));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(100, 0, 300, 40);

        fName = new JFormattedTextField("Name");
        fEmail = new JFormattedTextField("Email");
        pPassword = new JPasswordField("Password");
        pReconfirmPass = new JPasswordField("Reconfirm Password");
        bSubmit = new JButton("Submit");

        // === Layout ===
        fName.setBounds(100, 50, 300, 30);
        fEmail.setBounds(100, 90, 300, 30);
        pPassword.setBounds(100, 130, 300, 30);
        pReconfirmPass.setBounds(100, 170, 300, 30);
        bSubmit.setBounds(100, 220, 300, 40);

        // === Field styles ===
        pPassword.setEchoChar((char) 0);
        pReconfirmPass.setEchoChar((char) 0);

        // === Add to Panel ===
        pnlSignUp.add(lblTitle); // ✅ add title
        pnlSignUp.add(fName);
        pnlSignUp.add(fEmail);
        pnlSignUp.add(pPassword);
        pnlSignUp.add(pReconfirmPass);
        pnlSignUp.add(bSubmit);

        // === Listeners ===
        fName.addFocusListener(this);
        fEmail.addFocusListener(this);
        pPassword.addFocusListener(this);
        pReconfirmPass.addFocusListener(this);
        bSubmit.addActionListener(this);

        setVisible(true);
    }

    // === Action on Submit ===
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = fName.getText();
        String email = fEmail.getText();
        String pass = String.valueOf(pPassword.getPassword());
        String confirm = String.valueOf(pReconfirmPass.getPassword());

        if (name.isEmpty() || name.equals("Name")) {
            JOptionPane.showMessageDialog(this, "Please enter your name");
        } else if (email.isEmpty() || email.equals("Email")) {
            JOptionPane.showMessageDialog(this, "Please enter your email");
        } else if (!email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Gmail address");
        } else if (pass.equals("Password")) {
            JOptionPane.showMessageDialog(this, "Please enter your password");
        } else if (confirm.equals("Reconfirm Password")) {
            JOptionPane.showMessageDialog(this, "Please confirm your password");
        } else if (!pass.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match");
        } else if (isEmailAlreadyUsed(email)) {
            JOptionPane.showMessageDialog(this, "This email is already registered");
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("password.txt", true))) {
                bw.write("Email: " + email);
                bw.newLine();
                bw.write("Password: " + pass);
                bw.newLine();
                bw.write("------------------------");
                bw.newLine();
                JOptionPane.showMessageDialog(this, "Account created! Redirecting to login.");
                new LogInWindow();
                dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving to file: " + ex.getMessage());
            }
        }
    }

    private boolean isEmailAlreadyUsed(String email) {
        File file = new File("password.txt");
        if (!file.exists()) return false;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                if (sc.nextLine().equals("Email: " + email)) return true;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file");
        }
        return false;
    }

    // === Placeholder Behavior ===
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

    // === Back Button Placeholder (optional) ===
    @Override public void mouseClicked(MouseEvent e) {}

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}

