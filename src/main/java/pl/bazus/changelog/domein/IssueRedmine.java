package pl.bazus.changelog.domein;

import lombok.Data;

@Data
public class IssueRedmine extends Issue {
    String subject;

    public IssueRedmine(String issueId) {
        super(issueId);

    }



}
