package story.model;

import story.enums.Scene;

import java.util.Random;

public class World {
    private final Random rnd;
    private Scene scene = Scene.SETUP;

    private final Room room = new Room("Комната Малыша");

    public World(long seed) {
        this.rnd = new Random(seed);
    }

    public World() {
        this.rnd = new Random();
    }

    public Room room() {
        return room;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public boolean chance(int percent) {
        return rnd.nextInt(100) < percent;
    }

    public int range(int min, int maxInclusive) {
        return min + rnd.nextInt(maxInclusive - min + 1);
    }

    public void log(String s) {
        System.out.println(s);
    }
}
