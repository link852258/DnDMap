import Dungeon.Dungeon;
import GUI.DungeonSizeFrame;
import GUI.MainFrame;

public class Dnd {
    public static void main(String[] args){
        MainFrame main = new MainFrame();
        Dungeon d = new Dungeon(10,15,8);
        Controller c = new Controller(main,d);
        c.initController();
    }
}
