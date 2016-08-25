package pl.bazus.changelog.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.log4j.Logger;
import pl.bazus.changelog.domein.Issue;
import pl.bazus.changelog.domein.IssueChangeLog;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangelogGitService {
    private final static Logger LOGGER = Logger.getLogger(ChangelogGitService.class);

    public List<Issue> getAllIssues(String response) {
        Issue issue;
        List<Issue> lista = Lists.newArrayList();
        Set<String> resultSet = Sets.newHashSet();
        String pattern = "(#{1}[0-9]{4,9})";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(response.substring(response.indexOf("OUTPUT:")));

        while (m.find()) {
            issue = new IssueChangeLog(m.group());
            if(resultSet.add(issue.getIssueId())){
                lista.add(issue);
            }

        }
        LOGGER.info("Ilość issue w changeLOG git "+ lista.size());

        return lista;
    }
}
