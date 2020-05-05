import GUI.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Dnd {
    public static void main(String[] args){
        MainFrame main = new MainFrame(1000,1000);
        Dungeon d = new Dungeon(50,100,8);
        d.generateDungeon();
        main.addImage(d.draw());
        main.show();
    }
}
