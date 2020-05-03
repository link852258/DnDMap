import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Dungeon extends JPanel {
    // Constants
    private final int MIN_NB_OF_ROOMS;
    private final int MAX_NB_OF_ROOMS;
    private static final String DUNGEON_NAME = "Default";
    private static final String DUNGEON_TYPE = "2D";
    private static final int RGB_FLOOR = new Color(98, 137, 90).getRGB();
    private static final int RGB_GRID = new Color(45, 45, 45).getRGB();
    private static final int RGB_DOOR = new Color(151, 97, 67).getRGB();
    private static final int DUNGEON_SIZE_RATIO = 40;

    // Variables
    private char[][] dataTable;
    private HashMap<Integer, Block> blockMap;
    private int scaleNumber;
    private int firstX;
    private int firstY;
    private int lastX;
    private int lastY;

    // Functions
    public Dungeon(int minRooms, int maxRooms, int scale){
        // Constants
        MIN_NB_OF_ROOMS = minRooms;
        MAX_NB_OF_ROOMS = maxRooms;

        // Variables
        scaleNumber = scale;
        dataTable = new char[MAX_NB_OF_ROOMS*DUNGEON_SIZE_RATIO][MAX_NB_OF_ROOMS*DUNGEON_SIZE_RATIO];
        blockMap = new HashMap<>();
        firstX = MAX_NB_OF_ROOMS * DUNGEON_SIZE_RATIO;
        lastX = 0;
        firstY = MAX_NB_OF_ROOMS * DUNGEON_SIZE_RATIO;
        lastY = 0;
    }

    /*public void fillDataTable(){
        for(int i = 0; i < dataTable.length; i++){
            for(int j=0; j < dataTable[0].length; j++){
                dataTable[i][j]='W';
            }
        }
    }*/

    public int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public void generateDungeon() {
        // Randomize the amount of rooms in the dungeon
        int wantedNbOfRooms = randomInt(MIN_NB_OF_ROOMS, MAX_NB_OF_ROOMS);

        // Generate the first block
        Block b = generateFirstBlock();

        // Create and populate list of unused doors
        ArrayList<Door> unusedDoorQueue = new ArrayList<>();
        this.populateDoorQueue(b, unusedDoorQueue);

        // Generate other blocks
        int currentNbOfRooms = 1;
        int rand;
        Door d;
        HashMap<String, Integer> space;
        do {
            boolean roomGenerated = false;
            do {
                // Get a random door from the queue
                rand = randomInt(0, unusedDoorQueue.size() - 1);
                d = unusedDoorQueue.get(rand);
                // Check if the spot where the new room would be generate already has floor
                if (this.dataTable[d.nextRoomPosX][d.nextRoomPosY] != 'F') {
                    b = this.generateBlock("room", d.nextRoomPosX, d.nextRoomPosY, d, d.direction);

                    // Populate the door queue with the new unused doors
                    this.populateDoorQueue(b, unusedDoorQueue);

                    // Generation completed
                    currentNbOfRooms++;
                    roomGenerated = true;
                } else {
                    // Assume the door is taken and remove from queue
                    d.isLinked = true;
                    unusedDoorQueue.remove(d);
                }
            } while (!roomGenerated);
        } while (currentNbOfRooms < wantedNbOfRooms && unusedDoorQueue.size() != 0);
    } // TODO LATER - Generate Room at a random position and then generate Passage to it

    private void populateDoorQueue(Block b, ArrayList<Door> unusedDoorQueue) {
        for (int X = 1; X < b.doors.length; X++) {
            if (!b.doors[X].isLinked)
                unusedDoorQueue.add(b.doors[X]);
        }
    }

    public Block generateFirstBlock() {
        Block b = new Room();
        b.generateFirst();
        this.bakeIntoDataTable(b);
        blockMap.put(blockMap.size(), b);
        return b;
    }

    public Block generateBlock(String type, int X, int Y, Door d, String direction) {
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
        b.generate(X, Y, d, direction, space);
        // Add generated block to the blockMap
        this.blockMap.put(blockMap.size(), b);
        // Immediately draw it into the dataTable
        this.bakeIntoDataTable(b);
        return b;
    }

    public HashMap<String,Integer> scanForSpace(int maxWidth, int maxHeight, int X, int Y) {
        HashMap<String,Integer> space = new HashMap<>();
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
        // TODO - NEEDS TO BE TESTED
        for (int X = 0; X < b.dataTable.length; X++){
            for (int Y = 0; Y < b.dataTable[0].length; Y++) {
                this.dataTable[b.posTopLeftX+X][b.posTopLeftY+Y] = b.dataTable[X][Y];
            }
        }
        for (int X = 0; X < b.doors.length; X++){
            this.dataTable[b.doors[X].posX][b.doors[X].posY] = 'D';
        }
    }

    // Display the Dungeon tiles as individual pixels on an image and then scale it up
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (!blockMap.isEmpty()) {
            BufferedImage bi = new BufferedImage(dataTable.length, dataTable[0].length, BufferedImage.TYPE_INT_RGB);
            Image image;
            // Draw the dungeon
            for (int X = 0; X < dataTable.length; X++) {
                for (int Y = 0; Y < dataTable[0].length; Y++) {
                    if (dataTable[X][Y] == 'F') {
                        bi.setRGB(X, Y, RGB_FLOOR);
                        this.checkDungeonSizeDuringScan(X, Y);
                    } else if (dataTable[X][Y] == 'D') {
                        bi.setRGB(X, Y, RGB_DOOR);
                        this.checkDungeonSizeDuringScan(X, Y);
                    }
                }
            }

            // Scale up the image for easier viewing and to draw a grid with transparency on top of the dungeon
            image = bi.getScaledInstance(dataTable.length * scaleNumber, dataTable[0].length * scaleNumber, Image.SCALE_SMOOTH);
            g.drawImage(image, 0, 0, this);
            bi = new BufferedImage(dataTable.length * scaleNumber, dataTable[0].length * scaleNumber, BufferedImage.TYPE_INT_ARGB);
            for (int X = 0; X < dataTable.length * scaleNumber; X++) {
                for (int Y = 0; Y < dataTable[0].length * scaleNumber; Y++) {
                    if ((X % scaleNumber) == 0)
                        bi.setRGB(X, Y, RGB_GRID);
                    if ((Y % scaleNumber) == 0)
                        bi.setRGB(X, Y, RGB_GRID);
                }
            }

            g.drawImage(bi, 0, 0, this);
        }
    }

    private void checkDungeonSizeDuringScan(int X, int Y) {
        if (X < this.firstX)
            this.firstX = X;
        if (X > this.lastX)
            this.lastX = X;
        if (Y < this.firstY)
            this.firstY = Y;
        if (Y > this.lastY)
            this.lastY = Y;
    }
}