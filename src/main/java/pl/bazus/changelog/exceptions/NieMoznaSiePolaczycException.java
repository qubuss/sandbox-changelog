package pl.bazus.changelog.exceptions;

import pl.bazus.changelog.bo.exceptions.ChangelogSupplierException;

public class NieMoznaSiePolaczycException extends ChangelogSupplierException {

    public NieMoznaSiePolaczycException() {
        super("Błąd z połączeniem");
    }

}

