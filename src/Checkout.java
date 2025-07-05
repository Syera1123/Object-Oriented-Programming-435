import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Checkout extends JFrame implements ActionListener {
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
    //button group
    private ButtonGroup bgWeek;
    private ButtonGroup bgSafariRide;
    private ButtonGroup bgPhotobooth;




    public Checkout(){
        super.setSize(700,500);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        //group membership radio button
        bgWeek = new ButtonGroup();
        bgWeek.add(rbWeekday);
        bgWeek.add(rbWeekend);
        bgSafariRide = new ButtonGroup();
        bgSafariRide.add(rbSafariYes);
        bgSafariRide.add(rbSafariNo);
        bgPhotobooth = new ButtonGroup();
        bgPhotobooth.add(rbPhotoboothYes);
        bgPhotobooth.add(rbPhotoboothNo);

        //group spinner
        spKid.setModel(new SpinnerNumberModel(0,0,1000,1));
        spAdult.setModel(new SpinnerNumberModel(1,1,1000,1));
        spOKU.setModel(new SpinnerNumberModel(0,0,1000,1));
        spAnimalFood.setModel(new SpinnerNumberModel(0,0,1000,1));
        spBirdShow.setModel(new SpinnerNumberModel(0,0,1000,1));


        //add to panel
        Container cp = super.getContentPane();
        cp.add(pnlCheckout);





        super.setVisible(true);

    }

    public static void main(String[] args){
        new Checkout();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
