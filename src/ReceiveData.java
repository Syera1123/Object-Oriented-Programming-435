import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiveData extends JFrame {
    private JPanel mainReceive; // ↔ ini mesti bind dengan .form
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

    public ReceiveData(String ticketType, int kid, int kidPrice, int adult, int adultPrice, int oku, int okuPrice,
                       boolean safari, boolean photobooth, int animalFood, int animalFoodPrice,
                       int birdShow, int birdShowPrice, int total) {
        setTitle("Zoo Ticket Summary");
        setSize(700, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainReceive); // Guna panel dari .form

        // Set text pada semua JLabel
        lblTicketType.setText("Ticket Type: " + ticketType);
        lblKid.setText("   - Kid    x " + kid + " = RM " + (kid * kidPrice));
        lblAdult.setText("   - Adult x " + adult + " = RM " + (adult * adultPrice));
        lblOKU.setText("   - OKU  x " + oku + " = RM " + (oku * okuPrice));
        lblSafari.setText("   - Safari Ride: " + (safari ? "Yes (RM30)" : "No"));
        lblPhotobooth.setText("   - Photobooth: " + (photobooth ? "Yes (RM30)" : "No"));
        lblAnimalFood.setText("   - Animal Food x " + animalFood + " = RM " + (animalFood * animalFoodPrice));
        lblBirdShow.setText("   - Bird Show     x " + birdShow + " = RM " + (birdShow * birdShowPrice));
        lblTotal.setText("TOTAL : RM " + total);

        // Butang "Pay"
        btnPay.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Confirm to pay?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Object[] options = {"Cash", "Card"};
                int method = JOptionPane.showOptionDialog(this, "Choose payment method:", "Payment Method",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (method != JOptionPane.CLOSED_OPTION) {
                    JOptionPane.showMessageDialog(this, "Payment successful via " + options[method] + "!");
                    saveReceiptToFile(ticketType, kid, kidPrice, adult, adultPrice, oku, okuPrice,
                            safari, photobooth, animalFood, animalFoodPrice, birdShow, birdShowPrice, total, options[method].toString());
                }
            }
        });

        setVisible(true);
    }

    private void saveReceiptToFile(String ticketType, int kid, int kidPrice, int adult, int adultPrice, int oku, int okuPrice,
                                   boolean safari, boolean photobooth, int animalFood, int animalFoodPrice,
                                   int birdShow, int birdShowPrice, int total, String method) {
        try (FileWriter writer = new FileWriter("receipt.txt")) {
            writer.write("Ticket Type: " + ticketType + "\n");
            writer.write("Kid x " + kid + " = RM " + (kid * kidPrice) + "\n");
            writer.write("Adult x " + adult + " = RM " + (adult * adultPrice) + "\n");
            writer.write("OKU x " + oku + " = RM " + (oku * okuPrice) + "\n");
            writer.write("Safari Ride: " + (safari ? "Yes (RM30)" : "No") + "\n");
            writer.write("Photobooth: " + (photobooth ? "Yes (RM30)" : "No") + "\n");
            writer.write("Animal Food x " + animalFood + " = RM " + (animalFood * animalFoodPrice) + "\n");
            writer.write("Bird Show x " + birdShow + " = RM " + (birdShow * birdShowPrice) + "\n");
            writer.write("TOTAL: RM " + total + "\n");
            writer.write("Payment Method: " + method + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to save receipt.");
        }
    }
}
