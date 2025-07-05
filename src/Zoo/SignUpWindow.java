package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class SignUpWindow extends JFrame implements ActionListener, MouseListener,FocusListener {
    protected JFormattedTextField fName;
    protected JFormattedTextField fEmail;
    protected JPasswordField pPassword;
    protected JPasswordField pReconfirmPass;
    private JCheckBox cbRememberMe;
    private JButton bSubmit;
    private JPanel pnlSignUp;
    private JLabel lblBack;
    private JLabel lblSignUp;

    public SignUpWindow(){
        super.setTitle("Zoo Booking"); // title
        super.setSize(400,250);



        pPassword.setEchoChar((char)0); // Make text visible initially
        pPassword.setText("Password");

        //action listener
        lblBack.addMouseListener(this);
        bSubmit.addActionListener(this);
        cbRememberMe.addActionListener(this);
        fName.addFocusListener(this);
        fName.addActionListener(this);
        fEmail.addFocusListener(this);
        fEmail.addActionListener(this);
        pPassword.addMouseListener(this);
        pPassword.addActionListener(this);

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
            public void focusGained(FocusEvent e) {
                if(e.getSource()==fName&&fName.getText().equals("Name")){
                    fName.setText("");
                }
                if(e.getSource()==fEmail&&fEmail.getText().equals("Email")){
                    fEmail.setText("");
                }

                if (e.getSource() == pPassword) {
                    // Convert char array to String for comparison
                    String passwordText = new String(pPassword.getPassword());

                    // Check if it's showing the placeholder
                    if (passwordText.equals("Password") && pPassword.getEchoChar() == 0) {
                        pPassword.setText("");  // Clear the placeholder
                        pPassword.setEchoChar('•');  // Enable password masking
                    }
                }
                }

            @Override
            public void focusLost(FocusEvent e) {

                if(e.getSource()==fName&&fName.getText().equals("")){
                    fName.setText("Name");
                }
                if(e.getSource()==fEmail&&fEmail.getText().equals("")){
                    fEmail.setText("Email");
                }

            }

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
