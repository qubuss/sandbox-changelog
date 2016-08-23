package pl.bazus.changelog.service;



import org.apache.log4j.Logger;
import pl.bazus.changelog.domein.Issue;
import pl.bazus.changelog.domein.IssueRedmine;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;
import pl.bazus.changelog.service.connection.HttpConnectionMethod;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class RedmineIssueService {
    private final static Logger LOGGER = Logger.getLogger(RedmineIssueService.class);
    URL url;

    public RedmineIssueService(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public String getIssueFromRedmine(){
        Issue issue;
        String response = null;

        try {
            response = new HttpConnectionMethod().connection(url);
        } catch (NieMoznaSiePolaczyc nieMoznaSiePolaczyc) {
            nieMoznaSiePolaczyc.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }
}
