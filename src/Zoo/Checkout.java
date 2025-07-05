package Zoo;

import javax.swing.*;
import java.awt.event.*;

public class Checkout extends JFrame implements ActionListener {
    private JPanel pnlCheckout;
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
    private JButton btnCheckout;
    private JLabel lblKid;
    private JLabel lblAdult;
    private JLabel lblOKU;
    private JLabel lblSafariRide;
    private JLabel lblPhotobooth;
    private JLabel lblAnimalFood;
    private JLabel lblBirdShow;

    private ButtonGroup bgWeek, bgSafariRide, bgPhotobooth;

    public Checkout() {
        setTitle("Zoo Ticket System");
        setSize(700, 500);
        setContentPane(pnlCheckout);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // Group radio buttons
        bgWeek = new ButtonGroup();
        bgWeek.add(rbWeekday);
        bgWeek.add(rbWeekend);

        bgSafariRide = new ButtonGroup();
        bgSafariRide.add(rbSafariYes);
        bgSafariRide.add(rbSafariNo);

        bgPhotobooth = new ButtonGroup();
        bgPhotobooth.add(rbPhotoboothYes);
        bgPhotobooth.add(rbPhotoboothNo);

        // Spinner model
        spKid.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
        spAdult.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
        spOKU.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
        spAnimalFood.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
        spBirdShow.setModel(new SpinnerNumberModel(0, 0, 1000, 1));

        // Button Action
        btnCheckout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

        // Validasi opsyen Safari dan Photobooth
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

        // Papar resit guna ReceiveData (pass semua data)
        new ReceiveData(ticketType, noKid, kidPrice, noAdult, adultPrice, noOKU, okuPrice,
                safari, photobooth, noAnimalFood, animalFoodPrice, noBirdShow, birdShowPrice, total);
    }

    public static void main(String[] args) {
        new Checkout();
    }
}
