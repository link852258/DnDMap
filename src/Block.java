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
    int[] posTopLeft;
    Door[] doors;
    int width;
    int height;

    Block(int minHeight, int maxHeight, int minWidth, int maxWidth, int minDoors, int maxDoors){
        MIN_HEIGHT = minHeight;
        MAX_HEIGHT = maxHeight;
        MIN_WIDTH = minWidth;
        MAX_WIDTH = maxWidth;
        MIN_DOORS = minDoors;
        MAX_DOORS = maxDoors;
        posTopLeft = new int[2];
        doors = new Door[randomInt(MIN_DOORS, MAX_DOORS)];
        width = randomInt(MIN_WIDTH, MAX_WIDTH);
        height = randomInt(MIN_WIDTH, MAX_WIDTH);
    };

    // Functions
    public int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    // Generation for original room
    public void generateFirst() {
        // Set original position
        this.posTopLeft[0] = 201;
        this.posTopLeft[1] = 200 + ((height/2) + 1); // TO TEST POSITION
        // TODO - Generate doors
        // Generate it's first entry door on [200, 200]
        doors[0] = new Door(200, 200, "left");
        int doorNb = doors.length;
        for (int x = 1; x < doorNb; x++) {
            doors[x] = new Door(true, this.posTopLeft[0], this.posTopLeft[1], width, height);
        }
    };

    public void generate(int X, int Y, String direction, HashMap<String,Integer> space) {
        // TODO - Do after default generation
    };

    public void bakeIntoDataTable(Block b) {
        // TODO - Apply to the Block's dataTable
    };
}
