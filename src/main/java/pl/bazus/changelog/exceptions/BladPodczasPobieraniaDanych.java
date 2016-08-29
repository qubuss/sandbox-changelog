package pl.bazus.changelog.exceptions;

public class BladPodczasPobieraniaDanych extends Exception {
    @Override
    public String getMessage() {
        return "Błąd podczas pobierania danych";
    }
}
