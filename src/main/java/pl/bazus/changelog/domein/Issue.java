package pl.bazus.changelog.domein;

import lombok.Data;

@Data
public abstract class Issue {
    String issueId;

    public Issue(String issueId) {
        this.issueId = issueId;
    }

}
