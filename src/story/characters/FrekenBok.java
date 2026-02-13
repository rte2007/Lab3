package story.characters;

import story.enums.FeetState;
import story.enums.Location;
import story.enums.Mood;
import story.exceptions.CaughtByFrekenBokException;
import story.model.World;

public class FrekenBok extends Person {
    private FeetState feet = FeetState.DIRTY;

    public FrekenBok() {
        super("Фрекен Бок", Mood.CALM, Location.ROOM);
    }

    public FeetState feet() {
        return feet;
    }

    public boolean isControlling() {
        return location == Location.ROOM && feet == FeetState.DIRTY;
    }

    public void inspect(World w) {
        w.log("Фрекен Бок придирчиво осматривает комнату.");
        if (w.chance(35)) changeMood(Mood.ANNOYED);
    }

    public void washFeet(World w) {
        moveTo(Location.BATHROOM);
        w.log("Фрекен Бок идёт в ванную мыть ноги.");
        feet = FeetState.CLEAN;
        w.log("Фрекен Бок моет ноги.");
        if (mood == Mood.ANNOYED && w.chance(60)) changeMood(Mood.CALM);
    }

    public void returnToRoom(World w) {
        moveTo(Location.ROOM);
        w.log("Фрекен Бок возвращается в комнату.");
    }

    public void noticeSomething(World w) {
        int p = (feet == FeetState.DIRTY) ? 55 : 25;
        if (w.chance(p)) throw new CaughtByFrekenBokException();
    }

    @Override
    public void react(World w) {
        if (feet == FeetState.DIRTY) {
            w.log("Фрекен Бок: \"Порядок прежде всего!\"");
        } else {
            w.log("Фрекен Бок: \"Так-то лучше.\"");
        }
    }

    @Override
    public String toString() {
        return name + " [mood=" + mood + ", feet=" + feet + ", location=" + location + "]";
    }
}
