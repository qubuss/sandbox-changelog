package pl.bazus.changelog.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IssueChangeLog extends Issue {

    public IssueChangeLog(String issueId) {
        super(issueId);
    }
}
