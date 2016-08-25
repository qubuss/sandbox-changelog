package pl.bazus.changelog.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.domein.Issue;
import pl.bazus.changelog.properties.ConnectionProperties;
import pl.bazus.changelog.service.connection.ChangeLogGit;

import javax.annotation.Resource;
import java.net.URL;
import java.util.List;

@RestController
public class GitController {
    private final static Logger LOGGER = Logger.getLogger(GitController.class);

    @Resource
    private ConnectionProperties connectionProperties;

    @RequestMapping(path = "/getIssuesIDFromGit", method = RequestMethod.GET)
    public List<Issue> getIssues(@RequestParam(value = "count", defaultValue = "150") String count) throws Exception {
        ChangeLogGit changeLogGit = new ChangeLogGit();
        LOGGER.info("ChangelogGit: "+ connectionProperties.getUrlChangelogGit()+"&count="+count);
        List<Issue> lista = changeLogGit.getAllIssues(changeLogGit.connection(new URL(connectionProperties.getUrlChangelogGit()+"&count="+count)));
        return lista;
    }




}
