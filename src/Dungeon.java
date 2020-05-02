
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Dungeon extends JPanel {
    // Constants
    private final int WIDTH;
    private final int HEIGHT;
    private static final int MIN_NB_OF_ROOMS = 3;
    private static final int MAX_NB_OF_ROOMS = 10;
    private static final String DUNGEON_NAME = "Default";
    private static final String DUNGEON_TYPE = "2D";

    // Variables
    char[][] dataTable;
    HashMap<Integer,Block> blockMap;

    // Functions
    public Dungeon(int width, int height){
        WIDTH = width;
        HEIGHT = height;
        dataTable = new char[WIDTH][HEIGHT];
        this.fillDataTable();
    }

    public void fillDataTable(){
        for(int i = 0; i < dataTable.length; i++){
            for(int j=0; j < dataTable[0].length; j++){
                dataTable[i][j]='W';
            }
        }
    }

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
        // Will maybe work?
        for(int X =0; X< b.dataTable.length; X++){
            for (int Y =0; Y< b.dataTable[0].length; Y++){
                dataTable[b.posTopLeft[0]+X][b.posTopLeft[1]+Y] = b.dataTable[X][Y];
            }
        }
        for(int X =0; X< b.doors.length; X++){
            dataTable[b.doors[X].pos[0]][b.doors[X].pos[1]] = 'D';
        }
    }

    //diplay the Map as pixel
    public void paintComponent(Graphics g){
        //TODO vérifié qu'une salle soit bien rendu
        super.paintComponent(g);
        BufferedImage bi = new BufferedImage(dataTable.length,dataTable[0].length, BufferedImage.TYPE_INT_RGB);
        Image image;
        for(int i = 0; i<dataTable.length;i++){
            for(int j=0; j<dataTable[0].length;j++){
                if(dataTable[i][j]=='W')
                    bi.setRGB(j,i,new Color(0,0,0).getRGB());
                else if(dataTable[i][j]=='F')
                    bi.setRGB(j,i,new Color(255,0,0).getRGB());
            }
        }
        image = bi.getScaledInstance(HEIGHT*20,WIDTH*20,Image.SCALE_SMOOTH);
        ((Graphics2D)g).drawImage(image,50,50,this);

        //Create the line on the map
        bi = new BufferedImage(HEIGHT*20,WIDTH*20, BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i<HEIGHT*20;i++){
            for(int j=0; j<WIDTH*20;j++){
                if(i%20==0)
                    bi.setRGB(j,i,new Color(0,0,255).getRGB());
                if(j%20==0)
                    bi.setRGB(j,i,new Color(0,0,255).getRGB());
            }
        }

        ((Graphics2D)g).drawImage(bi,50,50,this);
    }

    //diplay the Map as letters
    public void bakeTestRoom(){
        for(int i = 0; i<5;i++){
            for(int j=0; j<5;j++){
                dataTable[i+10][j+10] = 'F';
            }
        }
    }
}