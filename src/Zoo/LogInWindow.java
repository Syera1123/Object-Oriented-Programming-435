package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class LogInWindow extends JFrame implements ActionListener {
    protected JPanel panel1;
    protected JFormattedTextField fEmail;
    protected JPasswordField jPassword;
    protected JCheckBox jcbRememberMe;
    protected JButton btnSubmit;
    protected JLabel lblLogIn;
    protected JLabel lblPassword;
    protected JLabel lblForgotPass;

    public LogInWindow(){
        super.setTitle("Zoo Booking"); // title
        super.setSize(400,300);

        //action listener
        fEmail.addActionListener(this);
        //add designed to Content Pane
        Container cp = super.getContentPane();
        cp.add(panel1);


        super.setVisible(true);
    }

    public static void main(String[] args) {
        new LogInWindow() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        };
    }

}
