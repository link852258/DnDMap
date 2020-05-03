
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Dungeon extends JPanel {
    // Constants
    private final int MIN_NB_OF_ROOMS;
    private final int MAX_NB_OF_ROOMS;
    private static final String DUNGEON_NAME = "Default";
    private static final String DUNGEON_TYPE = "2D";
    private static final int RGB_FLOOR = new Color(98, 137, 90).getRGB();
    private static final int RGB_GRID = new Color(255,255,255).getRGB();

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
        dataTable = new char[MAX_NB_OF_ROOMS*20][MAX_NB_OF_ROOMS*20];
        blockMap = new HashMap<Integer, Block>();
        firstX = MAX_NB_OF_ROOMS*20;
        lastX = 0;
        firstY = MAX_NB_OF_ROOMS*20;
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
        this.blockMap.put(blockMap.size(), b);
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
        // TODO - NEEDS TO BE TESTED
        for(int X = 0; X < b.dataTable.length; X++){
            for (int Y = 0; Y < b.dataTable[0].length; Y++){
                this.dataTable[b.posTopLeft[0]+X][b.posTopLeft[1]+Y] = b.dataTable[X][Y];
            }
        }
        for(int X = 0; X< b.doors.length; X++){
            this.dataTable[b.doors[X].pos[0]][b.doors[X].pos[1]] = 'D';
        }
    }

    // Display the Dungeon tiles as individual pixels on an image and then scale it up
    public void paintComponent(Graphics g){
        // TODO - NEEDS TO BE TESTED
        super.paintComponent(g);
        BufferedImage bi = new BufferedImage(dataTable.length, dataTable[0].length, BufferedImage.TYPE_INT_RGB);
        Image image;
        // Draw the dungeon
        for(int X = 0; X < dataTable.length; X++){
            for(int Y = 0; Y < dataTable[0].length; Y++){
                if(dataTable[X][Y] == 'F')
                    bi.setRGB(Y, X, RGB_FLOOR);
                    this.checkDungeonSizeDuringScan(X, Y);
            }
        }

        // Scale up the image for easier viewing and to draw a grid with transparency on top of the dungeon
        image = bi.getScaledInstance(HEIGHT * scaleNumber,WIDTH * scaleNumber, Image.SCALE_SMOOTH);
        ((Graphics2D)g).drawImage(image,0,0,this);
        bi = new BufferedImage(HEIGHT * scaleNumber,WIDTH * scaleNumber, BufferedImage.TYPE_INT_ARGB);
        for(int X = 0; X < HEIGHT * scaleNumber; X++){
            for(int Y = 0; Y < WIDTH * scaleNumber; Y++){
                if((X % scaleNumber) == 0)
                    bi.setRGB(Y, X, RGB_GRID);
                if((Y % scaleNumber) == 0)
                    bi.setRGB(Y, X, RGB_GRID);
            }
        }

        ((Graphics2D)g).drawImage(bi,0,0,this);
    }

    private void checkDungeonSizeDuringScan(int X, int Y) {
        if (X < firstX)
            this.firstX = X;
        if (X > lastX)
            this.lastX = X;
        if (Y < firstY)
            this.firstX = Y;
        if (Y > lastY)
            this.lastY = Y;
    }

    // TODO - TO REMOVE AFTER TESTING
    // Generate a default fake Block on the dataTable for testing
    public void bakeTestRoom(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                this.dataTable[i+10][j+10] = 'F';
            }
        }
    }
}