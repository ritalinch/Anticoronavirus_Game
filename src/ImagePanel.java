import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


class ImagePanel extends JPanel implements ActionListener {

    Image img;
    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(800, 450);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        for (Country c : Country.countries) {
            this.add(c.button);
            c.button.addActionListener (this);
        }
        for (Root r : Root.roots){
            switch (r.type) {
                case AIR -> SwingUtilities.invokeLater (() -> r.iconAction =
                        new iconAction (r.a.button.getLocation (),
                                r.b.button.getLocation (), "src/Images/airplane.png", Color.RED));
                case WATER -> SwingUtilities.invokeLater (() -> r.iconAction =
                        new iconAction (r.a.button.getLocation (),
                                r.b.button.getLocation (), "src/Images/ship.png", Color.BLUE));
                case LAND -> SwingUtilities.invokeLater (() -> r.iconAction =
                        new iconAction (r.a.button.getLocation (),
                                r.b.button.getLocation (), "src/Images/car.png", Color.GREEN));
            }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, 0, 0, null);
        for (Root r : Root.roots){
            g2.setColor(r.color);
            g2.setStroke(new BasicStroke (2));
            g2.drawLine(r.a.button.getX(), r.a.button.getY(), r.b.button.getX(), r.b.button.getY());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Country c : Country.countries)
            if(e.getSource () == c.button) {
                GameFrame.arr.get (0).infoPanel.cardLayout.show (
                        GameFrame.arr.get (0).infoPanel,  c.name);
            }
    }

    class iconAction{
        final JPanel square = new JPanel();
        ImageIcon icon;
        Timer timer;

        Point start;
        Point end;
        int x;
        int y;

        iconAction(Point start, Point end, String path, Color color){
            this.start = start;
            this.end = end;
            x = start.x;
            y = start.y;
            icon = new ImageIcon (path);
            square.add(new JLabel(icon));
            ImagePanel.this.add(square);
            square.setBounds(start.x, start.y ,25,25);
            square.setBackground(color);
            int width = end.x - start.x;
            int height = end.y - start.y;
            int gcd = gcd(width, height);
            timer = new Timer(1000/(gcd/2), arg0 -> {
                if ((x >= end.x && end.x > start.x) ||
                        (x <= end.x && end.x < start.x)){
                    x = start.x;
                    y = start.y;
                }
                else {
                    x = x + width/gcd;
                    y = y + height/gcd;
                }
                square.setLocation(x, y);

            });
            timer.start();
        }

        public int gcd(int a, int b) {
            if (a < 0) a = -a;
            if (b < 0) b = -b;
            if (b==0) return a;
            return gcd(b,a%b);
        }
    }
}
