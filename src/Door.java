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
            this.direction = "left";
            rand2 = randomInt(0, height - 1); // Takes a random position on the Block's left face
            this.posX = X - 1; // One before Block floor
            this.posY = Y + rand2;
            this.nextRoomPosX = X - 2;
            this.nextRoomPosY = Y + rand2;
        } else if (rand <= 0.5) { // up
            direction = "up";
            rand2 = randomInt(0, width - 1);
            this.posX = X + rand2;
            this.posY = Y - 1;
            this.nextRoomPosX = X + rand2;
            this.nextRoomPosY = Y - 2;
        } else if (rand <= 0.75) { // right
            direction = "right";
            rand2 = randomInt(0, height - 1);
            this.posX = X + width;
            this.posY = Y + rand2;
            this.nextRoomPosX = X + width + 1;
            this.nextRoomPosY = Y + rand2;
        } else { // down
            direction = "down";
            rand2 = randomInt(0, width - 1);
            this.posX = X + rand2;
            this.posY = Y + height;
            this.nextRoomPosX = X + rand2;
            this.nextRoomPosY = Y + height + 1;
        }
    }

    // Functions
    public int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }
}