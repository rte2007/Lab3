package story.characters;

import story.enums.Location;
import story.enums.Mood;
import story.model.World;

public class Malish extends Person {
    public Malish() {
        super("Малыш", Mood.WORRIED, Location.ROOM);
    }

    public void lookOutWindow(World w) {
        w.log("Малыш смотрит в окно на крышу.");
        if (w.chance(25)) changeMood(Mood.EXCITED);
    }

    public boolean giveSignal(World w, boolean risky) {
        int success = risky ? 40 : 75;
        w.log("Малыш пытается подать сигнал.");
        boolean ok = w.chance(success);
        w.log(ok ? "Сигнал получился." : "Сигнал не получился.");
        return ok;
    }

    @Override
    public void react(World w) {
        if (mood == Mood.WORRIED && w.chance(50)) {
            w.log("Малыш: \"Только бы всё получилось...\"");
        } else if (mood == Mood.EXCITED && w.chance(45)) {
            w.log("Малыш: \"Кажется, сейчас!\"");
        }
    }
}
