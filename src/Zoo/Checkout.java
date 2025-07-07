package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Checkout extends JFrame implements ActionListener {
    private JPanel pnlCheckout;
    private JButton btnBack;
    private JButton btnCheckout;
    private JRadioButton rbWeekday;
    private JRadioButton rbWeekend;
    private JSpinner spKid;
    private JSpinner spAdult;
    private JSpinner spOKU;
    private JSpinner spAnimalFood;
    private JSpinner spBirdShow;
    private JRadioButton rbSafariYes;
    private JRadioButton rbSafariNo;
    private JRadioButton rbPhotoboothYes;
    private JRadioButton rbPhotoboothNo;
    private JLabel lblKid;
    private JLabel lblAdult;
    private JLabel lblOKU;
    private JLabel lblSafariRide;
    private JLabel lblPhotobooth;
    private JLabel lblAnimalFood;
    private JLabel lblBirdShow;
    private Checkout checkoutPage;
    //bar
    private JMenuItem miAbout;
    private JMenu menuHelp;
    private JMenuBar menuBar;
    private JMenuItem logout;



    private ButtonGroup bgWeek, bgSafariRide, bgPhotobooth;

    public Checkout() {
        setTitle("Zoo Ticket System");
        setSize(700, 500);
        setContentPane(pnlCheckout);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        JLabel backgroundLabel = new JLabel();
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/World.png"));
        backgroundLabel.setIcon(new ImageIcon(bgIcon.getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, 700, 500);

        pnlCheckout.setOpaque(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 500));

        backgroundLabel.setBounds(0, 0, 700, 500);
        pnlCheckout.setBounds(0, 0, 700, 500);

        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(pnlCheckout, Integer.valueOf(1));

        setContentPane(layeredPane);
        pack();

        setVisible(true);

        bgWeek = new ButtonGroup();
        bgWeek.add(rbWeekday);
        bgWeek.add(rbWeekend);

        bgSafariRide = new ButtonGroup();
        bgSafariRide.add(rbSafariYes);
        bgSafariRide.add(rbSafariNo);

        bgPhotobooth = new ButtonGroup();
        bgPhotobooth.add(rbPhotoboothYes);
        bgPhotobooth.add(rbPhotoboothNo);

        spKid.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
        spAdult.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
        spOKU.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
        spAnimalFood.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
        spBirdShow.setModel(new SpinnerNumberModel(0, 0, 1000, 1));

        btnCheckout.addActionListener(this);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Price();
                dispose();

            }
        });
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

    public Checkout(String ticketType, int kid, int kidPrice, int adult, int adultPrice, int oku, int okuPrice,
                    boolean safari, boolean photobooth, int animalFood, int animalFoodPrice,
                    int birdShow, int birdShowPrice, int total) {
        this();

        if ("Weekday".equals(ticketType)) {
            rbWeekday.setSelected(true);
        } else if ("Weekend".equals(ticketType)) {
            rbWeekend.setSelected(true);
        }

        spKid.setValue(kid);
        spAdult.setValue(adult);
        spOKU.setValue(oku);
        spAnimalFood.setValue(animalFood);
        spBirdShow.setValue(birdShow);

        if (safari) {
            rbSafariYes.setSelected(true);
        } else {
            rbSafariNo.setSelected(true);
        }

        if (photobooth) {
            rbPhotoboothYes.setSelected(true);
        } else {
            rbPhotoboothNo.setSelected(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //LATEST
        //bar
        if(e.getSource()==miAbout)
        {
            String email = getLatestUserEmail();
            JOptionPane.showMessageDialog(this,
                    "Logged in as: " + email,
                    "About",
                    JOptionPane.INFORMATION_MESSAGE
            );
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

        else if(e.getSource()==btnCheckout) {

            int noKid = (Integer) spKid.getValue();
            int noAdult = (Integer) spAdult.getValue();
            int noOKU = (Integer) spOKU.getValue();
            int noAnimalFood = (Integer) spAnimalFood.getValue();
            int noBirdShow = (Integer) spBirdShow.getValue();

            boolean safari = rbSafariYes.isSelected();
            boolean photobooth = rbPhotoboothYes.isSelected();

            String ticketType;
            int kidPrice, adultPrice, okuPrice;

            if (rbWeekday.isSelected()) {
                ticketType = "Weekday";
                kidPrice = 10;
                adultPrice = 12;
                okuPrice = 7;
            } else if (rbWeekend.isSelected()) {
                ticketType = "Weekend";
                kidPrice = 13;
                adultPrice = 15;
                okuPrice = 9;
            } else {
                JOptionPane.showMessageDialog(this, "Please select day (Weekday/Weekend).");
                return;
            }

            if (!rbSafariYes.isSelected() && !rbSafariNo.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please select Safari Ride option.");
                return;
            }
            if (!rbPhotoboothYes.isSelected() && !rbPhotoboothNo.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please select Photobooth option.");
                return;
            }

            int safariPrice = safari ? 30 : 0;
            int photoPrice = photobooth ? 30 : 0;
            int animalFoodPrice = 10;
            int birdShowPrice = 15;

            int total = (noKid * kidPrice) + (noAdult * adultPrice) + (noOKU * okuPrice)
                    + safariPrice + photoPrice + (noAnimalFood * animalFoodPrice) + (noBirdShow * birdShowPrice);

            dispose(); //close current window

            new ReceiveData(ticketType, noKid, kidPrice, noAdult, adultPrice, noOKU, okuPrice,
                    safari, photobooth, noAnimalFood, animalFoodPrice, noBirdShow, birdShowPrice, total);
        }

            checkoutPage.setVisible(true);
            this.setVisible(false); // Just hide, don't dispose


    }

    public static void main(String[] args) {
        new Checkout();
    }
}
