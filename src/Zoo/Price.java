package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Price extends JFrame implements ActionListener {
    private JPanel pnlPrice;
    private JLabel operationHourLabel;
    private JPanel pnlprice1;
    private JPanel pnlprice13;
    private JPanel pnlprice11;
    private JPanel pnlprice12;
    private JPanel pnlprice121;
    private JPanel pnlprice122;
    private JPanel pnlprice14;
    private JButton btnContinueOrder;
    //bar
    private JMenuItem miAbout;
    private JMenu menuHelp;
    private JMenuBar menuBar;
    private JMenuItem logout;


    public Price(){
        super.setSize(700,500);
        setLocationRelativeTo(null);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //bar
        menuHelp = new JMenu(" ☰  🌿 Zoo Wonderland");
        menuBar = new JMenuBar();
        miAbout = new JMenuItem("About");
        miAbout.addActionListener(this);
        logout = new JMenuItem("Log out");
        logout.addActionListener(this);

        //construct menu bar
        menuBar.add(menuHelp);
        menuHelp.add(miAbout);
        menuHelp.add(logout);
        super.setJMenuBar(menuBar);

        //add action Listener
        btnContinueOrder.addActionListener(this);


        Container cp = super.getContentPane();
        cp.add(pnlPrice);

        // === Background ===
        JLabel backgroundLabel = new JLabel();
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/Untitled design.png"));
        backgroundLabel.setIcon(new ImageIcon(bgIcon.getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, 700, 500);

        pnlPrice.setOpaque(false);

        // === Layering ===
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 500));

        backgroundLabel.setBounds(0, 0, 700, 500);
        pnlPrice.setBounds(0, 0, 700, 500);

        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(pnlPrice, Integer.valueOf(1));

        setContentPane(layeredPane);
        pack();
        setVisible(true);

    }

//latest user
    private String getLatestUserEmail() {
        File file = new File("latestUser.txt");
        if (!file.exists()) return "Unknown";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String email = reader.readLine();
            return (email != null && !email.isEmpty()) ? email : "Unknown";
        } catch (IOException e) {
            e.printStackTrace();
            return "Unknown";
        }
    }




    public static void main(String[] args){
        Price price = new Price();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnContinueOrder) {
            Checkout cp = new Checkout();
            dispose();
        }
        else if(e.getSource() == logout){
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to log out?" ,
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (choice == JOptionPane.YES_OPTION) {
                new WelcomePage();
                dispose();
            }
        }
        //LATEST
        //bar
        else if(e.getSource()==miAbout)
        {
            String email = getLatestUserEmail();
            JOptionPane.showMessageDialog(this,
                    "Logged in as: " + email,
                    "About",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }



    }


}
