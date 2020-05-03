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
    int posTopLeftX;
    int posTopLeftY;
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
    }

    // Functions
    public int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    // Generation for original room
    public void generateFirst() {
        // Randomize sizes
        this.width = randomInt(this.MIN_WIDTH, this.MAX_WIDTH);
        this.height = randomInt(this.MIN_HEIGHT, this.MAX_HEIGHT);
        this.dataTable = new char[this.width][this.height];
        // Randomize the amount of doors
        int rand = this.randomInt(this.MIN_DOORS+2, this.MAX_DOORS+1);
        this.doors = new Door[rand];
        // Set Block original position
        this.posTopLeftX = 51;
        this.posTopLeftY = 50 - (Math.floorDiv(this.height, 2)); // TO TEST POSITION
        // Generate it's first entry door on [200, 200]
        this.doors[0] = new Door(50, 50, "left");
        // Generate other doors
        int doorNb = this.doors.length;
        for (int X = 1; X < doorNb; X++) {
            this.doors[X] = new Door(true, this.posTopLeftX, this.posTopLeftY, this.width, this.height);
        }
        // Bake Block into its own dataTable
        this.bakeIntoDataTable();
    }

    public void generate(int X, int Y, Door d, String direction, HashMap<String,Integer> space) {
        // TODO - Do after default generation
        // Calculate available space
        int availableWidth = Math.min(space.get("left") + space.get("right"), this.MAX_WIDTH);
        int availableHeight = Math.min(space.get("up") + space.get("down"), this.MAX_HEIGHT);

        // Randomize sizes
        this.width = randomInt(this.MIN_WIDTH, availableWidth);
        this.height = randomInt(this.MIN_HEIGHT, availableHeight);
        this.dataTable = new char[this.width][this.height];

        int minimum = 1;
        int maximum = 0;
        // Parse origin-door position minimum and maximum to avoid spillage
        if (direction == "up" || direction == "down") {
            // Minimum parsing
            if (this.width > space.get("right"))
                minimum = 1 + (this.width - space.get("right")); // Minimum is the unavailable space
            else
                minimum = 1; // Maximum space to the right
            // Maximum parsing
            if (this.width > space.get("left"))
                maximum = this.width - (this.width - space.get("left")); // Maximum is random width minus the unavailable place
            else
                maximum = this.width;
        } else if (direction == "left" || direction == "right") {
            // Minimum parsing
            if (this.height > space.get("down"))
                minimum = 1 + (this.height - space.get("down"));
            else
                minimum = 1;
            // Maximum parsing
            if (this.height > space.get("up"))
                maximum = this.height - (this.height - space.get("up"));
            else
                maximum = this.height;
        }

        // Randomize the Block positioning bias
        int bias = randomInt(minimum, maximum);

        // Figure out TopLeft tile
        this.findTopLeft(X, Y, direction, bias);

        // Randomize the amount of doors
        this.doors = new Door[randomInt(MIN_DOORS, MAX_DOORS)];

        // Assign first door with the originating door
        doors[0] = d;

        // Generate doors keeping in mind the
        for (int I = 1; I < doors.length; I++)
            doors[I] = new Door(false, this.posTopLeftX, this.posTopLeftY, this.width, this.height);

        // Bake Block into its own dataTable
        this.bakeIntoDataTable();
    }

    private void bakeIntoDataTable() {
        for (int X = 0; X < this.width; X++) {
            for (int Y = 0; Y < this.height; Y++) {
                this.dataTable[X][Y] = 'F';
            }
        }
    }

    private void findTopLeft(int X, int Y, String direction, int bias) {
        switch (direction) {
            case "left": {
                posTopLeftX = X - (this.width - 1);
                posTopLeftY = Y - bias;
            }
            case "up": {
                posTopLeftX = X + bias;
                posTopLeftY = Y - (this.height - 1);
            }
            case "right": {
                posTopLeftX = X;
                posTopLeftY = Y - bias;
            }
            case "down":
                posTopLeftX = X - bias;
                posTopLeftY = Y;
        }
    }
}
