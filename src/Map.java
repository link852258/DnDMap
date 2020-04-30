import java.util.HashMap;

public class Map {
    // Constants
    private static final int MIN_NB_OF_ROOMS = 3;
    private static final int MAX_NB_OF_ROOMS = 10;
    private static final String MAP_NAME = "Default";
    private static final String MAP_TYPE = "Flat";

    // Variables
    char[][] dataTable;
    HashMap<Integer,Block> blockMap;

    // Functions
    public void generateMap() {};
    public Block generateBlock() {
        return null;
    };
}