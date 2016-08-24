package pl.bazus.changelog.service;



import org.apache.log4j.Logger;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;
import pl.bazus.changelog.service.api.ConnectionsType;
import pl.bazus.changelog.service.connection.ApacheHttpClientConnectionMethod;
import pl.bazus.changelog.service.connection.HttpConnectionMethod;
import pl.bazus.changelog.service.connection.RestTemplateConnectionMethod;
import pl.bazus.changelog.service.connection.UniRestConnectionMethod;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class RedmineIssueService {
    private final static Logger LOGGER = Logger.getLogger(RedmineIssueService.class);
    private URL url;
    private ConnectionsType connectionsType;


    public RedmineIssueService(String url, ConnectionsType connectionsType) throws MalformedURLException {
        this.url = new URL(url);
        this.connectionsType = connectionsType;
    }

    public String getIssueFromRedmine(){
        String response = null;

        try {
            switch (connectionsType){
                case HTTPCONNECTION:
                    response = new HttpConnectionMethod().connection(url);
                    break;
                case APACHEHTTPCLIENT:
                    response = new ApacheHttpClientConnectionMethod().connection(url);
                    break;
                case RESTTAMPLATE:
                    response = new RestTemplateConnectionMethod().connection(url);
                    break;
                case UNIREST:
                    response = new UniRestConnectionMethod().connection(url);
                    break;
                default:
                    return new NieMoznaSiePolaczyc().getMessage();
            }

        } catch (NieMoznaSiePolaczyc nieMoznaSiePolaczyc) {
            nieMoznaSiePolaczyc.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }
}
