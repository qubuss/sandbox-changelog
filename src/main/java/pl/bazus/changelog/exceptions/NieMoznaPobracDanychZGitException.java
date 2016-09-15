package pl.bazus.changelog.exceptions;

import pl.bazus.changelog.bo.exceptions.ChangelogSupplierException;

public class NieMoznaPobracDanychZGitException extends ChangelogSupplierException {

    public NieMoznaPobracDanychZGitException() {
        super("Błąd podczas pobierania danych z changelog Git");
    }

}
