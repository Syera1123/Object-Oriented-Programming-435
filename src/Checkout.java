import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.im.InputContext;

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

        //button checkout add ActionListener
        btnCheckout.addActionListener(this);


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

        //change JSpinner from String to Integer
        int noKid = Integer.parseInt(String.valueOf(spKid.getValue()));
        int noAdult = Integer.parseInt(String.valueOf(spAdult.getValue()));
        int noOKU = Integer.parseInt(String.valueOf(spOKU.getValue()));
        int noAnimalFood = Integer.parseInt(String.valueOf(spAnimalFood.getValue()));
        int noBirdShow = Integer.parseInt(String.valueOf(spBirdShow.getValue()));


        if(e.getSource()==btnCheckout) {        //if  user click checkout button

            //initialize
            double priceKid = 0.0;
            double priceAdult = 0.0;
            double priceOKU = 0.0;
            double priceAnimalFood = 0.0;
            double priceBirdShow = 0.0;
            double sum = 0.0;


            if(rbWeekday.isSelected()){

                priceKid = 10 * (double)noKid;
                priceAdult = 12 * (double)noAdult;
                priceOKU = 7 * (double)noOKU;

                //Calculate sum for kid, adult and OKU
                sum = priceKid + priceAdult + priceOKU;

                //optional : Safari Ride
                if(rbSafariYes.isSelected())
                    sum += 30;
                else if(rbSafariNo.isSelected())
                    sum = sum;
                else {
                    JOptionPane.showMessageDialog(null, "Please select whether you would like to include the Safari Ride.");
                    return;
                }

                //optional : Photobooth
                if(rbPhotoboothYes.isSelected())
                    sum += 30;
                else if(rbPhotoboothNo.isSelected())
                    sum = sum;
                else {
                    JOptionPane.showMessageDialog(null, "Please select whether you would like to include the Photobooth service.");
                    return;
                }

                priceAnimalFood = 10 * (double)noAnimalFood;
                priceBirdShow = 15 * (double)noBirdShow;

                sum = sum + priceAnimalFood + priceBirdShow;

                //PRINT RECEIPT
                JOptionPane.showMessageDialog(this,
                        "\nno of kid = " + noKid +
                                "\nprice kid = " + priceKid +
                                "\nno of adult = " + noAdult +
                                "\nprice adult = " + priceAdult +
                                "\nno OKU = " + noOKU +
                                "\nprice OKU = " + priceOKU +
                                "\nno of animal food = " + noAnimalFood +
                                "\nprice animal food = " + priceAnimalFood +
                                "\n\nSUM = RM " + sum);



            }
            else if (rbWeekend.isSelected()){
                priceKid = 13 * (double)noKid;
                priceAdult = 15 * (double)noAdult;
                priceOKU = 9 * (double)noOKU;

                //Calculate sum for kid, adult and OKU
                sum = priceKid + priceAdult + priceOKU;

                //optional : Safari Ride
                if(rbSafariYes.isSelected())
                    sum += 30;
                else if(rbSafariNo.isSelected())
                    sum = sum;
                else {
                    JOptionPane.showMessageDialog(null, "Please select whether you would like to include the Safari Ride.");
                    return;
                }

                //optional : Photobooth
                if(rbPhotoboothYes.isSelected())
                    sum += 30;
                else if(rbPhotoboothNo.isSelected())
                    sum = sum;
                else {
                    JOptionPane.showMessageDialog(null, "Please select whether you would like to include the Photobooth service.");
                    return;
                }

                priceAnimalFood = 10 * (double)noAnimalFood;
                priceBirdShow = 15 * (double)noBirdShow;

                sum = sum + priceAnimalFood + priceBirdShow;

                //PRINT RECEIPT
                JOptionPane.showMessageDialog(this,
                        "\nno of kid = " + noKid +
                                "\nprice kid = " + priceKid +
                                "\nno of adult = " + noAdult +
                                "\nprice adult = " + priceAdult +
                                "\nno OKU = " + noOKU +
                                "\nprice OKU = " + priceOKU +
                                "\nno of animal food = " + noAnimalFood +
                                "\nprice animal food = " + priceAnimalFood +
                                "\n\nSUM = RM " + sum);
            }
            else {
                JOptionPane.showMessageDialog(null,"Please select a visit day (Weekday or Weekend) before proceeding.");
            }



        }
    }
}
