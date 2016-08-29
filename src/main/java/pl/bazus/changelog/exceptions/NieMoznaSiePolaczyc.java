package pl.bazus.changelog.exceptions;

public class NieMoznaSiePolaczyc extends Exception {
    public NieMoznaSiePolaczyc() {
        super();
    }

    @Override
    public String getMessage() {
        return "Błąd z połączeniem";
    }
}
