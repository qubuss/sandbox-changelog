package pl.bazus.changelog.exceptions;

public class NieMoznaSiePolaczyc extends Exception {
    public NieMoznaSiePolaczyc() {
        super();
    }

    public NieMoznaSiePolaczyc(String message) {
        super(message);
    }

    public NieMoznaSiePolaczyc(String message, Throwable cause) {
        super(message, cause);
    }

    public NieMoznaSiePolaczyc(Throwable cause) {
        super(cause);
    }

    protected NieMoznaSiePolaczyc(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return "Błąd z połączeniem";
    }
}
