package story.exceptions;

public class WaitingTooLongException extends Exception {
    private final int steps;

    public WaitingTooLongException(int steps) {
        this.steps = steps;
    }

    @Override
    public String getMessage() {
        return "Карлсон слишком долго ждёт на крыше! Шагов ожидания: " + steps;
    }
}
