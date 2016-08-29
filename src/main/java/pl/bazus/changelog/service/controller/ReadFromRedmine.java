package pl.bazus.changelog.service.controller;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.bazus.changelog.domain.Issue;
import pl.bazus.changelog.domain.IssueRedmine;
import pl.bazus.changelog.exceptions.BladJSON;
import pl.bazus.changelog.exceptions.NieMoznaPobracDanychZGit;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;
import pl.bazus.changelog.properties.ConnectionProperties;
import pl.bazus.changelog.service.ChangelogGitService;
import pl.bazus.changelog.service.JSONServiceImpl;
import pl.bazus.changelog.service.RedmineIssueService;
import pl.bazus.changelog.service.api.ConnectionsType;
import pl.bazus.changelog.service.connection.ChangeLogGit;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReadFromRedmine {
    private final static Logger LOGGER = Logger.getLogger(ReadFromRedmine.class);

    @Resource
    private ConnectionProperties connectionProperties;

    private JSONServiceImpl jsonService;
    private RedmineIssueService redmineIssueService;

    public ReadFromRedmine() {
        jsonService = new JSONServiceImpl();
        redmineIssueService = new RedmineIssueService(ConnectionsType.UNIREST);
    }

    public Map<String, String> getFieldFromAllIssue(String field) throws Exception {
        LOGGER.info("Zaczynam pobierać dane");
        List<Issue> listaGit = null;
        String urlRedmine;
        String filedResult;
        Map<String, String> resultMAP = new HashMap<>();
        List<Issue> listaRedmine = Lists.newArrayList();


        try {
            String responseGit = new ChangeLogGit().connection(new URL(connectionProperties.getUrlChangelogGit150()));
            listaGit = new ChangelogGitService().getAllIssues(responseGit);
        } catch (NieMoznaSiePolaczyc nieMoznaSiePolaczyc) {
            nieMoznaSiePolaczyc.getMessage();
        } catch (NieMoznaPobracDanychZGit nieMoznaPobracDanychZGit) {
            nieMoznaPobracDanychZGit.getMessage();
        }


        for (Issue issue : listaGit) {
            urlRedmine = MessageFormat.format("{0}{1}.json", connectionProperties.getUrlRedmine(), issue.getIssueId());
            filedResult = getResponse(field, urlRedmine);

            resultMAP.put(issue.getIssueId(), filedResult);
            Issue issueRedmine = new IssueRedmine(issue.getIssueId(), filedResult);
            listaRedmine.add(issueRedmine);

        }

        LOGGER.info("Kończe pobierać dane");

        return resultMAP;

    }

    public String getFieldFromIssue(String id, String field) throws Exception {
        LOGGER.info(MessageFormat.format("Pobieram pole: {0} id issue: {1}", field, id));
        String url = MessageFormat.format("{0}{1}.json", connectionProperties.getUrlRedmine(), id);
        String fieldResult = getResponse(field, url);

        return MessageFormat.format("#{0} {1}: {2}", id, field, fieldResult);
    }

    private String getResponse(String field, String urlRedmine) throws IOException {
        String response;
        String result = null;
        try {
            response = redmineIssueService.connection(new URL(urlRedmine), connectionProperties.getUsername(), connectionProperties.getPassword());
            jsonService.setResponse(response);
            result = jsonService.getFieldFromIssue(field);
        } catch (NieMoznaSiePolaczyc nieMoznaSiePolaczyc) {
            nieMoznaSiePolaczyc.getMessage();
        } catch (BladJSON bladJSON) {
            bladJSON.getMessage();
        }
        return result;
    }

}
