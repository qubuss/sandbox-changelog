package pl.bazus.changelog.service;



import org.apache.log4j.Logger;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;
import pl.bazus.changelog.service.api.Connection;
import pl.bazus.changelog.service.api.ConnectionsType;
import pl.bazus.changelog.service.connection.ApacheHttpClientConnectionMethod;
import pl.bazus.changelog.service.connection.HttpConnectionMethod;
import pl.bazus.changelog.service.connection.RestTemplateConnectionMethod;
import pl.bazus.changelog.service.connection.UniRestConnectionMethod;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;


public class RedmineIssueService implements Connection{
    private final static Logger LOGGER = Logger.getLogger(RedmineIssueService.class);
    private ConnectionsType connectionsType;


    public RedmineIssueService(ConnectionsType connectionsType) throws MalformedURLException {
        this.connectionsType = connectionsType;
    }

    @Override
    public String connection(URL url) throws NieMoznaSiePolaczyc, IOException {
        String response = null;

        try {
            switch (connectionsType){
                case HTTPCONNECTION:
                    LOGGER.info(MessageFormat.format("Łączę przez {0}", connectionsType));
                    response = new HttpConnectionMethod().connection(url);
                    break;
                case APACHEHTTPCLIENT:
                    LOGGER.info(MessageFormat.format("Łączę przez {0}", connectionsType));
                    response = new ApacheHttpClientConnectionMethod().connection(url);
                    break;
                case RESTTAMPLATE:
                    LOGGER.info(MessageFormat.format("Łączę przez {0}", connectionsType));
                    response = new RestTemplateConnectionMethod().connection(url);
                    break;
                case UNIREST:
                    LOGGER.info(MessageFormat.format("Łączę przez {0}", connectionsType));
                    response = new UniRestConnectionMethod().connection(url);
                    break;
                default:
                    return new NieMoznaSiePolaczyc().getMessage();
            }

        } catch (NieMoznaSiePolaczyc | IOException nieMoznaSiePolaczyc) {
            nieMoznaSiePolaczyc.printStackTrace();
        }


        return response;
    }

    @Override
    public String connection(URL url, String username, String password) throws Exception {
        String response = null;

        try {
            switch (connectionsType){
                case HTTPCONNECTION:
                    LOGGER.info(MessageFormat.format("Łączę przez {0}", connectionsType));
                    response = new HttpConnectionMethod().connection(url, username, password);
                    break;
                case APACHEHTTPCLIENT:
                    LOGGER.info(MessageFormat.format("Łączę przez {0}", connectionsType));
                    response = new ApacheHttpClientConnectionMethod().connection(url, username, password);
                    break;
                case RESTTAMPLATE:
                    LOGGER.info(MessageFormat.format("Łączę przez {0}", connectionsType));
                    response = new RestTemplateConnectionMethod().connection(url, username, password);
                    break;
                case UNIREST:
                    LOGGER.info(MessageFormat.format("Łączę przez {0}", connectionsType));
                    response = new UniRestConnectionMethod().connection(url, username, password);
                    break;
                default:
                    return new NieMoznaSiePolaczyc().getMessage();
            }

        } catch (NieMoznaSiePolaczyc | IOException nieMoznaSiePolaczyc) {
            nieMoznaSiePolaczyc.printStackTrace();
        }


        return response;
    }
}
