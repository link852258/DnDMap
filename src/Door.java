public class Door {
    boolean isLinked = false;
    String direction;
    int[] pos = new int[2];
    int[] nextRoomPos = new int[2];

    // Constructor with exact position and direction
    Door(int X, int Y, String direction) {
        this.isLinked = true;
        this.pos[0] = X;
        this.pos[1] = Y;
        this.direction = direction;
    }

    // Constructor for randomized generation on Block
    Door(boolean isFirstRoom, int X, int Y, int width, int height) {
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
            this.pos[0] = X - 1; // One before Block floor
            this.pos[1] = Y + rand2;
            this.nextRoomPos[0] = X - 2;
            this.nextRoomPos[1] = Y + rand2;
        } else if (rand <= 0.5) { // up
            direction = "up";
            rand2 = randomInt(0, width - 1);
            this.pos[0] = X + rand2;
            this.pos[1] = Y - 1;
            this.nextRoomPos[0] = X + rand2;
            this.nextRoomPos[1] = Y - 2;
        } else if (rand <= 0.75) { // right
            direction = "right";
            rand2 = randomInt(0, height - 1);
            this.pos[0] = X + width;
            this.pos[1] = Y + rand2;
            this.nextRoomPos[0] = X + width + 2;
            this.nextRoomPos[1] = Y + rand2;
        } else { // down
            direction = "down";
            rand2 = randomInt(0, width - 1);
            this.pos[0] = X + rand2;
            this.pos[1] = Y + height;
            this.nextRoomPos[0] = X + rand2;
            this.nextRoomPos[1] = Y + height + 2;
        }
    }

    // Functions
    public int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }
}