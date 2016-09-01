package pl.bazus.changelog.domain;


import org.apache.log4j.Logger;

public class IssueRedmine extends Issue {
    private final static Logger LOGGER = Logger.getLogger(IssueRedmine.class);

    private String subject;

    public IssueRedmine(String issueId) {
        super(issueId);

    }

    public IssueRedmine(String issueId, String subject) {
        super(issueId);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
