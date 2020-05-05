import java.util.stream.Stream;

public class Door {
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

    // Constructor for randomized generation on Block
    Door(boolean isFirstRoom, int X, int Y, int width, int height) {
        this.isLinked = false;
        // Randomize on which face the door is
        double rand = Math.random();

        // Make sure if it's the first room that the door isn't spawned on the left side
        while (isFirstRoom && rand <= 0.25) {
            rand = Math.random();
        }

        int rand2;
        // Generate the door on the Block's face
        if (rand <= 0.25) { // left
            rand2 = randomInt(0, height - 1); // Takes a random position on the Block's left face
            setDoor("left",X - 1,Y + rand2, X - 2, Y + rand2);
        } else if (rand <= 0.5) { // up
            rand2 = randomInt(0, width - 1);
            setDoor("up",X + rand2,Y - 1, X + rand2, Y - 2);
        } else if (rand <= 0.75) { // right
            rand2 = randomInt(0, height - 1);
            setDoor("right",X + width,Y + rand2, X + width + 1, Y + rand2);
        } else { // down
            rand2 = randomInt(0, width - 1);
            setDoor("down",X + rand2,Y + height, X + rand2, Y + height + 1);
        }
    }

    // Functions
    public int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public void setDoor(String direction, int posX, int posY, int nextRoomPosX, int nextRoomPosY){
        this.direction = direction;
        this.posX = posX;
        this.posY = posY;
        this.nextRoomPosX = nextRoomPosX;
        this.nextRoomPosY = nextRoomPosY;
    }
}