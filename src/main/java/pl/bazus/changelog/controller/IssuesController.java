package pl.bazus.changelog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.service.Testowe;

import java.util.List;

@RestController
public class IssuesController {

    @RequestMapping(path = "/getIssues", method = RequestMethod.GET)
    public List<String> getIssues(@RequestParam(value = "url", defaultValue = "user") String url) throws Exception {
        List<String> lista = new Testowe().HttpConn(url);
        return lista;
    }


}
