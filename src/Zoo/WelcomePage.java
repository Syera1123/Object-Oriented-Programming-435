package Zoo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class WelcomePage extends JFrame implements ActionListener,MouseListener {
    public JPanel pnlMain;
    public JButton btnSignUp;
    public JLabel lblTitle;
    public JLabel lblaccount;
    public JLabel lblLogIn;

    public WelcomePage() {

        super.setTitle("Zoo Booking"); // title
        super.setSize(400,300);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //action Listener
        btnSignUp.addActionListener(this);
        lblLogIn.addMouseListener((MouseListener) this);

        //add designed to Content Pane
        Container cp = super.getContentPane();
        cp.add(pnlMain);

        ImageIcon image = new ImageIcon("Zoo.jfif");
        super.setIconImage(image.getImage());




        super.setVisible(true);

    }

    public static void main(String[] args) {

        new WelcomePage() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==lblLogIn){
                    LogInWindow login = new LogInWindow() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                        }
                    };
                    dispose();
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btnSignUp){
                    new SignUpWindow();
                    dispose();
                }

            }
        };
    }
}
