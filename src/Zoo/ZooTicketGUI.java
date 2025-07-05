package Zoo;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class ZooTicketGUI {
    private JButton btnClick;
    private JLabel lblOutput;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ZooTicketGUI().createGUI());
    }

    void createGUI() {
        JFrame frame = new JFrame("Zoo Ticket Checkout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // ====== Ticket Type Panel ======
        JPanel ticketPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ticketPanel.setBorder(BorderFactory.createTitledBorder("Ticket Type"));
        JRadioButton rbWeekday = new JRadioButton("Weekday");
        JRadioButton rbWeekend = new JRadioButton("Weekend");
        ButtonGroup ticketGroup = new ButtonGroup();
        ticketGroup.add(rbWeekday);
        ticketGroup.add(rbWeekend);
        ticketPanel.add(rbWeekday);
        ticketPanel.add(rbWeekend);
        mainPanel.add(ticketPanel);

        // ====== Person Count Panel ======
        JPanel personPanel = new JPanel(new GridLayout(3, 2, 10, 5));
        personPanel.setBorder(BorderFactory.createTitledBorder("Visitors"));
        personPanel.add(new JLabel("Kids:"));
        JSpinner spKid = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
        personPanel.add(spKid);

        personPanel.add(new JLabel("Adults:"));
        JSpinner spAdult = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
        personPanel.add(spAdult);

        personPanel.add(new JLabel("OKU:"));
        JSpinner spOKU = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
        personPanel.add(spOKU);
        mainPanel.add(personPanel);

        // ====== Activities Panel ======
        JPanel activityPanel = new JPanel();
        activityPanel.setLayout(new BoxLayout(activityPanel, BoxLayout.Y_AXIS));
        activityPanel.setBorder(BorderFactory.createTitledBorder("Activities"));

        JPanel safariPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        safariPanel.add(new JLabel("Safari Ride:"));
        JRadioButton rbSafariYes = new JRadioButton("Yes");
        JRadioButton rbSafariNo = new JRadioButton("No", true);
        ButtonGroup safariGroup = new ButtonGroup();
        safariGroup.add(rbSafariYes);
        safariGroup.add(rbSafariNo);
        safariPanel.add(rbSafariYes);
        safariPanel.add(rbSafariNo);
        activityPanel.add(safariPanel);

        JPanel photoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        photoPanel.add(new JLabel("Photobooth:"));
        JRadioButton rbPhotoboothYes = new JRadioButton("Yes");
        JRadioButton rbPhotoboothNo = new JRadioButton("No", true);
        ButtonGroup photoGroup = new ButtonGroup();
        photoGroup.add(rbPhotoboothYes);
        photoGroup.add(rbPhotoboothNo);
        photoPanel.add(rbPhotoboothYes);
        photoPanel.add(rbPhotoboothNo);
        activityPanel.add(photoPanel);

        JPanel extraPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        extraPanel.add(new JLabel("Animal Food:"));
        JSpinner spAnimalFood = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        extraPanel.add(spAnimalFood);

        extraPanel.add(new JLabel("Bird Show:"));
        JSpinner spBirdShow = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        extraPanel.add(spBirdShow);
        activityPanel.add(extraPanel);

        mainPanel.add(activityPanel);

        // ====== Checkout Button ======
        JButton btnCheckout = new JButton("Checkout");
        btnCheckout.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(btnCheckout);

        frame.add(mainPanel);
        frame.setVisible(true);

        // ====== Button Action Listener ======
        btnCheckout.addActionListener(e -> {
            StringBuilder summary = new StringBuilder();
            String ticketType = rbWeekday.isSelected() ? "Weekday" : rbWeekend.isSelected() ? "Weekend" : "Not selected";
            String safari = rbSafariYes.isSelected() ? "Yes" : "No";
            String photobooth = rbPhotoboothYes.isSelected() ? "Yes" : "No";

            summary.append("Ticket Type: ").append(ticketType).append("\n");
            summary.append("Number of Kids: ").append(spKid.getValue()).append("\n");
            summary.append("Number of Adults: ").append(spAdult.getValue()).append("\n");
            summary.append("Number of OKU: ").append(spOKU.getValue()).append("\n");
            summary.append("Safari Ride: ").append(safari).append("\n");
            summary.append("Photobooth: ").append(photobooth).append("\n");
            summary.append("Animal Food: ").append(spAnimalFood.getValue()).append("\n");
            summary.append("Bird Show: ").append(spBirdShow.getValue()).append("\n");

            // Papar dalam pop-up resit
            JTextArea textArea = new JTextArea(summary.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(300, 200));
            JOptionPane.showMessageDialog(frame, scrollPane, "Receipt Summary", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
