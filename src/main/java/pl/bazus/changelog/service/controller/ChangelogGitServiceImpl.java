package pl.bazus.changelog.service.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bazus.changelog.domain.Issue;
import pl.bazus.changelog.domain.IssueChangeLog;
import pl.bazus.changelog.exceptions.NieMoznaPobracDanychZGitException;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczycException;
import pl.bazus.changelog.service.connection.ChangeLogGit;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ChangelogGitServiceImpl implements ChangelogGitService {
    private final static Logger LOGGER = Logger.getLogger(ChangelogGitServiceImpl.class);

    private ChangeLogGit changeLogGit;

    @Autowired
    public ChangelogGitServiceImpl(ChangeLogGit changeLogGit) {
        this.changeLogGit = changeLogGit;
    }

    private static Pattern regexPattern;

    @Override
    public List<Issue> connectAndGetAllIssue(URL url) throws NieMoznaPobracDanychZGitException, NieMoznaSiePolaczycException, IOException {
        return getAllIssues(getResponseFromGit(url));
    }

    private String getResponseFromGit(URL url) throws NieMoznaSiePolaczycException, IOException {
        return changeLogGit.connection(url);
    }

    private List<Issue> getAllIssues(String response) throws NieMoznaPobracDanychZGitException {
        Issue issue;
        List<Issue> lista = Lists.newArrayList();
        Set<String> resultSet = Sets.newHashSet();
        String pattern = "(#{1}[0-9]{1,9})";
        regexPattern = Pattern.compile(pattern);
        Matcher m = regexPattern.matcher(response.substring(response.indexOf("OUTPUT:")));

        while (m.find()) {
            issue = new IssueChangeLog(m.group());
            if (resultSet.add(issue.getIssueId())) {
                lista.add(issue);
            }

        }
        LOGGER.info("Ilość issue w changeLOG git " + lista.size());

        return lista;
    }
}
