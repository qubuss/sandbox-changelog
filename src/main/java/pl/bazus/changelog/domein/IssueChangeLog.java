package pl.bazus.changelog.domein;

import lombok.Data;

@Data
public class IssueChangeLog extends Issue {

    public IssueChangeLog(String issueId) {
        super(issueId);
    }
}
