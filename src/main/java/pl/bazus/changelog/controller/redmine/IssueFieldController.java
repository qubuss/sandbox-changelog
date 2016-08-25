package pl.bazus.changelog.controller.redmine;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.properties.ConnectionProperties;
import pl.bazus.changelog.service.JSONServiceImpl;
import pl.bazus.changelog.service.RedmineIssueService;
import pl.bazus.changelog.service.api.ConnectionsType;

import javax.annotation.Resource;
import java.net.URL;
import java.text.MessageFormat;

@RestController
public class IssueFieldController {
    private final static Logger LOGGER = Logger.getLogger(IssueFieldController.class);

    @Resource
    private ConnectionProperties connectionProperties;

    @RequestMapping(path = "/getFieldFromIssue", method = RequestMethod.GET)
    public String getFieldFromIssue(@RequestParam(value = "idIssue", defaultValue = "") Integer id,
                                    @RequestParam(value = "fieldName", defaultValue = "subject") String field) throws Exception {

        LOGGER.info(MessageFormat.format("Pobieram pole: {0} id issue: {1}", field, id));

        String url = connectionProperties.getUrlRedmine() + id + ".json";
        RedmineIssueService redmineIssueService = new RedmineIssueService(ConnectionsType.UNIREST);
        String issue = redmineIssueService.connection(new URL(url));
        JSONServiceImpl jsonService = new JSONServiceImpl();
        jsonService.setResponse(issue);
        String fieldResult = jsonService.getFieldFromIssue(field);

        return MessageFormat.format("#{0} {1}: {2}", id, field, fieldResult);
    }
}
