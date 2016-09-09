package pl.bazus.changelog.exceptions;

public class NieMoznaSiePolaczycException extends Exception {
    public NieMoznaSiePolaczycException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Błąd z połączeniem";
    }
}
