import java.awt.*;
import java.util.ArrayList;

public class Root {
    public enum Type{ AIR, WATER, LAND }
    public static ArrayList<Root> roots = new ArrayList<>();
    Country a;
    Country b;
    Type type;
    Color color;
    ImagePanel.iconAction iconAction;
    public Root(Country a, Country b, Root.Type type){
        this.a = a;
        this.b = b;
        this.type = type;
        switch (type){
            case AIR -> color = Color.RED;
            case LAND -> color = Color.GREEN;
            case WATER -> color = Color.BLUE;
        }
        a.neighbours.add(b);
        b.neighbours.add(a);
    }
}
