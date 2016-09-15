package pl.bazus.changelog.service.controller;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bazus.changelog.domain.Issue;
import pl.bazus.changelog.domain.IssueRedmine;
import pl.bazus.changelog.exceptions.BladJSONException;
import pl.bazus.changelog.exceptions.NieIstniejeIssueException;
import pl.bazus.changelog.exceptions.NieMoznaPobracDanychZGitException;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczycException;
import pl.bazus.changelog.properties.ConnectionProperties;
import pl.bazus.changelog.service.JSONServiceImpl;
import pl.bazus.changelog.service.RedmineIssueService;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;

@Service
public class ReadFromRedmineService {
    private final static Logger LOGGER = Logger.getLogger(ReadFromRedmineService.class);

    @Resource
    private ConnectionProperties connectionProperties;

    @Autowired
    private ChangelogGitService changelogGitService;

    @Autowired
    private JSONServiceImpl jsonService;

    @Autowired
    private RedmineIssueService redmineIssueService;

    public List<IssueRedmine> getFieldFromAllIssue(String field) throws IOException, NieMoznaPobracDanychZGitException, NieMoznaSiePolaczycException {
        LOGGER.info("Zaczynam pobierać dane");
        List<Issue> listaGit;
        List<IssueRedmine> listaRedmine = Lists.newArrayList();

        listaGit = changelogGitService.connectAndGetAllIssue(new URL(connectionProperties.getUrlChangelogGit150()));

        for (Issue issue : listaGit) {
            IssueRedmine issueRedmine = new IssueRedmine(issue.getIssueId(),
                    getResponse(field, getUrlStringForIssue(issue))
                    );
            listaRedmine.add(issueRedmine);


        }

        LOGGER.info("Kończe pobierać dane");

        return listaRedmine;

    }

    private String getUrlStringForIssue(Issue issue) {
        return MessageFormat.format("{0}{1}.json", connectionProperties.getUrlRedmine(), issue.getIssueId().substring(1));
    }

    public IssueRedmine getFieldFromIssue(String id, String field) throws NieIstniejeIssueException, IOException {
        LOGGER.info(MessageFormat.format("Pobieram pole: {0} id issue: {1}", field, id));
        String url = MessageFormat.format("{0}{1}.json", connectionProperties.getUrlRedmine(), id);
        String fieldResult = getResponse(field, url);

        return new IssueRedmine(id, fieldResult);
    }

    private String getResponse(String field, String urlRedmine) throws IOException {
        String response;
        String result = null;
        try {
            response = redmineIssueService.connection(new URL(urlRedmine), connectionProperties.getUsername(), connectionProperties.getPassword());
            jsonService.setResponse(response);
            result = jsonService.getFieldFromIssue(field);
        } catch (NieMoznaSiePolaczycException nieMoznaSiePolaczycException) {
            LOGGER.error(nieMoznaSiePolaczycException.getMessage());
        } catch (BladJSONException bladJSONException) {
            LOGGER.error(bladJSONException.getMessage());
        }
        return result;
    }

}
