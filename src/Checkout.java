import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Checkout extends JFrame {
    private JRadioButton rbWeekday;
    private JRadioButton rbWeekend;
    private JSpinner spKid;
    private JSpinner spAdult;
    private JSpinner spOKU;
    private JRadioButton rbSafariYes;
    private JRadioButton rbSafariNo;
    private JRadioButton rbPhotoboothYes;
    private JRadioButton rbPhotoboothNo;
    private JSpinner spBirdShow;
    private JButton btnCheckout;
    private JLabel lblKid;
    private JLabel lblAdult;
    private JLabel lblOKU;
    private JLabel lblSafariRide;
    private JLabel lblPhotobooth;
    private JLabel lblAnimalFood;
    private JLabel lblBirdShow;
    private JPanel pnlCheckout;
    private JSpinner spAnimalFood;

    private ButtonGroup bgWeek;
    private ButtonGroup bgSafariRide;
    private ButtonGroup bgPhotobooth;

    public Checkout() {
        setTitle("Zoo Ticketing Checkout");
        setSize(700, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Button groups
        bgWeek = new ButtonGroup();
        bgWeek.add(rbWeekday);
        bgWeek.add(rbWeekend);
        bgSafariRide = new ButtonGroup();
        bgSafariRide.add(rbSafariYes);
        bgSafariRide.add(rbSafariNo);
        bgPhotobooth = new ButtonGroup();
        bgPhotobooth.add(rbPhotoboothYes);
        bgPhotobooth.add(rbPhotoboothNo);

        // Spinner setup
        spKid.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
        spAdult.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
        spOKU.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
        spAnimalFood.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
        spBirdShow.setModel(new SpinnerNumberModel(0, 0, 1000, 1));

        // Button listener
        btnCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckout();
            }
        });

        setContentPane(pnlCheckout);
        setVisible(true);
    }

    private void handleCheckout() {
        int kid = (Integer) spKid.getValue();
        int adult = (Integer) spAdult.getValue();
        int oku = (Integer) spOKU.getValue();
        int animalFood = (Integer) spAnimalFood.getValue();
        int birdShow = (Integer) spBirdShow.getValue();

        boolean isWeekday = rbWeekday.isSelected();
        boolean safari = rbSafariYes.isSelected();
        boolean photobooth = rbPhotoboothYes.isSelected();

        // Prices
        int kidPrice = isWeekday ? 10 : 12;
        int adultPrice = isWeekday ? 20 : 25;
        int okuPrice = 5;
        int safariPrice = safari ? 30 : 0;
        int photoboothPrice = photobooth ? 30 : 0;
        int animalFoodPrice = 10;
        int birdShowPrice = 15;

        // Total calculation
        int total = (kid * kidPrice) + (adult * adultPrice) + (oku * okuPrice)
                + safariPrice + photoboothPrice
                + (animalFood * animalFoodPrice) + (birdShow * birdShowPrice);

        // Send to new window
        new ReceiveData(
                isWeekday ? "Weekday" : "Weekend",
                kid, kidPrice,
                adult, adultPrice,
                oku, okuPrice,
                safari,
                photobooth,
                animalFood, animalFoodPrice,
                birdShow, birdShowPrice,
                total
        );
    }

    public static void main(String[] args) {
        new Checkout();
    }
}
