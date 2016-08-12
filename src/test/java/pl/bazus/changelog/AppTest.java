package pl.bazus.changelog;

import org.junit.Before;
import org.junit.Test;
import pl.bazus.changelog.Service.RedmineIssueService;


public class AppTest
{
   @Test
    public void testuj() throws Exception{
       RedmineIssueService redmineIssueService = new RedmineIssueService(2);
//       redmineIssueService.HttpConn();
//       redmineIssueService.ApacheHttpClient();
//       redmineIssueService.RestTemplate();
       redmineIssueService.Unirest();
    }
}
