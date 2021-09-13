import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    ButtonPanel(){
        this.setLayout (new BorderLayout ());
        this.setBackground(Color.RED.darker ().darker ().darker ());
        this.setPreferredSize(new Dimension(1200, 50));
        this.add (new BanknotesInfo (), BorderLayout.EAST);
    }
    static class BanknotesInfo extends JLabel{
        BanknotesInfo(){
            this.setIcon (new ImageIcon ("src/Images/banknoteicon.png"));
            this.setFont (new Font("Monospaced", Font.BOLD, 25));
            this.setBounds (900, 5, 200, 40);
            this.setForeground (Color.BLACK);
            Timer timer = new Timer(2000, arg0 -> this.setText (
                    Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes + " BANKNOTES"
            ));
            timer.start();
        }
    }
}
