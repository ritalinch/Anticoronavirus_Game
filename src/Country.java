import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Country {
    static ArrayList<Country> countries = new ArrayList<>();
    String name;
    int population;
    double infected;
    static double recoveredPeople = 0;
    static double worldPopulation = 0;
    static int deadCountries = 0;
    int area;
    JButton button;
    int density;
    Infection infection;
    Recovery recovery;
    JLabel label;
    ArrayList <Country> neighbours = new ArrayList<> ();
    JPanel settingPanel;
    double infectionSpeed = 0;
    double recoverySpeed = 0;

    public Country(String name, int population, int area, JButton button){
        worldPopulation += population;
        this.infection = new Infection();
        this.recovery = new Recovery ();
        this.name = name;
        this.settingPanel = new JPanel (){
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(new ImageIcon("src/Images/doctorsymbol.png").getImage(),
                        0, 0, null);
            }
            @Override
            public String toString(){ return Country.this.name; }
        };
        this.population = population;
        this.area = area;
        countries.add(this);
        this.button = button;
        this.infected = 0;
        this.density = population/area;
        this.label = new JLabel ();
        this.setSettingPanel ();

    }

    public String toString (){

        return this.name + " " + this.population + " " + this.area;
    }

    public void setSettingPanel(){
        this.settingPanel.setLayout (null);
        JLabel name = new JLabel ("COUNTRY: " + this.name);
        name.setFont (new Font("Monospaced", Font.BOLD, 25));
        name.setBounds (25, 17, 350, 45);
        name.setForeground (Color.RED.darker ().darker ());
        this.settingPanel.add(name);
        JTextArea info = new JTextArea ("POPULATION: " + this.population + "\n" +
                "AREA: " + this.area + "\n" +
                "DENSITY: " + this.density);
        info.setForeground (Color.RED.darker ().darker ());
        info.setFont (new Font("Monospaced", Font.BOLD, 20));
        JScrollPane scrollPane = new JScrollPane (info);
        scrollPane.setBounds (25, 60, 350,70);
        scrollPane.setPreferredSize (new Dimension (350, 70));
        scrollPane.setOpaque(false);
        scrollPane.setBorder (null);
        scrollPane.getVerticalScrollBar().setBackground (Color.BLACK);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI () {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.RED.darker ().darker ().darker ().darker ();
            }
        });
        scrollPane.getViewport().setOpaque(false);
        info.setOpaque(false);
        this.settingPanel.add(scrollPane);

        javax.swing.SwingUtilities.invokeLater(setInfectedField::new);

        JScrollPane measuresPane = new JScrollPane (new QuarantineMeasuresPanel ());
        measuresPane.setBounds (25, 225, 350,215);
        measuresPane.setPreferredSize (new Dimension (350, 215));
        measuresPane.setOpaque(false);
        measuresPane.setBorder (null);
        measuresPane.getVerticalScrollBar().setBackground (Color.BLACK);
        measuresPane.getVerticalScrollBar().setUI(new BasicScrollBarUI () {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.RED.darker ().darker ().darker ().darker ();
            }
        });
        measuresPane.getViewport().setOpaque(false);
        this.settingPanel.add(measuresPane);
    }

    class QuarantineMeasuresPanel extends JPanel implements ActionListener{
        MeasureButton maskWearing;
        MeasureButton socialDistance;
        MeasureButton publicPlaces;
        MeasureButton transport;
        MeasureButton eLearning;
        MeasureButton waterBorders;
        MeasureButton airBorders;
        MeasureButton landBorders;
        MeasureButton totalLockdown;
        QuarantineMeasuresPanel(){
            this.setOpaque (false);

            this.setLayout (new GridLayout (9, 1, 5, 10));

            this.maskWearing = new MeasureButton ("Mask wearing", 50);
            this.add(this.maskWearing);

            this.socialDistance = new MeasureButton ("Social distance", 100);
            this.add(this.socialDistance);

            this.publicPlaces = new MeasureButton ("Public places", 200);
            this.add(this.publicPlaces);

            this.transport = new MeasureButton ("Transport", 500);
            this.add(this.transport);

            this.eLearning = new MeasureButton ("eLearning", 1000);
            this.add(this.eLearning);

            this.waterBorders = new MeasureButton ("Water borders", 3000);
            this.add(this.waterBorders);

            this.airBorders = new MeasureButton ("Air borders", 7000);
            this.add(this.airBorders);

            this.landBorders = new MeasureButton ("Land borders", 10000);
            this.add(this.landBorders);

            this.totalLockdown = new MeasureButton ("Total lockdown", 15000);
            this.add(this.totalLockdown);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if ((1.0*Country.this.population)/10000 > Country.this.infected) JOptionPane.showMessageDialog (null,"You are not able to use it now, please wait for more people to be infected");
            else if(e.getSource () == this.maskWearing){
                if (this.maskWearing.price > Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes)
                    JOptionPane.showMessageDialog (null, "no enough banknotes");
                else {
                    Country.this.recoverySpeed *= 2;
                    if (Country.this.recoverySpeed == 0) Country.this.recovery.start ();
                    Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes -= this.maskWearing.price;
                    this.maskWearing.setEnabled (false);
                    this.maskWearing.setOpaque (false);
                    Country.this.updateRecoverySpeed (2);
                }
            }
            else if(e.getSource ()==this.socialDistance){
                if (this.socialDistance.price > Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes)
                    JOptionPane.showMessageDialog (null, "no enough banknotes");
                else {
                    Country.this.recoverySpeed *= 4;
                    if (Country.this.recoverySpeed == 0) Country.this.recovery.start ();
                    Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes -= this.socialDistance.price;
                    this.socialDistance.setEnabled (false);
                    this.socialDistance.setOpaque (false);
                    Country.this.updateRecoverySpeed (3);
                }
            }
            else if(e.getSource ()==this.publicPlaces){
                if (this.publicPlaces.price > Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes)
                    JOptionPane.showMessageDialog (null, "no enough banknotes");
                else {
                    Country.this.recoverySpeed *= 16;
                    if (Country.this.recoverySpeed == 0) Country.this.recovery.start ();
                    Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes -= this.publicPlaces.price;
                    this.publicPlaces.setEnabled (false);
                    this.publicPlaces.setOpaque (false);
                    Country.this.updateRecoverySpeed (5);
                }
            }
            else if(e.getSource ()==this.transport){
                if (this.transport.price > Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes)
                    JOptionPane.showMessageDialog (null, "no enough banknotes");
                else {
                    Country.this.recoverySpeed *= 64;
                    if (Country.this.recoverySpeed == 0) Country.this.recovery.start ();
                    Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes -= this.transport.price;
                    this.transport.setEnabled (false);
                    this.transport.setOpaque (false);

                    Country.this.updateRecoverySpeed (15);
                }
            }
            else if(e.getSource ()==this.eLearning){
                if (this.eLearning.price > Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes)
                    JOptionPane.showMessageDialog (null, "no enough banknotes");
                else {
                    Country.this.recoverySpeed *= 128;
                    if (Country.this.recoverySpeed == 0) Country.this.recovery.start ();
                    Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes -= this.eLearning.price;
                    this.eLearning.setEnabled (false);
                    this.eLearning.setOpaque (false);

                    Country.this.updateRecoverySpeed (30);
                }
            }
            else if(e.getSource ()==this.waterBorders){
                if (this.waterBorders.price > Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes)
                    JOptionPane.showMessageDialog (null, "no enough banknotes");
                else {
                    Country.this.recoverySpeed *= 256;
                    if (Country.this.recoverySpeed == 0) Country.this.recovery.start ();
                    Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes -= this.waterBorders.price;
                    this.waterBorders.setEnabled (false);
                    this.waterBorders.setOpaque (false);
                    for(Root r : Root.roots)
                        if((r.a == Country.this || r.b == Country.this) && r.type == Root.Type.WATER){
                            r.iconAction.timer.stop ();
                        }
                    Country.this.updateRecoverySpeed (50);
                }
            }
            else if(e.getSource ()==this.airBorders){
                if (this.airBorders.price > Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes)
                    JOptionPane.showMessageDialog (null, "no enough banknotes");
                else {
                    Country.this.recoverySpeed *= 512;
                    if (Country.this.recoverySpeed == 0) Country.this.recovery.start ();
                    Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes -= this.airBorders.price;
                    this.airBorders.setEnabled (false);
                    this.airBorders.setOpaque (false);
                    for(Root r : Root.roots)
                        if((r.a == Country.this || r.b == Country.this) && r.type == Root.Type.AIR){
                            r.iconAction.timer.stop ();
                        }
                    Country.this.updateRecoverySpeed (70);
                }
            }
            else if(e.getSource ()==this.landBorders){
                if (this.landBorders.price > Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes)
                    JOptionPane.showMessageDialog (null, "no enough banknotes");
                else {
                    Country.this.recoverySpeed *= 1024;
                    if (Country.this.recoverySpeed == 0) Country.this.recovery.start ();
                    Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes -= this.landBorders.price;
                    this.landBorders.setEnabled (false);
                    this.landBorders.setOpaque (false);
                    for(Root r : Root.roots)
                        if((r.a == Country.this || r.b == Country.this) && r.type == Root.Type.LAND){
                            r.iconAction.timer.stop ();
                        }
                    Country.this.updateRecoverySpeed (100);
                }
            }
            else if(e.getSource ()==this.totalLockdown){
                if (this.totalLockdown.price > Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes)
                    JOptionPane.showMessageDialog (null, "no enough banknotes");
                else {
                    Country.this.recoverySpeed *= 2048;
                    if (Country.this.recoverySpeed == 0) Country.this.recovery.start ();
                    Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes -= this.totalLockdown.price;
                    this.totalLockdown.setEnabled (false);
                    this.totalLockdown.setOpaque (false);
                    for(Root r : Root.roots)
                        if(r.a == Country.this || r.b == Country.this){
                            r.iconAction.timer.stop ();
                        }
                    Country.this.updateRecoverySpeed (150);
                }
            } }
        class MeasureButton extends JButton{
            int price;
            String name;
            MeasureButton(String name, int price){
                this.price = price;
                this.name = name;
                this.setIcon (new ImageIcon ("src/Images/icon.png"));
                this.setText (price + ":    " + name);
                this.setBackground (Color.RED.darker ());
                this.setFont (new Font ("Monospaced", Font.BOLD, 15));
                this.setForeground (Color.BLACK);
                this.setPreferredSize (new Dimension (300, 35));
                this.addActionListener (QuarantineMeasuresPanel.this);
            }
        }
    }


    class setInfectedField{
        final JTextArea iInfected = new JTextArea ();
        setInfectedField(){
            Country.this.settingPanel.add(iInfected);
            iInfected.setBounds (25, 135, 350, 85);
            iInfected.setOpaque (false);
            iInfected.setBorder (null);
            iInfected.setForeground (Color.RED.darker ().darker ());
            iInfected.setFont (new Font("Monospaced", Font.BOLD, 20));
            Timer timer = new Timer(1000/25, arg0 -> iInfected.setText (
                        "INFECTED: " + (int) Country.this.infected + "\n" +
                                "INFECTION SPEED: " + Country.this.infectionSpeed + "\n" +
                                "RECOVERY SPEED: " + Country.this.recoverySpeed)
            );
            timer.start();
        }
    }

    public void startInfection(){ this.infected = 1; this.updateInfectionSpeed (); }
    public void updateInfectionSpeed() { this.infectionSpeed = GameFrame.hardness * (1.0*this.density) * (this.infected)/(1.0*this.population); }
    public synchronized void infectionProcess() {  this.infected += this.infectionSpeed; }

    public void startRecovery(){ this.recoverySpeed = 1; this.updateRecoverySpeed (1);}
    public void updateRecoverySpeed(int x) { this.recoverySpeed = recoverySpeed + x * this.density * (this.infected/this.population); }
    public synchronized void recoveryProcess() {
        this.infected -= this.recoverySpeed;
        recoveredPeople += this.recoverySpeed;
        Player.playersRecords.get (Player.playersRecords.size () - 1).banknotes += (int)this.recoverySpeed;
    }

    class Infection extends Thread{

        @Override
        public void run() {
            Country.this.startInfection ();
            Country.this.button.setBackground (Color.RED.darker ());
            while (Country.this.infected < Country.this.population){
                try {
                    sleep (1000);
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
                if((int)(Country.this.infected) > (Country.this.population/10000))
                    for(Country cN : Country.this.neighbours)
                        if(cN.infected == 0)
                            cN.infection.start ();
                Country.this.updateInfectionSpeed ();
                Country.this.infectionProcess ();

            }
            Country.deadCountries++;
            Country.this.button.setEnabled (false);
            this.interrupt ();
        }
    }
    class Recovery extends Thread{

        @Override
        public void run() {
            Country.this.startRecovery ();
            Country.this.button.setIcon (new ImageIcon ("src/Images/recoveryIcon.png"));
            while (!Thread.currentThread ().isInterrupted ()) {
                try {
                    sleep (1000);
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
                Country.this.recoveryProcess ();
            }
        }
    }
}
