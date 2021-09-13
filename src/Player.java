import java.io.*;
import java.util.ArrayList;

public class Player implements Serializable {
    int banknotes;
    int orderNum;
    String name;
    long timeElapsed;
    boolean victory;
    static ArrayList<Player> playersRecords = new ArrayList<>();
    Player(){
        this.orderNum = playersRecords.size ();
    }
    public void setBanknotes(int x){
        this.banknotes = x;
    }
    public String toString(){
        return "Player No " + orderNum + ": name " + name + ", victory " + victory + ", time elapsed " + timeElapsed + "\n";
    }
    public static void serialize(String fileName, ArrayList<Player> p){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream (fileName)))
        {
            oos.writeObject(p);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }
    public static ArrayList<Player> deserialize(String fileName){
        ArrayList<Player> playersNew = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream (fileName)))
        {
            playersNew=((ArrayList<Player>)ois.readObject());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return playersNew;
    }
}
