package Zoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Price extends JFrame implements ActionListener {
    private JPanel pnlPrice;
    private JButton btnContinueOrder;
    private JList list1;
    private JList list2;
    private JLabel lblWeekday;
    private JLabel lblWeekend;


    public Price(){
        super.setSize(700,500);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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


    public static void main(String[] args){
        new Price();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnContinueOrder){
            Checkout cp = new Checkout();
            dispose();
        }
    }
}
