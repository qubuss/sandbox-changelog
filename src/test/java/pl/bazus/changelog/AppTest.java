package pl.bazus.changelog;


import org.junit.Ignore;
import org.junit.Test;
import pl.bazus.changelog.service.JSONServiceImpl;
import pl.bazus.changelog.service.connection.*;

import java.net.MalformedURLException;
import java.net.URL;


public class AppTest {
    URL url = new URL("http://serwis.bazus.pl/issues.json");

    public AppTest() throws MalformedURLException {
    }

    @Test
    public void testujHttpConn() throws Exception {
        new HttpConnectionMethod().connection(url);

    }


    @Test
    public void testujApache() throws Exception {
        new ApacheHttpClientConnectionMethod().connection(url);

    }

@Ignore
    @Test
    public void testujRest() throws Exception {
        new RestTemplateConnectionMethod().connection(url);
    }

    @Ignore
    @Test
    public void testujUni() throws Exception {
       String result =  new UniRestConnectionMethod().connection(url);
        JSONServiceImpl jsonService = new JSONServiceImpl();
        System.out.println(jsonService.getALLIssues(result));

    }


    @Test
    public void test() throws Exception {
        String url = "http://git.bazus.pl:8100/?repo=projekty/bazus.git&count=10";
        ChangeLogGit changeLogGit = new ChangeLogGit();
        String response = changeLogGit.connection(new URL(url));
        changeLogGit.getAllIssues(response).stream().forEach(issue -> System.out.println(issue.getIssueId()));

    }

}
