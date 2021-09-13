import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class StartFrame extends JFrame implements ActionListener {
    JButton newGame = new JButton ("New game");
    JButton records = new JButton ("Records");
    JButton exit = new JButton ("Exit");
    static long start;
    static long end;
    static ArrayList<JButton> buttons = new ArrayList<>();
    public StartFrame(){
        buttons.add(newGame);
        buttons.add(records);
        buttons.add(exit);
        newGame.addActionListener (this);
        newGame.setPreferredSize (new Dimension (350, 100));
        newGame.setBackground (Color.red.darker ().darker ());
        newGame.setFont (new Font("Monospaced", Font.BOLD, 50));
        records.addActionListener (this);
        records.setPreferredSize (new Dimension (350, 100));
        records.setBackground (Color.red.darker ().darker ());
        records.setFont (new Font("Monospaced", Font.BOLD, 50));
        exit.addActionListener (this);
        exit.setPreferredSize (new Dimension (350, 100));
        exit.setBackground (Color.red.darker ().darker ());
        exit.setFont (new Font("Monospaced", Font.BOLD, 50));
        this.setTitle("Start game");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize ();
        this.setBounds(d.width / 2 - 350, d.height / 2 - 190, 700, 380);
        JPanel panel = new JPanel (){
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(new ImageIcon("src/Images/world map_small (1).png").getImage(),
                        0, 0, null);
            }
        };
        panel.setBounds (0, 0, 400, 400);
        panel.setLayout(new FlowLayout ());
        panel.add(newGame);
        panel.add(records);
        panel.add(exit);
        this.add(panel);
        this.setVisible(true);
    }

    static class EndFrame extends JFrame{
        EndFrame(String ans, String comment){
            this.setTitle("Game over");
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension d = tk.getScreenSize();
            this.setBounds(d.width / 2 - 200, d.height / 2 - 200, 400, 200);
            JPanel panel = new JPanel (){
                public void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.drawImage(new ImageIcon("src/Images/world map_small (1).png").getImage(),
                            0, 0, null);
                }
            };
            panel.setBounds (0, 0, 400, 200);
            panel.setOpaque (false);
            this.add(panel, BorderLayout.CENTER);
            JTextField res = new JTextField (ans);
            res.setOpaque (false);
            panel.add (res);
            panel.setBorder (null);
            res.setBorder (null);
            res.setFont(new Font ("Monospaced", Font.BOLD, 40));
            res.setForeground (Color.red.darker ());
            res.setPreferredSize (new Dimension (195, 100));
            JTextField inf = new JTextField (comment);
            inf.setOpaque (false);
            panel.add (inf);
            panel.setBorder (null);
            inf.setBorder (null);
            inf.setFont(new Font ("Monospaced", Font.BOLD, 15));
            inf.setForeground (Color.red.darker ());
            this.add(new JTextArea ());
            this.setVisible (true);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource () == newGame) {
            this.dispose();
            final GameFrame[] gf = new GameFrame[1];
            Player player = new Player ();
            Country.countries.get (8).infection.start ();
            Thread game = new Thread(() -> {
                int lvl = Integer.parseInt (
                        JOptionPane.showInputDialog (null,
                                "please enter the level fo complexity (1, 2, 3)"));
                switch (lvl) {
                    case 1 -> {
                        player.setBanknotes (1500);
                        gf[0] = new GameFrame (100);
                    }
                    case 2 -> {
                        player.setBanknotes (1000);
                        gf[0] = new GameFrame (1000);
                    }
                    case 3 -> {
                        player.setBanknotes (500);
                        gf[0] = new GameFrame (10000);
                    }
                    default -> JOptionPane.showMessageDialog (null, "inappropriate level of hardness");
                }
                start = System.nanoTime ();
            });
            Thread check = new Thread (() -> {
                do{
                    try {
                        sleep(5000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace ();
                    }
                    if (Country.deadCountries >= 5) {
                        gf[0].dispose();
                        EndFrame ef = new EndFrame ("GAMEOVER", "too many people were infected");
                        try {
                            sleep(10000);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace ();
                        }
                        ef.dispose ();
                        end = System.nanoTime ();
                        player.name = JOptionPane.showInputDialog (null, "Enter you name");
                        player.timeElapsed = end - start;
                        player.victory = false;
                        Player.playersRecords.add(player);
                        Player.serialize("records.txt", Player.playersRecords);
                        System.exit (1);
                        break;
                    }
                    else if (Country.worldPopulation/2 < Country.recoveredPeople) {
                        gf[0].dispose();
                        EndFrame ef = new EndFrame ("YOU WON", "vaccine was created");
                        try {
                            sleep (3500);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace ();
                        }
                        ef.dispose ();
                        end = System.nanoTime ();
                        player.name = JOptionPane.showInputDialog (null, "Enter you name");
                        player.timeElapsed = end - start;
                        player.victory = false;
                        Player.playersRecords.add(player);
                        Player.serialize("src/records", Player.playersRecords);
                        System.exit (1);
                        break;
                    }
                } while(true);

            });
            check.start ();
            game.start();

        }
        else if (e.getSource () == records){
            this.dispose ();
            JFrame records = new JFrame ("records");
            records.setDefaultCloseOperation(EXIT_ON_CLOSE);
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension d = tk.getScreenSize();
            records.setBounds(d.width / 2 - 500, d.height / 2 - 300, 1000, 600);
            JPanel panel = new JPanel (){
                public void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.drawImage(new ImageIcon("src/Images/world map_small (1).png").getImage(),
                            0, 0, null);
                }
            };
            panel.setBounds (0, 0, 1000, 600);
            panel.setOpaque (false);
            records.add(panel, BorderLayout.CENTER);
            JTextArea res = new JTextArea(Player.playersRecords.toString ());
            res.setOpaque (false);
            panel.add (res);
            panel.setBorder (null);
            res.setBorder (null);
            res.setFont(new Font ("Monospaced", Font.BOLD, 20));
            res.setForeground (Color.red);
            res.setPreferredSize (new Dimension (1000, 600));
            records.setVisible (true);
        }
        else if(e.getSource () == exit)System.exit(1);
    }
}
