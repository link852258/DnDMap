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
    public char[][] dataTable;
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
    }

    // Functions
    public int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    // Generation for original room
    public void generateFirst() {
        // Set original position
        this.posTopLeft[0] = 201;
        this.posTopLeft[1] = 200 + ((height/2) + 1); // TO TEST POSITION
        // Generate it's first entry door on [200, 200]
        doors[0] = new Door(200, 200, "left");
        // Generate other doors
        int doorNb = doors.length;
        for (int X = 1; X < doorNb; X++) {
            doors[X] = new Door(true, this.posTopLeft[0], this.posTopLeft[1], width, height);
        }
        // Bake Block into its own dataTable
        this.bakeIntoDataTable();
    }

    public void generate(int X, int Y, String direction, HashMap<String,Integer> space) {
        // TODO - Do after default generation
        // Calculate available space
        int availableWidth = space.get("left") + space.get("right");
        if (availableWidth > MAX_WIDTH)
            availableWidth = MAX_WIDTH;
        int availableHeight = space.get("up") + space.get("down");
        if (availableHeight > MAX_HEIGHT)
            availableHeight = MAX_HEIGHT;
        // Randomize sizes
        int randWidth = randomInt(MIN_WIDTH, availableWidth);
        int randHeight = randomInt(MIN_HEIGHT, availableHeight);
        // Randomize position of originating door
        int originDoorPos;
        int minimum;
        int maximum;
        // SEUL JO CONNAITRA L'EFFORT QUI A ÉTÉ NÉCÉSSAIRE À CRÉER CECI
        // Parse origin-door position minimum and maximum to avoid spillage
        if (direction == "up" || direction == "down") {
            if (randWidth > space.get("right"))
                minimum = 1 + (randWidth - space.get("right")); // Minimum is the unavailable space
            else
                minimum = 1; // Maximum space to the right
            if (randWidth > space.get("left"))
                maximum = randWidth - (randWidth - space.get("left")); // Maximum is random width minus the unavailable place
            else
                maximum = randWidth;
            originDoorPos = randomInt(minimum, maximum);
        }
        this.posTopLeft[0] = 5;
    }

    public void bakeIntoDataTable() {
        for (int X = 0; X < width; X++) {
            for (int Y = 0; Y < height; Y++) {
                this.dataTable[this.posTopLeft[0]][this.posTopLeft[1]] = 'F';
            }
        }
    }
}
