import Dungeon.Dungeon;
import GUI.MainFrame;

public class Dnd {
    public static void main(String[] args){
        MainFrame main = new MainFrame(1000,1000);
        Dungeon d = new Dungeon(10,15,8);
        Controller c = new Controller(main,d);
        c.initController();
    }
}
