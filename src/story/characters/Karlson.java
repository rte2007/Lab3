package story.characters;

import story.enums.Location;
import story.enums.Mood;
import story.exceptions.WaitingTooLongException;
import story.model.World;

public class Karlson extends Person {
    private int waitedSteps = 0;

    public Karlson() {
        super("Карлсон", Mood.CALM, Location.ROOF);
    }

    public void waitOnRoof(World w) throws WaitingTooLongException {
        waitedSteps++;
        w.log("Карлсон ждёт на крыше... (ожидание: " + waitedSteps + ")");

        if (waitedSteps >= 3 && w.chance(40)) {
            throw new WaitingTooLongException(waitedSteps);
        }
        if (waitedSteps >= 3 && w.chance(35)) {
            changeMood(Mood.IMPATIENT);
        }
    }

    public void hoverNearWindow(World w) {
        w.log("Карлсон кружит рядом с окном и прислушивается.");
    }

    public void enterRoom(World w) {
        moveTo(Location.ROOM);
        w.log("Карлсон залетает в комнату.");
    }

    public void hide(World w) {
        moveTo(Location.HIDE_SPOT);
        w.log("Карлсон прячется в комнате.");
    }

    public void flyAway(World w) {
        moveTo(Location.ROOF);
        w.log("Карлсон улетает прочь.");
    }

    @Override
    public void react(World w) {
        if (mood == Mood.IMPATIENT && w.chance(55)) {
            w.log("Карлсон: \"Ну и долго же!\"");
        }
    }
}
