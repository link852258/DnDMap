import javax.swing.*;
import java.awt.*;

public class Dnd {
    public static void main(String[] args){
        JFrame frame = new JFrame("DnDMap");
        Dungeon d = new Dungeon(3,10,8);
        frame.setSize(1800,900);
        frame.setLayout(new BorderLayout());
        frame.add(d,BorderLayout.CENTER);
        frame.setVisible(true);
        d.generateFirstBlock();
    }
}
