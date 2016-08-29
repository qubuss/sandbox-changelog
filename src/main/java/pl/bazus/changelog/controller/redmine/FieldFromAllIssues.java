package pl.bazus.changelog.controller.redmine;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.domein.Issue;
import pl.bazus.changelog.domein.IssueRedmine;
import pl.bazus.changelog.properties.ConnectionProperties;
import pl.bazus.changelog.service.JSONServiceImpl;
import pl.bazus.changelog.service.RedmineIssueService;
import pl.bazus.changelog.service.api.ConnectionsType;
import pl.bazus.changelog.service.connection.ChangeLogGit;
import pl.bazus.changelog.service.ChangelogGitService;

import javax.annotation.Resource;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FieldFromAllIssues {
    private final static Logger LOGGER = Logger.getLogger(FieldFromAllIssues.class);

    @Resource
    private ConnectionProperties connectionProperties;

    @RequestMapping(path = "/getFieldFromAllIssues", method = RequestMethod.GET)
    public Map<String, String> getFieldFromAllIssue(@RequestParam(value = "fieldName", defaultValue = "subject") String field) throws Exception {

        LOGGER.info("Zaczynam pobierać dane");
        String responseGit = new ChangeLogGit().connection(new URL(connectionProperties.getUrlChangelogGit150()));
        List<Issue> listaGit = new ChangelogGitService().getAllIssues(responseGit);
        Map<String, String> resultMAP = new HashMap<>();
        List<Issue> listaRedmine = Lists.newArrayList();

        String urlRedmine;
        RedmineIssueService redmineIssueService;
        JSONServiceImpl jsonService = new JSONServiceImpl();

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

}

