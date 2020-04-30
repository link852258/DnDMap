import java.util.ArrayList;
import java.util.HashMap;

public abstract class Block {
    // Constants
    protected final int MIN_HEIGHT;
    protected final int MAX_HEIGHT;
    protected final int MIN_WIDTH;
    protected final int MAX_WIDTH;
    protected final int MIN_DOORS;
    protected final int MAX_DOORS;

    // Variables
    char[][] dataTable;
    HashMap<String,Integer> sizesFromOriginalPos;
    int[] originalPos;
    Door[] doors;

    Block(int minHeight, int maxHeight, int minWidth, int maxWidth, int minDoors, int maxDoors){
        MIN_HEIGHT = minHeight;
        MAX_HEIGHT = maxHeight;
        MIN_WIDTH = minWidth;
        MAX_WIDTH = maxWidth;
        MIN_DOORS = minDoors;
        MAX_DOORS = maxDoors;
        originalPos = new int[2];
        doors = new Door[randomInt(MIN_DOORS, MAX_DOORS)];
    };

    // Functions
    public int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public void generate() {
        // TODO - Randomize sizes and generate square block
        // TODO - Generate doors
    };

    public void generate(int X, int Y, String direction, HashMap<String,Integer> space) {
        // TODO - Do after default generation
    };
}
