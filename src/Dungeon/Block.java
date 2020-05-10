package Dungeon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Block implements Serializable {
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
    ArrayList<Door> doors;
    int doorCount;
    int width;
    int height;
    boolean isEligibleForMoreDoors = true;

    Block(int minHeight, int maxHeight, int minWidth, int maxWidth, int minDoors, int maxDoors){
        MIN_HEIGHT = minHeight;
        MAX_HEIGHT = maxHeight;
        MIN_WIDTH = minWidth;
        MAX_WIDTH = maxWidth;
        MIN_DOORS = minDoors;
        MAX_DOORS = maxDoors;
        doors = new ArrayList<>();
    }

    // Functions
    public int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    // Generation for original room
    public void generateFirst(int dungeonSize) {
        // Randomize sizes
        this.width = randomInt(this.MIN_WIDTH, this.MAX_WIDTH);
        this.height = randomInt(this.MIN_HEIGHT, this.MAX_HEIGHT);
        this.dataTable = new char[this.width][this.height];
        // Randomize the amount of doors
        this.doorCount = this.randomInt(this.MIN_DOORS+2, this.MAX_DOORS+1);
        // Set Dungeon.Block original position
        this.posTopLeftX = dungeonSize/2 + 1;
        this.posTopLeftY = dungeonSize/2 - (Math.floorDiv(this.height, 2)); // TO TEST POSITION
        // Generate it's first entry door on [200, 200]
        Door d = new Door(dungeonSize / 2, dungeonSize / 2, "left");
        this.doors.add(d);
        // Generate other doors
        for (int X = 1; X < this.doorCount; X++) {
            this.createDoorWithoutCheckingForElegibility(true);
        }
        // Bake Dungeon.Block into its own dataTable
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
        if (direction.equals("up") || direction.equals("down")) {
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
        } else if (direction.equals("left") || direction.equals("right")) {
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

        // Randomize the Dungeon.Block positioning bias
        int bias = randomInt(minimum, maximum);

        // Figure out TopLeft tile
        this.findTopLeft(X, Y, direction, bias);

        // Randomize the amount of doors
        this.doorCount = randomInt(MIN_DOORS, MAX_DOORS);

        // Dungeon.Door isLinked
        d.isLinked = true;

        // Assign first door with the originating door
        doors.add(d);

        // Generate doors keeping in mind the
        for (int I = 1; I < doorCount; I++) {
            this.createDoorWithoutCheckingForElegibility(false);
        }

        // Bake Dungeon.Block into its own dataTable
        this.bakeIntoDataTable();
    }

    public Door createDoor(boolean isFirstRoom) {
        Door d;
        if (this.isEligibleForMoreDoors) {
            d = new Door(false, this.posTopLeftX, this.posTopLeftY, this.width, this.height, doors);
            this.doors.add(d);
            this.checkIfBlockEligibleForMoreDoors();
            return d;
        } else {
            return null;
        }
    }

    public Door createDoorWithoutCheckingForElegibility(boolean isFirstRoom) {
        Door d = new Door(false, this.posTopLeftX, this.posTopLeftY, this.width, this.height, doors);
        this.doors.add(d);
        this.checkIfBlockEligibleForMoreDoors();
        return d;
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
            case "left":
                posTopLeftX = X - (this.width - 1);
                posTopLeftY = Y - (bias - 1);
                break;
            case "up":
                posTopLeftX = X - (bias - 1);
                posTopLeftY = Y - (this.height - 1);
                break;
            case "right":
                posTopLeftX = X;
                posTopLeftY = Y - (bias - 1);
                break;
            case "down":
                posTopLeftX = X - (bias - 1);
                posTopLeftY = Y;
                break;
        }
    }

    public void checkIfBlockEligibleForMoreDoors() {
        boolean isThereDoorLeft = false;
        boolean isThereDoorUp = false;
        boolean isThereDoorRight = false;
        boolean isThereDoorDown = false;
        for (int X = 0; X < doors.size(); X++) {
            if (doors.get(X).direction == "left")
                isThereDoorLeft = true;
            else if (doors.get(X).direction == "up")
                isThereDoorUp = true;
            else if (doors.get(X).direction == "right")
                isThereDoorRight = true;
            else if (doors.get(X).direction == "down")
                isThereDoorDown = true;
        }
        if (isThereDoorDown && isThereDoorLeft && isThereDoorRight && isThereDoorUp)
            this.isEligibleForMoreDoors = false;
    }

    public void deleteRemainingDoors() {
        for (int X = 1; X < doors.size(); X++) {
            if (!doors.get(X).isLinked)
                doors.remove(X);
        }
    }
}
