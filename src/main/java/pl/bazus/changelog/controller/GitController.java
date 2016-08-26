package pl.bazus.changelog.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.domein.Issue;
import pl.bazus.changelog.properties.ConnectionProperties;
import pl.bazus.changelog.service.ChangelogGitService;
import pl.bazus.changelog.service.connection.ChangeLogGit;

import javax.annotation.Resource;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;

@RestController
public class GitController {
    private final static Logger LOGGER = Logger.getLogger(GitController.class);

    @Resource
    private ConnectionProperties connectionProperties;

    @RequestMapping(path = "/getIssuesIDFromGit", method = RequestMethod.GET)
    public List<Issue> getIssues(@RequestParam(value = "count", defaultValue = "150") String count) throws Exception {
        String response = new ChangeLogGit().connection(new URL(MessageFormat.format("{0}&count={1}", connectionProperties.getUrlChangelogGit(), count)));
        LOGGER.info("ChangelogGit: "+ connectionProperties.getUrlChangelogGit()+"&count="+count);
        List<Issue> lista = new ChangelogGitService().getAllIssues(response);
        return lista;
    }

}
