package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class WelcomePage extends JFrame implements ActionListener, MouseListener {
    public JButton btnSignUp;
    public JLabel lblTitle;
    public JLabel lblaccount;
    public JLabel lblLogIn;
    public JPanel pnlMain; // guna panel utama dari .form (pastikan binding betul)
    public JPanel welpanel;

    public WelcomePage() {
        super.setTitle("Zoo Booking");
        super.setSize(700, 500);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // === Background Image ===
        JLabel backgroundLabel = new JLabel();
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/Untitled design.png"));
        backgroundLabel.setIcon(new ImageIcon(bgIcon.getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, 700, 500);

        welpanel.setOpaque(false);
        welpanel.setBounds(0, 0, 700, 500);

        // === Layering ===
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 500));
        layeredPane.setLayout(null);

        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(welpanel, Integer.valueOf(1));

        setContentPane(layeredPane);

        // Action Listeners
        btnSignUp.addActionListener(this);
        lblLogIn.addMouseListener(this);

        // Set icon
        ImageIcon image = new ImageIcon("Zoo.jfif");
        super.setIconImage(image.getImage());

        pack();
        super.setVisible(true);
    }

    public static void main(String[] args) {
        new WelcomePage() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == lblLogIn) {
                    LogInWindow login = new LogInWindow() {
                        @Override
                        public void mouseClicked(MouseEvent e) {}

                        @Override
                        public void mousePressed(MouseEvent e) {}

                        @Override
                        public void mouseReleased(MouseEvent e) {}

                        @Override
                        public void mouseEntered(MouseEvent e) {}

                        @Override
                        public void mouseExited(MouseEvent e) {}

                        @Override
                        public void actionPerformed(ActionEvent e) {}
                    };
                    dispose();
                }
            }

            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnSignUp) {
                    new SignUpWindow() {
                        @Override public void focusGained(FocusEvent e) {}
                        @Override public void focusLost(FocusEvent e) {}
                        @Override public void mouseClicked(MouseEvent e) {}
                        @Override public void mousePressed(MouseEvent e) {}
                        @Override public void mouseReleased(MouseEvent e) {}
                        @Override public void mouseEntered(MouseEvent e) {}
                        @Override public void mouseExited(MouseEvent e) {}
                        @Override public void actionPerformed(ActionEvent e) {}
                    };
                    dispose();
                }
            }
        };
    }
}
