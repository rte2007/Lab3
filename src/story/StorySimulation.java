package story;

import story.characters.*;
import story.enums.*;
import story.exceptions.*;
import story.model.World;

import java.util.ArrayList;
import java.util.List;

public class StorySimulation {
    public static void main(String[] args) {
        World w = (args.length > 0) ? new World(parseSeed(args[0])) : new World();

        Karlson karlson = new Karlson();
        Malish malish = new Malish();
        FrekenBok frekenBok = new FrekenBok();

        List<Person> cast = new ArrayList<>(List.of(karlson, malish, frekenBok));

        System.out.println("=_-_-_-_ НАЧАЛО ИСТОРИИ _-_-_-_=");

        int maxTicks = w.range(6, 10);
        boolean meeting = false;
        boolean karlsonLeft = false;

        for (int i = 0; i < maxTicks; i++) {
            if (!meeting && !karlsonLeft) {
                w.setScene(Scene.WAITING);
                try {
                    karlson.waitOnRoof(w);
                } catch (WaitingTooLongException e) {
                    w.log(e.getMessage());
                    if (w.chance(55)) {
                        w.log("Карлсон обижается и решает улететь.");
                        karlson.flyAway(w);
                        karlsonLeft = true;
                        continue;
                    } else {
                        karlson.changeMood(Mood.IMPATIENT);
                    }
                }

                w.setScene(Scene.CONTROL);
                frekenBok.inspect(w);
                malish.lookOutWindow(w);

                if (frekenBok.feet() == FeetState.DIRTY && w.chance(45)) {
                    w.setScene(Scene.WASHING);
                    frekenBok.washFeet(w);
                }

                if (frekenBok.feet() == FeetState.CLEAN) {
                    w.setScene(Scene.OPPORTUNITY);

                    boolean quickReturn = w.chance(35);
                    if (quickReturn) {
                        w.log("Фрекен Бок моет ноги быстро — она может вернуться в любой момент.");
                    } else {
                        w.log("В комнате становится тише.");
                    }

                    boolean risky = frekenBok.isControlling();
                    boolean signal = malish.giveSignal(w, risky);

                    w.setScene(Scene.ATTEMPT);
                    if (signal) {
                        boolean openMeeting = !quickReturn && w.chance(70);
                        if (openMeeting) {
                            karlson.enterRoom(w);
                            meeting = true;
                        } else {
                            karlson.hoverNearWindow(w);
                            if (w.chance(60)) {
                                karlson.hide(w);
                                meeting = true;
                            }
                        }
                    } else {
                        w.log("Малыш не смог подать сигнал — шанс упущен.");
                    }

                    if (frekenBok.getLocation() == Location.BATHROOM && w.chance(80)) {
                        frekenBok.returnToRoom(w);
                    }

                    if (meeting && frekenBok.getLocation() == Location.ROOM) {
                        try {
                            frekenBok.noticeSomething(w);
                        } catch (CaughtByFrekenBokException ex) {
                            w.log(ex.getMessage());
                            w.log("Карлсон спасается и улетает обратно.");
                            karlson.moveTo(Location.ROOF);
                            meeting = false;
                            malish.changeMood(Mood.WORRIED);
                        }
                    }
                }

            } else {
                break;
            }

            for (Person p : cast) p.react(w);

            if (karlsonLeft || meeting) break;

            System.out.println();
        }

        System.out.println("=_-_-_-_ ФИНАЛ _-_-_-_=");
        if (karlsonLeft) {
            System.out.println("Карлсон улетел, и Малыш остался ждать.");
        } else if (meeting) {
            if (karlson.getLocation() == Location.ROOM) {
                System.out.println("Карлсон и Малыш встретились в комнате.");
            } else if (karlson.getLocation() == Location.HIDE_SPOT) {
                System.out.println("Карлсон в комнате, но прячется. Встреча получилась тайной.");
            } else {
                System.out.println("Встреча почти состоялась, но что-то помешало.");
            }
        } else {
            System.out.println("Вечер прошёл в ожидании, и ничего не случилось.");
        }

        System.out.println();
        System.out.println("Финальные состояния объектов:");
        for (Person p : cast) System.out.println(p);
    }

    private static long parseSeed(String s) {
        try {
            return Long.parseLong(s.trim());
        } catch (Exception e) {
            return 42L;
        }
    }
}
