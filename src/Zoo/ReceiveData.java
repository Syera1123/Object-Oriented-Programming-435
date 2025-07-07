package Zoo;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class ExtraActivity {
    protected String name;
    protected int price;

    public ExtraActivity(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public abstract String getDescription();

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

class SafariRide extends ExtraActivity {
    public SafariRide(boolean selected) {
        super(" Safari Ride", selected ? 30 : 0);
    }

    @Override
    public String getDescription() {
        return price > 0 ? "Yes (RM30)" : "No";
    }
}

class Photobooth extends ExtraActivity {
    public Photobooth(boolean selected) {
        super("Photobooth", selected ? 30 : 0);
    }

    @Override
    public String getDescription() {
        return price > 0 ? "Yes (RM30)" : "No";
    }
}

class BirdShow extends ExtraActivity {
    private int quantity;

    public BirdShow(int quantity, int unitPrice) {
        super("Bird Show", quantity * unitPrice);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getDescription() {
        return "x " + quantity + " = RM " + price;
    }
}

public class ReceiveData extends JFrame {
    private JPanel mainReceive;
    private JLabel lblTicketType;
    private JLabel lblKid;
    private JLabel lblAdult;
    private JLabel lblOKU;
    private JLabel lblSafari;
    private JLabel lblPhotobooth;
    private JLabel lblAnimalFood;
    private JLabel lblBirdShow;
    private JLabel lblTotal;
    private JButton btnPay;
    private JButton btnEdit;

    private String ticketType;
    private int kid, kidPrice, adult, adultPrice, oku, okuPrice, animalFood, animalFoodPrice, total;
    private ExtraActivity[] activities;

    public ReceiveData(String ticketType, int kid, int kidPrice, int adult, int adultPrice, int oku, int okuPrice,
                       boolean safari, boolean photobooth, int animalFood, int animalFoodPrice,
                       int birdShow, int birdShowPrice, int total) {

        this.ticketType = ticketType;
        this.kid = kid;
        this.kidPrice = kidPrice;
        this.adult = adult;
        this.adultPrice = adultPrice;
        this.oku = oku;
        this.okuPrice = okuPrice;
        this.animalFood = animalFood;
        this.animalFoodPrice = animalFoodPrice;
        this.total = total;

        this.activities = new ExtraActivity[]{
                new SafariRide(safari),
                new Photobooth(photobooth),
                new BirdShow(birdShow, birdShowPrice)
        };

        setTitle("Zoo Ticket Summary");
        setSize(700, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel backgroundLabel = new JLabel();
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/Untitled design.png"));
        backgroundLabel.setIcon(new ImageIcon(bgIcon.getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, 700, 500);

        mainReceive.setOpaque(false);


        lblTicketType.setText("Ticket Type: " + ticketType);
        lblKid.setText("    - Kid       x " + kid + " = RM " + (kid * kidPrice));
        lblAdult.setText("    - Adult   x " + adult + " = RM " + (adult * adultPrice));
        lblOKU.setText("    - OKU    x " + oku + " = RM " + (oku * okuPrice));

        lblSafari.setText("    - " + activities[0].getName() + ": " + activities[0].getDescription());
        lblPhotobooth.setText("    -  " + activities[1].getName() + ": " + activities[1].getDescription());
        lblBirdShow.setText("    - "   + activities[2].getName() +    " " + activities[2].getDescription());

        lblAnimalFood.setText("    - Animal Food   x " + animalFood + "  = RM " + (animalFood * animalFoodPrice));
        lblTotal.setText(" Total: RM"  + total);

        btnPay.addActionListener(e -> handlePayment());

        btnEdit.addActionListener(e -> {
            this.dispose();
            new Checkout(ticketType, kid, kidPrice, adult, adultPrice, oku, okuPrice,
                    activities[0].getPrice() > 0, activities[1].getPrice() > 0, animalFood, animalFoodPrice,
                    ((BirdShow) activities[2]).getQuantity(), birdShowPrice, total);
        });

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 500));

        backgroundLabel.setBounds(0, 0, 700, 500);
        mainReceive.setBounds(0, 0, 700, 500);

        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(mainReceive, Integer.valueOf(1));

        setContentPane(layeredPane);
        pack();
        setVisible(true);
    }

    //latest
    private void handlePayment() {
        int confirm = JOptionPane.showConfirmDialog(this, "Confirm payment?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Object[] options = {"Cash", "Card", "Online Transfer"};
            int method = JOptionPane.showOptionDialog(this, "Select payment method:", "Payment",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (method != JOptionPane.CLOSED_OPTION) {
                saveReceiptToFile(options[method].toString());
                JOptionPane.showMessageDialog(this, "Payment successful via " + options[method] + "!");

                // ✅ Redirect to Price page after successful payment
                this.dispose();            // Close current receipt window
                new Price();               // Open Price page again
            }
        }
    }


    private void saveReceiptToFile(String method) {
        try (FileWriter writer = new FileWriter("receipt.txt")) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            writer.write("\n======================================\n");
            writer.write("           FINAL RECEIPT              \n");
            writer.write("======================================\n");
            writer.write("Date & Time     : " + now.format(formatter) + "\n");
            writer.write("Ticket Type     : " + ticketType + "\n");
            writer.write("--------------------------------------\n");
            writer.write("Kid             x " + kid + " = RM " + (kid * kidPrice) + "\n");
            writer.write("Adult           x " + adult + " = RM " + (adult * adultPrice) + "\n");
            writer.write("OKU             x " + oku + " = RM " + (oku * okuPrice) + "\n");
            writer.write("--------------------------------------\n");
            for (ExtraActivity activity : activities) {
                writer.write(activity.getName() + "     : " + activity.getDescription() + "\n");
            }
            writer.write("Animal Food     x " + animalFood + " = RM " + (animalFood * animalFoodPrice) + "\n");
            writer.write("======================================\n");
            writer.write("Payment Method  : " + method + "\n");
            writer.write("TOTAL           : RM " + total + "\n");
            writer.write("======================================\n");

            File file = new File("receipt.txt");
            if (file.exists()) Desktop.getDesktop().open(file);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to save or open receipt.");
        }
    }
}