package pl.bazus.changelog.controller.redmine;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.domain.IssueRedmine;
import pl.bazus.changelog.exceptions.BladPodczasPobieraniaDanych;
import pl.bazus.changelog.service.controller.ReadFromRedmine;

@RestController
public class IssueFieldController {
    private final static Logger LOGGER = Logger.getLogger(IssueFieldController.class);

    @Autowired
    private ReadFromRedmine readFromRedmine;

    @RequestMapping(path = "/getFieldFromIssue", method = RequestMethod.GET)
    public IssueRedmine getFieldFromIssue(@RequestParam(value = "idIssue", defaultValue = "") String id,
                                    @RequestParam(value = "fieldName", defaultValue = "subject") String field) throws BladPodczasPobieraniaDanych {

        IssueRedmine result;
        try {
            result = readFromRedmine.getFieldFromIssue(id, field);
        } catch (Exception e) {
            throw new BladPodczasPobieraniaDanych();
        }

        return result;
    }
}
