import Dungeon.Dungeon;
import GUI.DungeonSizeFrame;
import GUI.MainFrame;

public class Dnd {
    public static void main(String[] args){
        MainFrame main = new MainFrame();
        Dungeon d = new Dungeon(15,15,8, 20);
        Controller c = new Controller(main,d);
        c.initController();
    }
}
