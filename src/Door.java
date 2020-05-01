public class Door {
    boolean isLinked = false;
    String direction;
    int[] pos = new int[2];

    // Constructor with exact position and direction
    Door(int X, int Y, String direction) {
        this.isLinked = true;
        this.pos[0] = X;
        this.pos[1] = Y;
        this.direction = direction;
    }

    // Constructor for randomized generation on Block
    Door(boolean isFirstRoom, int X, int Y, int length, int height) {
        // Randomize on which face the door is
        double rand = Math.random();

        // Make sure if it's the first room that the door isn't spawned on the left side
        if (isFirstRoom && rand <= 0.25) {
            do {
                rand = Math.random();
            } while (rand <= 0.25);
        }

        // Generate the door on the Block's face
        if (rand <= 0.25) { // left
            direction = "left";
        } else if (rand <= 0.5) { // up
            direction = "up";
        } else if (rand <= 0.75) { // right
            direction = "right";
        } else { // down
            direction = "down";
        }
    }
}