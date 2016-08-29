package pl.bazus.changelog.domain;

import lombok.Data;

@Data
public abstract class Issue {
    String issueId;

    public Issue(String issueId) {
        this.issueId = issueId;
    }

    public String getIssueId() {
        return issueId.substring(1);
    }

}