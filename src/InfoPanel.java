import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoPanel extends JPanel implements ActionListener {
    CardLayout cardLayout = new CardLayout ();
    InfoPanel(){
        this.setLayout(cardLayout);
        for(Country c : Country.countries){
            this.add(c.settingPanel, c.name);
        }

        this.setBackground(Color.RED.darker ().darker ().darker ());
        this.setPreferredSize(new Dimension(400, 450));
        cardLayout.show (this, "China");

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
