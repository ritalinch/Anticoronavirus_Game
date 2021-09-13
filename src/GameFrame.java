import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameFrame extends JFrame {

    static ArrayList<GameFrame> arr = new ArrayList<> ();
    InfoPanel infoPanel;
    static int hardness;
    GameFrame(int hardness){
        GameFrame.hardness = hardness;
        this.setTitle("Anticoronavirus game");
        this.standardSettings();
        this.setLayout(new BorderLayout());
        this.panelCreation();
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        arr.add(this);
    }

    public void panelCreation(){
        this.add(new ButtonPanel(), BorderLayout.NORTH);
        this.add(new ImagePanel(new ImageIcon("src/Images/52611.png").getImage()), BorderLayout.WEST);
        this.infoPanel = new InfoPanel();
        this.add(infoPanel, BorderLayout.EAST);
    }

    public void standardSettings() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        this.setBounds(d.width / 2 - 600, d.height / 2 - 250, 1200, 500);
    }

}
