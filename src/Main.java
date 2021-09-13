import javax.swing.*;
import java.awt.*;

public class Main {


    public static void main(String[] args) {
        Country Africa = new Country ("Africa",
                1200000,
                3040,
                new JButton ("Africa") {
                    @Override
                    public void setLayout(LayoutManager mgr) {
                        super.setLayout (mgr);
                    }
                });
        Country SouthAmerica = new Country ("South America",
                422500, 17840,
                new JButton ("South America") {
                    @Override
                    public void setLayout(LayoutManager mgr) {
                        super.setLayout (mgr);
                    }
                }
        );
        Country Canada = new Country ("Canada",
                37600, 9985,
                new JButton ("Canada") {
                    @Override
                    public void setLayout(LayoutManager mgr) {
                        super.setLayout (mgr);
                    }
                });
        Country NorthAmerica = new Country ("North America",
                328200, 9834,
                new JButton ("North America") {
                    @Override
                    public void setLayout(LayoutManager mgr) {
                        super.setLayout (mgr);
                    }
                }
        );
        Country Mexico = new Country ("Mexico",
                127600, 1973,
                new JButton ("Mexico") {
                    @Override
                    public void setLayout(LayoutManager mgr) {
                        super.setLayout (mgr);
                    }
                });
        Country Europe = new Country ("Europe",
                746400, 10180,
                new JButton ("Europe") {
                    @Override
                    public void setLayout(LayoutManager mgr) {
                        super.setLayout (mgr);
                    }
                }
        );
        Country Russia = new Country ("Russia",
                144400, 17130,
                new JButton ("Russia") {
                    @Override
                    public void setLayout(LayoutManager mgr) {
                        super.setLayout (mgr);
                    }
                });
        Country MiddleAsia = new Country ("Middle Asia",
                72000, 4003,
                new JButton ("Middle Asia") {
                    @Override
                    public void setLayout(LayoutManager mgr) {
                        super.setLayout (mgr);
                    }
                });
        Country China = new Country ("China",
                1398000, 9597,
                new JButton ("China") {
                    @Override
                    public void setLayout(LayoutManager mgr) {
                        super.setLayout (mgr);
                    }
                });
        Country India = new Country ("India",
                1366000, 3287,
                new JButton ("India") {
                    @Override
                    public void setLayout(LayoutManager mgr) {
                        super.setLayout (mgr);
                    }
                });
        Country Australia = new Country ("Australia",
                25360, 7692,
                new JButton ("Australia") {
                    @Override
                    public void setLayout(LayoutManager mgr) {
                        super.setLayout (mgr);
                    }
                });
        Africa.button.setBounds(350, 225, 70, 25);
        SouthAmerica.button.setBounds(225, 270, 120, 25);
        Canada.button.setBounds(135, 100, 120, 25);
        NorthAmerica.button.setBounds(180, 150, 120, 25);
        Mexico.button.setBounds(160, 225, 85, 25);
        Europe.button.setBounds(390, 120, 80, 25);
        Russia.button.setBounds(550,90,75, 25);
        MiddleAsia.button.setBounds(450, 190, 110, 25);
        China.button.setBounds(510, 160 , 80, 25);
        India.button.setBounds(480, 230, 75, 25);
        Australia.button.setBounds(575, 310, 90, 25);

        Root.roots.add(new Root(Canada, NorthAmerica, Root.Type.LAND ));
        Root.roots.add(new Root(Mexico, NorthAmerica, Root.Type.LAND ));
        Root.roots.add(new Root(Canada, Mexico, Root.Type.WATER ));
        Root.roots.add(new Root(Mexico, SouthAmerica, Root.Type.WATER ));
        Root.roots.add(new Root(Europe, NorthAmerica, Root.Type.AIR ));
        Root.roots.add(new Root(Europe, SouthAmerica, Root.Type.AIR ));
        Root.roots.add(new Root(Europe, Africa, Root.Type.WATER ));
        Root.roots.add(new Root(Europe, Russia, Root.Type.LAND ));
        Root.roots.add(new Root(Russia, China, Root.Type.LAND ));
        Root.roots.add(new Root(Europe, China, Root.Type.AIR ));
        Root.roots.add(new Root(China, MiddleAsia, Root.Type.LAND ));
        Root.roots.add(new Root(China, India, Root.Type.WATER ));
        Root.roots.add(new Root(India, NorthAmerica, Root.Type.AIR ));
        Root.roots.add(new Root(India, Australia, Root.Type.WATER ));
        Root.roots.add(new Root(Australia, Africa, Root.Type.WATER ));
        Root.roots.add(new Root(Australia, Europe, Root.Type.AIR ));
        Player.playersRecords = Player.deserialize ("src/records");
        new StartFrame ();
    }
}
