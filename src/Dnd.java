import Dungeon.Dungeon;
import GUI.MainFrame;

public class Dnd {
    public static void main(String[] args){
        new MainFrame(1000,1000);
        Dungeon d = new Dungeon(10,15,8);
        d.generateDungeon();
        MainFrame.setDungeon(d);
        MainFrame.addImage();
        MainFrame.show();
    }
}
