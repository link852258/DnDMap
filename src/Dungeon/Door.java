package Dungeon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Door implements Serializable {
    boolean isLinked;
    String direction;
    int posX;
    int posY;
    int nextRoomPosX;
    int nextRoomPosY;

    // Constructor with exact position and direction
    Door(int X, int Y, String direction) {
        this.isLinked = true;
        this.posX = X;
        this.posY = Y;
        this.direction = direction;
    }

    // Constructor for randomized generation on Dungeon.Block using existing Dungeon.Block doors
    Door(boolean isFirstRoom, int X, int Y, int width, int height, ArrayList<Door> doors) {
        this.isLinked = false;
        // Randomize on which face the door is
        double rand = Math.random();

        // Make sure if it's the first room that the door isn't spawned on the left side
        while (isFirstRoom && rand <= 0.25) {
            rand = Math.random();
        }

        boolean isGenerated = false;
        int rand2;
        do {
            if (rand <= 0.25) { // left
                if (isDoorGeneratedOnSameSide(doors, "left")) {
                    isGenerated = true;
                    rand2 = randomInt(0, height - 1); // Takes a random position on the Dungeon.Block's left face
                    setDoor("left", X - 1, Y + rand2, X - 2, Y + rand2);
                }
            } else if (rand <= 0.5) { // up
                if (isDoorGeneratedOnSameSide(doors, "up")) {
                    isGenerated = true;
                    rand2 = randomInt(0, width - 1);
                    setDoor("up", X + rand2, Y - 1, X + rand2, Y - 2);
                }
            } else if (rand <= 0.75) { // right
                if (isDoorGeneratedOnSameSide(doors, "right")) {
                    isGenerated = true;
                    rand2 = randomInt(0, height - 1);
                    setDoor("right", X + width, Y + rand2, X + width + 1, Y + rand2);
                }
            } else { // down
                if (isDoorGeneratedOnSameSide(doors, "down")) {
                    isGenerated = true;
                    rand2 = randomInt(0, width - 1);
                    setDoor("down", X + rand2, Y + height, X + rand2, Y + height + 1);
                }
            }
            if(!isGenerated){
                rand = Math.random();
            }

        }while (!isGenerated);
    }


    // Functions
    private int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    private int numberDoorsOnSide(ArrayList<Door> doors, String direction) {
        Stream<Door> sd = doors.stream();
        return (int)sd.filter(x -> x.direction.equals(direction)).count();
    }

    public boolean isDoorGeneratedOnSameSide(ArrayList<Door> doors, String direction){
        return randomInt(0, this.numberDoorsOnSide(doors, direction) * 5) == 0;
    }

    public void setDoor(String direction, int posX, int posY, int nextRoomPosX, int nextRoomPosY){
        this.direction = direction;
        this.posX = posX;
        this.posY = posY;
        this.nextRoomPosX = nextRoomPosX;
        this.nextRoomPosY = nextRoomPosY;
    }
}