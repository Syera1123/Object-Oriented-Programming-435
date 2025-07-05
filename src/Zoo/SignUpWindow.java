package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public abstract class SignUpWindow extends JFrame implements ActionListener, MouseListener,FocusListener {
    protected JFormattedTextField fName;
    protected JFormattedTextField fEmail;
    protected JPasswordField pPassword;
    protected JPasswordField pReconfirmPass;
    protected JButton bSubmit;
    protected JPanel pnlSignUp;
    private JLabel lblSignUp;

    public SignUpWindow(){
        super.setTitle("Sign Up"); // title
        super.setSize(700,500);
        // === Background Image ===
        setContentPane(new JLabel(new ImageIcon(
                new ImageIcon(getClass().getResource("/Untitled design.png"))
                        .getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH))));
        setLayout(null); // Manual layout

        pnlSignUp.setOpaque(false);
        pnlSignUp.setBounds(0, 0, 700, 500);
        add(pnlSignUp); // Add sign-up panel on top of background


        pPassword.setEchoChar((char)0); // Make text visible initially
        pPassword.setText("Password");

        pReconfirmPass.setEchoChar((char)0);
        pReconfirmPass.setText("Reconfirm Password");

        //action listener
        bSubmit.addActionListener(this);
        fName.addFocusListener(this);
        fName.addActionListener(this);
        fEmail.addFocusListener(this);
        fEmail.addActionListener(this);
        pPassword.addFocusListener(this);
        pPassword.addActionListener(this);
        pReconfirmPass.addFocusListener(this);
        pReconfirmPass.addActionListener(this);


        ImageIcon image = new ImageIcon("Zoo.jfif");
        super.setIconImage(image.getImage());

        super.setVisible(true);
    }

    public static void main(String[] args){
        new SignUpWindow() {
            @Override
            public void focusGained(FocusEvent e) {
                if (e.getSource() == fName && fName.getText().equals("Name")) {
                    fName.setText("");
                }
                if (e.getSource() == fEmail && fEmail.getText().equals("Email")) {
                    fEmail.setText("");
                }

                if (e.getSource() == pPassword) {
                    if (String.valueOf(pPassword.getPassword()).equals("Password") && pPassword.getEchoChar() == 0) {
                        pPassword.setText("");
                        pPassword.setEchoChar('•');
                    }
                }

                if (e.getSource() == pReconfirmPass) {
                    if (String.valueOf(pReconfirmPass.getPassword()).equals("Reconfirm Password") && pReconfirmPass.getEchoChar() == 0) {
                        pReconfirmPass.setText("");
                        pReconfirmPass.setEchoChar('•');
                    }
                }


            }

            @Override
            public void focusLost(FocusEvent e) {

                if (e.getSource() == fName && fName.getText().equals("")) {
                    fName.setText("Name");
                }
                if (e.getSource() == fEmail && fEmail.getText().equals("")) {
                    fEmail.setText("Email");
                }
                if (e.getSource() == pPassword && String.valueOf(pPassword.getPassword()).isEmpty()) {
                    pPassword.setText("Password");
                    pPassword.setEchoChar((char) 0);
                }
                if (e.getSource() == pPassword && String.valueOf(pPassword.getPassword()).isEmpty()) {
                    pPassword.setText("Password");
                    pPassword.setEchoChar((char) 0);
                }
                if (e.getSource() == pReconfirmPass && String.valueOf(pReconfirmPass.getPassword()).isEmpty()) {
                    pReconfirmPass.setText("Reconfirm Password");
                    pReconfirmPass.setEchoChar((char) 0);
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
                if (e.getSource() == bSubmit) {
                    //get text from password field n reconfirm field
                    String password = String.valueOf(pPassword.getPassword());
                    String confirmpass = String.valueOf(pReconfirmPass.getPassword());


                    if (fName.getText().equals("Name")) {
                        JOptionPane.showMessageDialog(this, "Please enter your name");
                        return;
                    } else if (fEmail.getText().equals("Email")) {
                        JOptionPane.showMessageDialog(this, "Please enter your email");
                        return;
                    } else if (!fEmail.getText().matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid Gmail address (e.g., name@gmail.com)");
                        return;
                    } else if (password.equals("Password")) {
                        JOptionPane.showMessageDialog(this, "Please enter your password");
                        return;
                    } else if (confirmpass.equals("Reconfirm Password")) {
                        JOptionPane.showMessageDialog(this, "Please enter your reconfirm password");
                        return;
                    }

                    // Check if both passwords match
                    else if (!password.equals(confirmpass)) {
                        JOptionPane.showMessageDialog(this, "Passwords do not match. Please retype.");
                        return;
                    }
                    else if(isEmailAlreadyUsed(fEmail.getText())){
                        JOptionPane.showMessageDialog(this, "This email is already registered.");
                        return;
                    }


                    if (e.getSource() == bSubmit) {
                        // All good
                       new LogInWindow() {
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
                        // Proceed to next screen or save data
                        try {
                            FileWriter fw = new FileWriter("password.txt", true); // true = append
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write("Email: " + fEmail.getText());
                            bw.newLine();
                            bw.write("Password: " + String.valueOf(pPassword.getPassword()));
                            bw.newLine();
                            bw.write("------------------------");
                            bw.newLine();
                            bw.close();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(this, "Error saving to file: " + ex.getMessage());
                        }
                    }


                }


            }

            private boolean isEmailAlreadyUsed(String email) {
                try {
                    File file = new File("password.txt");
                    if (!file.exists())
                        return false;
                    Scanner sc = new Scanner(file);
                    while ((sc.hasNextLine())) {
                        if (sc.nextLine().equals("Email: " + email)) {
                            sc.close();
                            return true;
                        }
                    }
                    sc.close();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Error checking email: " + ex.getMessage());
                }
                return false;
            }


            };


        };
    }

