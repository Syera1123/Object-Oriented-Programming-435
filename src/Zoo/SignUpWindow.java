package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class SignUpWindow extends JFrame implements ActionListener, MouseListener {
    private JFormattedTextField fName;
    private JFormattedTextField fEmail;
    private JPasswordField pPassword;
    private JPasswordField pReconfirmPass;
    private JCheckBox cbRememberMe;
    private JButton bSubmit;
    private JPanel pnlSignUp;
    private JLabel lblBack;
    private JLabel lblSignUp;

    public SignUpWindow(){
        super.setTitle("Zoo Booking"); // title
        super.setSize(400,400);

        //action listener
        lblBack.addMouseListener(this);
        bSubmit.addActionListener(this);
        cbRememberMe.addActionListener(this);


        //add designed to Content Pane
        Container cp = super.getContentPane();
        cp.add(pnlSignUp);

        ImageIcon image = new ImageIcon("Zoo.jfif");
        super.setIconImage(image.getImage());

        super.setVisible(true);
    }

    public static void main(String[] args){
        new SignUpWindow() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }
}
