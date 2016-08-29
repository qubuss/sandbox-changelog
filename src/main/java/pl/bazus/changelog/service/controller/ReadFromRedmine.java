package pl.bazus.changelog.service.controller;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.bazus.changelog.domain.Issue;
import pl.bazus.changelog.domain.IssueRedmine;
import pl.bazus.changelog.exceptions.BladPodczasPobieraniaDanych;
import pl.bazus.changelog.exceptions.NieIstniejeIssue;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;
import pl.bazus.changelog.properties.ConnectionProperties;
import pl.bazus.changelog.service.ChangelogGitService;
import pl.bazus.changelog.service.JSONServiceImpl;
import pl.bazus.changelog.service.RedmineIssueService;
import pl.bazus.changelog.service.api.ConnectionsType;
import pl.bazus.changelog.service.connection.ChangeLogGit;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
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

    public Map<String, String> getFieldFromAllIssue(String field) throws Exception {
        LOGGER.info("Zaczynam pobierać dane");
        String responseGit = null;
        List<Issue> listaGit = null;
        Map<String, String> resultMAP = new HashMap<>();
        List<Issue> listaRedmine = Lists.newArrayList();
        String urlRedmine;
        RedmineIssueService redmineIssueService;
        JSONServiceImpl jsonService = new JSONServiceImpl();

        try {
            responseGit = new ChangeLogGit().connection(new URL(connectionProperties.getUrlChangelogGit150()));
        } catch (NieMoznaSiePolaczyc nieMoznaSiePolaczyc) {
            nieMoznaSiePolaczyc.getMessage();
        }

        try {
            listaGit = new ChangelogGitService().getAllIssues(responseGit);
        } catch (Exception e) {
            throw new BladPodczasPobieraniaDanych();
        }

        for (Issue issue : listaGit) {
            urlRedmine = MessageFormat.format("{0}{1}.json", connectionProperties.getUrlRedmine(), issue.getIssueId());
            LOGGER.info(urlRedmine);
            redmineIssueService = new RedmineIssueService(ConnectionsType.UNIREST);
            String response = redmineIssueService.connection(new URL(urlRedmine), connectionProperties.getUsername(), connectionProperties.getPassword());
            jsonService.setResponse(response);
            String filedResult = jsonService.getFieldFromIssue(field);
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
        RedmineIssueService redmineIssueService = new RedmineIssueService(ConnectionsType.UNIREST);
        String issue = redmineIssueService.connection(new URL(url), connectionProperties.getUsername(), connectionProperties.getPassword());
        JSONServiceImpl jsonService = new JSONServiceImpl();
        jsonService.setResponse(issue);
        String fieldResult = jsonService.getFieldFromIssue(field);

        return MessageFormat.format("#{0} {1}: {2}", id, field, fieldResult);
    }
}
