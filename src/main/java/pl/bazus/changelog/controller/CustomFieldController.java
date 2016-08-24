package pl.bazus.changelog.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;
import pl.bazus.changelog.domein.Issue;
import pl.bazus.changelog.service.JSONServiceImpl;
import pl.bazus.changelog.service.RedmineIssueService;
import pl.bazus.changelog.service.api.ConnectionsType;
import pl.bazus.changelog.service.connection.ChangeLogGit;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class CustomFieldController {

    private final static Logger LOGGER = Logger.getLogger(CustomFieldController.class);


    @RequestMapping(path = "/getFieldFromIssue", method = RequestMethod.GET)
    public String getFieldFromIssue(@RequestParam(value = "idIssue" , defaultValue = "") Integer id,
                            @RequestParam(value = "fieldName", defaultValue = "subject")  String field ) throws Exception {

        String url = "http://serwis.bazus.pl/issues/"+id+".json";

        RedmineIssueService redmineIssueService = new RedmineIssueService(url, ConnectionsType.HTTPCONNECTION);
        String issue = redmineIssueService.getIssueFromRedmine();
        JSONServiceImpl jsonService = new JSONServiceImpl();
        jsonService.setResponse(issue);
        String fieldResult = jsonService.getFieldFromIssue(field);

        return id +" "+fieldResult;
    }

    @RequestMapping(path = "/getIssuesField", method = RequestMethod.GET)
    public ModelAndView getFieldFromAllIssue(@RequestParam(value = "fieldName", defaultValue = "subject")  String field,
                                             @RequestParam(value = "gitURL", defaultValue = "http://git.bazus.pl:8100/?repo=projekty/bazus.git&count=10", required = false) String url) throws Exception {

        //TODO url z '' 150 do git &'gitURL=http://git.bazus.pl:8100/?repo=projekty/bazus.git&count=150'
        LOGGER.info("Zaczynam pobierać dane");
        ChangeLogGit changeLogGit = new ChangeLogGit();
        List<Issue> lista = changeLogGit.getAllIssues(changeLogGit.connection(new URL(url)));
        System.out.println(lista.size());
        Map<String, String> resultMAP = new HashMap<>();

        String urlRedmine;
        RedmineIssueService redmineIssueService;
        JSONServiceImpl jsonService = new JSONServiceImpl();

        for(Issue issue: lista){
            urlRedmine = "http://serwis.bazus.pl/issues/"+issue.getIssueId()+".json";
            redmineIssueService = new RedmineIssueService(urlRedmine, ConnectionsType.UNIREST);
            String result = redmineIssueService.getIssueFromRedmine();
            jsonService.setResponse(result);
            String filedResult = jsonService.getFieldFromIssue(field);

            resultMAP.put(issue.getIssueId(), filedResult);

        }

        LOGGER.info("Kończe pobierać dane");
        return new ModelAndView("issues", resultMAP);
    }






}
