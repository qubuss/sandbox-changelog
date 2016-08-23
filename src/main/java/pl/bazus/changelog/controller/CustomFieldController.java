package pl.bazus.changelog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.domein.Issue;
import pl.bazus.changelog.domein.IssueRedmine;
import pl.bazus.changelog.service.RedmineIssueService;
import pl.bazus.changelog.service.connection.ChangeLogGit;

import java.net.URL;
import java.util.List;

@RestController
public class CustomFieldController {


    @RequestMapping(path = "/getIssueInfo", method = RequestMethod.GET)
    public String getIssues(@RequestParam(value = "idIssue", defaultValue = "") Integer id) throws Exception {

        String url = "http://serwis.bazus.pl/issues/"+id+".json";

        RedmineIssueService redmineIssueService = new RedmineIssueService(url);
        String issue = redmineIssueService.getIssueFromRedmine();
        return issue;
    }

}
