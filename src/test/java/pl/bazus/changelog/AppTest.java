package pl.bazus.changelog;


import org.junit.Ignore;
import org.junit.Test;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczycException;
import pl.bazus.changelog.properties.ConnectionProperties;
import pl.bazus.changelog.service.controller.ChangelogGitServiceImpl;
import pl.bazus.changelog.service.JSONServiceImpl;
import pl.bazus.changelog.service.connection.*;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;

//@SpringBootTest
//@RunWith(SpringRunner.class)

public class AppTest {
    URL url = new URL("http://10.0.10.31/issues.json");

    @Resource
    private ConnectionProperties connectionProperties;

    public AppTest() throws MalformedURLException {
    }

    @Test
    public void testujHttpConn() throws Exception {
        new HttpConnectionMethod().connection(url, "jakub.fryga", "daniel.12");

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


    @Test
    public void testujUni() throws Exception {
        String result = new UniRestConnectionMethod().connection(url);
        JSONServiceImpl jsonService = new JSONServiceImpl();
        System.out.println(jsonService.getALLIssues(result));

    }


    @Test
    public void test() throws Exception {
        String url = "http://git.bazus.pl:8100/?repo=projekty/bazus.git&count=10";
        ChangeLogGit changeLogGit = new ChangeLogGit();
        new ChangelogGitServiceImpl().connectAndGetAllIssue(new URL(url)).forEach(issue -> System.out.println(issue.getIssueId()));

    }

    @Test
    public void testException() throws NieMoznaSiePolaczycException {
        throw new NieMoznaSiePolaczycException();
    }

}
