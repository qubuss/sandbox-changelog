package pl.bazus.changelog.exceptions;

public class NieMoznaPobracDanychZGit extends Exception {
    @Override
    public String getMessage() {
        return "Błąd podczas pobierania danych z changelog Git";
    }
}
