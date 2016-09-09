package pl.bazus.changelog.exceptions;

public class NieIstniejeIssueException extends Exception {
    @Override
    public String getMessage() {
        return "Issue o podanym id nie istnieje";
    }
}
