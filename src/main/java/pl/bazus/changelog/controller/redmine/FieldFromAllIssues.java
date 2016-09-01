package pl.bazus.changelog.controller.redmine;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.domain.Issue;
import pl.bazus.changelog.domain.IssueRedmine;
import pl.bazus.changelog.exceptions.BladPodczasPobieraniaDanych;
import pl.bazus.changelog.exceptions.NieMoznaPobracDanychZGit;
import pl.bazus.changelog.service.controller.ReadFromRedmine;

import java.util.List;
import java.util.Map;

@RestController
public class FieldFromAllIssues {
    private final static Logger LOGGER = Logger.getLogger(FieldFromAllIssues.class);


    @Autowired
    private ReadFromRedmine readFromRedmine;

    @RequestMapping(path = "/getFieldFromAllIssues", method = RequestMethod.GET, produces = "application/json")
    public List<IssueRedmine> getFieldFromAllIssue(@RequestParam(value = "fieldName", defaultValue = "subject") String field) throws NieMoznaPobracDanychZGit, BladPodczasPobieraniaDanych {

        List<IssueRedmine> result = Lists.newArrayList();
        try {
            result = readFromRedmine.getFieldFromAllIssue(field);
        } catch (Exception e) {
            e.getMessage();
        }

        result.forEach(issue -> System.out.println(issue.getIssueId()+" "+issue.getSubject()));
        return result;
    }

}

