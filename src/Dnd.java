import javax.swing.*;
import java.awt.*;

public class Dnd {
    public static void main(String[] args){
        Dungeon d = new Dungeon(50,50,10);
        JFrame frame = new JFrame("DnDMap");
        d.bakeTestRoom();
        frame.setSize(500,500);
        frame.setLayout(new BorderLayout());
        frame.add(d,BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
