package pl.bazus.changelog.domein;

import lombok.Data;


public class IssueRedmine extends Issue {

    String subject;

    public IssueRedmine(String issueId) {
        super(issueId);

    }

    public IssueRedmine(String issueId, String subject) {
        super(issueId);
        this.subject = subject;
    }

}
