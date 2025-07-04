package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class SignUpWindow extends JFrame implements ActionListener,MouseListener{
    private JFormattedTextField fName;
    private JFormattedTextField fEmail;
    private JPasswordField pPassword;
    private JPasswordField pCPassword;
    private JCheckBox cbRememberMe;
    private JButton bSubmit;
    private JLabel lblBack;
    private JLabel lblSignUp;
    private JPanel pblSignUp;

    public SignUpWindow(){
        super.setTitle("Sign Up Page"); // title
        super.setSize(400,300);

        lblBack.addMouseListener(this);
        bSubmit.addActionListener(this);
        cbRememberMe.addActionListener(this);

        Container cp = super.getContentPane();
        cp.add(pblSignUp);

        ImageIcon image = new ImageIcon("Zoo.jfif");
        super.setIconImage(image.getImage());

        super.setVisible(true);
    }

    public static void main(String[] args) {
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
