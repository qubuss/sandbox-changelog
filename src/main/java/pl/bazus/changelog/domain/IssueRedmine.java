package pl.bazus.changelog.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.log4j.Logger;

@Data
@EqualsAndHashCode(callSuper = true)
public class IssueRedmine extends Issue {
    private final static Logger LOGGER = Logger.getLogger(IssueRedmine.class);

    private String subject;

    public IssueRedmine(String issueId, String subject) {
        super(issueId);
        this.subject = subject;
    }
}
