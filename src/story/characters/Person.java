package story.characters;

import story.enums.Location;
import story.enums.Mood;
import story.model.World;

import java.util.Objects;

public abstract class Person implements Movable {
    protected final String name;
    protected Mood mood;
    protected Location location;

    protected Person(String name, Mood mood, Location location) {
        this.name = name;
        this.mood = mood;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Mood getMood() {
        return mood;
    }

    public Location getLocation() {
        return location;
    }

    public void changeMood(Mood m) {
        mood = m;
        System.out.println(name + " меняет настроение на " + mood);
    }

    public void moveTo(Location loc) {
        location = loc;
    }

    public abstract void react(World w);

    @Override
    public String toString() {
        return name + " [mood=" + mood + ", location=" + location + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person p)) return false;
        return Objects.equals(name, p.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
