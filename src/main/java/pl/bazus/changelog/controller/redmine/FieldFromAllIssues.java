package pl.bazus.changelog.controller.redmine;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.domein.Issue;
import pl.bazus.changelog.properties.ConnectionProperties;
import pl.bazus.changelog.service.JSONServiceImpl;
import pl.bazus.changelog.service.RedmineIssueService;
import pl.bazus.changelog.service.api.ConnectionsType;
import pl.bazus.changelog.service.connection.ChangeLogGit;
import pl.bazus.changelog.service.ChangelogGitService;

import javax.annotation.Resource;
import java.net.URL;
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
        List<Issue> lista = new ChangelogGitService().getAllIssues(responseGit);
        Map<String, String> resultMAP = new HashMap<>();

        String urlRedmine;
        RedmineIssueService redmineIssueService;
        JSONServiceImpl jsonService = new JSONServiceImpl();

        for (Issue issue : lista) {
            urlRedmine = connectionProperties.getUrlRedmine() + issue.getIssueId() + ".json";
            LOGGER.info(urlRedmine);
            redmineIssueService = new RedmineIssueService(ConnectionsType.UNIREST);
            String response = redmineIssueService.connection(new URL(urlRedmine));
            jsonService.setResponse(response);
            String filedResult = jsonService.getFieldFromIssue(field);
            resultMAP.put(issue.getIssueId(), filedResult);

        }

        LOGGER.info("Kończe pobierać dane");

        return resultMAP;
    }

}

