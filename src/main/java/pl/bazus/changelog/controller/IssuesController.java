package pl.bazus.changelog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.domein.Issue;
import pl.bazus.changelog.service.connection.ChangeLogGit;

import java.net.URL;
import java.util.List;

@RestController
public class IssuesController {

    @RequestMapping(path = "/getIssuesFromGit", method = RequestMethod.GET)
    public List<Issue> getIssues(@RequestParam(value = "url", defaultValue = "user") String url) throws Exception {
        ChangeLogGit changeLogGit = new ChangeLogGit();
        List<Issue> lista = changeLogGit.getAllIssues(changeLogGit.connection(new URL(url)));
        return lista;
    }




}
