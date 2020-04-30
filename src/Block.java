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
    int width;
    int length;
    int[] posTopLeft;

    Block(int minHeight, int maxHeight, int minWidth, int maxWidth, int minDoors, int maxDoors){
        MIN_HEIGHT = minHeight;
        MAX_HEIGHT = maxHeight;
        MIN_WIDTH = minWidth;
        MAX_WIDTH = maxWidth;
        MIN_DOORS = minDoors;
        MAX_DOORS = maxDoors;
        posTopLeft = new int[2];
        width = 0;
        length = 0;
    };
}
