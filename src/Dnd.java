import GUI.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Dnd {
    public static void main(String[] args){
        MainFrame main = new MainFrame(1000,1000);
        Dungeon d = new Dungeon(2000,2000,3);
        d.generateDungeon();
        main.addImage(d.draw());
        main.show();
    }
}
