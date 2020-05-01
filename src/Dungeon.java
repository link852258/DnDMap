import java.util.HashMap;

public class Dungeon {
    // Constants
    private static final int MIN_NB_OF_ROOMS = 3;
    private static final int MAX_NB_OF_ROOMS = 10;
    private static final String DUNGEON_NAME = "Default";
    private static final String DUNGEON_TYPE = "2D";

    // Variables
    char[][] dataTable;
    HashMap<Integer,Block> blockMap;

    // Functions
    public int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public void generateDungeon() {
        int nbOfRooms = randomInt(MIN_NB_OF_ROOMS, MAX_NB_OF_ROOMS);
        Block b = generateFirstBlock();
        for (int f = 1; f < nbOfRooms; f++) {
            // Generate other blocks
            if(Math.random() <= 0.5) {
                // TODO - Generate Room adjacent to Door
            } else {
                // TODO - Generate Room at a random position and then generate Passage to it
            }
        }
    }

    public Block generateFirstBlock() {
        Block b = new Room();
        b.generateFirst();
        return b;
    }

    public Block generateBlock(String type, int X, int Y, String direction) {
        Block b;
        // Micro factory
        switch (type) {
            case "passage":
                b = new Passage();
            default:
                b = new Room();
        }
        // Check for available space
        HashMap<String,Integer> space = scanForSpace(b.MAX_WIDTH, b.MAX_HEIGHT, X, Y);
        // Generate room according to available space
        b.generate(X, Y, direction, space);
        // Add generated block to the blockMap
        blockMap.put(blockMap.size(), b);
        // Immediately draw it into the dataTable
        this.bakeIntoDataTable(b);
        return b;
    }

    public HashMap<String,Integer> scanForSpace(int maxWidth, int maxHeight, int X, int Y) {
        HashMap<String,Integer> space = new HashMap<String,Integer>();
        int currentSpace = 0;
        // Scan RIGHT
        do {
            currentSpace++;
        } while (dataTable[X+currentSpace+2][Y] != 'F' && currentSpace <= maxWidth);
        // Add to Map
        space.put("right", currentSpace);
        currentSpace = 0;

        // Scan LEFT
        do {
            currentSpace++;
        } while (dataTable[X-currentSpace-2][Y] != 'F' && currentSpace <= maxWidth);
        // Add to Map
        space.put("left", currentSpace);
        currentSpace = 0;

        // Scan DOWN
        do {
            currentSpace++;
        } while (dataTable[X][Y+currentSpace+2] != 'F' && currentSpace <= maxHeight);
        // Add to Map
        space.put("down", currentSpace);
        currentSpace = 0;

        // Scan UP
        do {
            currentSpace++;
        } while (dataTable[X][Y-currentSpace-2] != 'F' && currentSpace <= maxHeight);
        // Add to Map
        space.put("up", currentSpace);

        return space;
    }

    public void bakeIntoDataTable(Block b) {
        // TODO - Apply Block's dataTable data to Dungeon's dataTable at it's right position
    }
}