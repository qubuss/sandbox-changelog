package pl.bazus.changelog.exceptions;

public class NieMoznaPobracDanychZGitException extends Exception {
    @Override
    public String getMessage() {
        return "Błąd podczas pobierania danych z changelog Git";
    }
}
