package pl.bazus.changelog.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.domain.Issue;
import pl.bazus.changelog.exceptions.NieMoznaPobracDanychZGitException;
import pl.bazus.changelog.properties.ConnectionProperties;
import pl.bazus.changelog.service.controller.ChangelogGitService;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;

@RestController
public class GitController {
    private final static Logger LOGGER = Logger.getLogger(GitController.class);

    @Resource
    private ConnectionProperties connectionProperties;

    @Autowired
    private ChangelogGitService changelogGitService;

    @RequestMapping(path = "/git/getIssuesIDFromGit", method = RequestMethod.GET, produces = "application/json")
    public List<Issue> getIssues(@RequestParam(value = "count", defaultValue = "150") String count) throws NieMoznaPobracDanychZGitException, MalformedURLException {
        LOGGER.info("ChangelogGit: " + connectionProperties.getUrlChangelogGit() + "&count=" + count);
        LOGGER.info("Details "+SecurityContextHolder.getContext().getAuthentication().getDetails());
        LOGGER.info("Principal "+SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        LOGGER.info("Authorities "+SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        LOGGER.info("Credentials "+SecurityContextHolder.getContext().getAuthentication().getCredentials());
        return changelogGitService.connectAndGetAllIssue(new URL(MessageFormat.format("{0}&count={1}", connectionProperties.getUrlChangelogGit(), count)));

    }

}
