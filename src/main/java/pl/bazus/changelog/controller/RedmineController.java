package pl.bazus.changelog.controller;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.domain.IssueRedmine;
import pl.bazus.changelog.exceptions.BladPodczasPobieraniaDanychException;
import pl.bazus.changelog.exceptions.NieIstniejeIssueException;
import pl.bazus.changelog.exceptions.NieMoznaPobracDanychZGitException;
import pl.bazus.changelog.service.controller.ReadFromRedmineService;

import java.io.IOException;
import java.util.List;


@RestController

public class RedmineController {
    private final static Logger LOGGER = Logger.getLogger(RedmineController.class);

    @Autowired
    private ReadFromRedmineService readFromRedmineService;


    @RequestMapping(path = "/redmine/getFieldFromAllIssues", method = RequestMethod.GET, produces = "application/json")
    public List<IssueRedmine> getFieldFromAllIssue(@RequestParam(value = "fieldName", defaultValue = "subject") String field) throws NieMoznaPobracDanychZGitException, BladPodczasPobieraniaDanychException {

        List<IssueRedmine> result = Lists.newArrayList();
        try {
            result = readFromRedmineService.getFieldFromAllIssue(field);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        result.forEach(issue -> LOGGER.info(issue.getIssueId()+" "+issue.getSubject()));
        return result;
    }

    @RequestMapping(path = "/redmine/getFieldFromIssue", method = RequestMethod.GET)
    public IssueRedmine getFieldFromIssue(@RequestParam(value = "idIssue", defaultValue = "") String id,
                                          @RequestParam(value = "fieldName", defaultValue = "subject") String field)  {

        IssueRedmine result = null;
        try {
            result = readFromRedmineService.getFieldFromIssue(id, field);
        } catch (NieIstniejeIssueException e) {
            LOGGER.error(id+" "+e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info(result);
        return result;
    }
}
