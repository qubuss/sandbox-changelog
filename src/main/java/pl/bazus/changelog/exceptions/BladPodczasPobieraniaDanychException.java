package pl.bazus.changelog.exceptions;

public class BladPodczasPobieraniaDanychException extends Exception {


    @Override
    public String getMessage() {
        return "Błąd podczas pobierania danych";
    }

}
