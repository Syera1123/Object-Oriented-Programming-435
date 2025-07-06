package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePage extends JFrame implements ActionListener, MouseListener {
    private JButton btnSignUp;
    private JLabel lblTitle;
    private JLabel lblAccount;
    private JLabel lblLogIn;
    private JPanel pnlMain;
    private JPanel welPanel;

    public WelcomePage() {
        setTitle("Zoo Booking");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // === Background Image ===
        JLabel backgroundLabel = new JLabel();
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/Untitled design.png"));
        backgroundLabel.setIcon(new ImageIcon(bgIcon.getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, 700, 500);

        // === Foreground Panel ===
        welPanel = new JPanel(null);
        welPanel.setOpaque(false);
        welPanel.setBounds(0, 0, 700, 500);

        pnlMain = new JPanel(null);
        pnlMain.setOpaque(false);
        pnlMain.setBounds(150, 150, 400, 200);
        welPanel.add(pnlMain);

        // === UI Components ===
        lblTitle = new JLabel("Welcome to Zoo Booking");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(70, 10, 300, 30);
        pnlMain.add(lblTitle);

        btnSignUp = new JButton("Sign Up");
        btnSignUp.setBounds(100, 60, 200, 40);
        pnlMain.add(btnSignUp);

        lblAccount = new JLabel("Already have an account?");
        lblAccount.setBounds(100, 110, 200, 30);
        pnlMain.add(lblAccount);

        lblLogIn = new JLabel("<HTML><U>Log In</U></HTML>");
        lblLogIn.setForeground(Color.BLUE);
        lblLogIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblLogIn.setBounds(150, 140, 100, 30);
        pnlMain.add(lblLogIn);

        // === Layered Pane for background and content ===
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setPreferredSize(new Dimension(700, 500));
        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(welPanel, Integer.valueOf(1));

        setContentPane(layeredPane);

        // === Listeners ===
        btnSignUp.addActionListener(this);
        lblLogIn.addMouseListener(this);

        // === Window Icon ===
        ImageIcon icon = new ImageIcon("Zoo.jfif");
        setIconImage(icon.getImage());

        setVisible(true);
    }

    public static void main(String[] args) {
        new WelcomePage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSignUp) {
            new SignUpWindow();  // ✅ Just call without overriding
            dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == lblLogIn) {
            new LogInWindow();  // ✅ Also call directly
            dispose();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
