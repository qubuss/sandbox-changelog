package pl.bazus.changelog.controller.redmine;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.service.controller.ReadFromRedmine;

import java.util.Map;

@RestController
public class FieldFromAllIssues {
    private final static Logger LOGGER = Logger.getLogger(FieldFromAllIssues.class);


    @Autowired
    private ReadFromRedmine readFromRedmine;

    @RequestMapping(path = "/getFieldFromAllIssues", method = RequestMethod.GET)
    public Map<String, String> getFieldFromAllIssue(@RequestParam(value = "fieldName", defaultValue = "subject") String field) throws Exception {

        Map<String, String> result = readFromRedmine.getFieldFromAllIssue(field);
        return result;
    }

}

