package story.exceptions;

public class CaughtByFrekenBokException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Фрекен Бок замечает Карлсона!";
    }
}
