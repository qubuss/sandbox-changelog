package pl.bazus.changelog;

import org.hibernate.validator.constraints.URL;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import pl.bazus.changelog.service.RedmineIssueService;
import pl.bazus.changelog.service.Testowe;


public class AppTest {

    @Test
    public void testujHttpConn() throws Exception {
        RedmineIssueService redmineIssueService = new RedmineIssueService(32439);
        redmineIssueService.HttpConn();

    }

    @Ignore
    public void testujApache() throws Exception {
        RedmineIssueService redmineIssueService = new RedmineIssueService(2);
        redmineIssueService.ApacheHttpClient();

    }

    @Ignore
    @Test
    public void testujRest() throws Exception {
        RedmineIssueService redmineIssueService = new RedmineIssueService(2);
        redmineIssueService.RestTemplate();
    }

    @Ignore
    @Test
    public void testujUni() throws Exception {
        RedmineIssueService redmineIssueService = new RedmineIssueService(2);
        redmineIssueService.Unirest();
    }

    @Test
    public void test() throws Exception {
        String url = "http://git.bazus.pl:8100/?repo=projekty/bazus.git&count=150";
        new Testowe().HttpConn(url);
    }
}
