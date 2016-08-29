package pl.bazus.changelog.domain;

import lombok.Data;

@Data
public class IssueChangeLog extends Issue {

    public IssueChangeLog(String issueId) {
        super(issueId);
    }
}
