package pl.bazus.changelog.exceptions;

import java.text.MessageFormat;

public class NieIstniejeIssue extends Exception {
    @Override
    public String getMessage() {
        return "Issue o podanym id nie istnieje";
    }
}
