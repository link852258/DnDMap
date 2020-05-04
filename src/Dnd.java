import GUI.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Dnd {
    public static void main(String[] args){
        MainFrame main = new MainFrame(1000,1000);
        Dungeon d = new Dungeon(950,1000,5);
        d.setPreferredSize(new Dimension(2000,2000));
        d.setLayout(new BorderLayout());
        d.generateDungeon();
        main.addImage(d.draw());
        main.show();
    }
}
