

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Price extends JFrame implements ActionListener {
    private JPanel pnlPrice;
    private JButton btnContinueOrder;
    private JList<String> list1;
    private JList<String> list2;
    private JLabel lblWeekday;
    private JLabel lblWeekend;

    public Price() {
        super.setSize(500, 300);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Weekday Prices
        DefaultListModel<String> model1 = new DefaultListModel<>();
        model1.addElement("Kid: RM10");
        model1.addElement("Adult: RM20");
        model1.addElement("OKU: RM5");
        list1.setModel(model1);

        // Weekend Prices
        DefaultListModel<String> model2 = new DefaultListModel<>();
        model2.addElement("Kid: RM15");
        model2.addElement("Adult: RM25");
        model2.addElement("OKU: RM10");
        list2.setModel(model2);

        btnContinueOrder.addActionListener(this);

        Container cp = super.getContentPane();
        cp.add(pnlPrice);

        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnContinueOrder) {
            new Checkout();
        }
    }

    public static void main(String[] args) {
        new Price();
    }
}
