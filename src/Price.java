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
        super.setSize(500,250);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //add action Listener
        btnContinueOrder.addActionListener(this);

        Container cp = super.getContentPane();
        cp.add(pnlPrice);

        super.setVisible(true);

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
