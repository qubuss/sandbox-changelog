package pl.bazus.changelog.service;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczycException;
import pl.bazus.changelog.service.api.Connection;
import pl.bazus.changelog.service.api.ConnectionsType;
import pl.bazus.changelog.service.connection.ApacheHttpClientConnectionMethod;
import pl.bazus.changelog.service.connection.HttpConnectionMethod;
import pl.bazus.changelog.service.connection.RestTemplateConnectionMethod;
import pl.bazus.changelog.service.connection.UniRestConnectionMethod;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;

@Service
public class RedmineIssueService implements Connection {
    private final static Logger LOGGER = Logger.getLogger(RedmineIssueService.class);
    private final ConnectionsType connectionsType;


    public RedmineIssueService() {
        LOGGER.info("Tworze obiekt RedmineIssueService");
        this.connectionsType = ConnectionsType.UNIREST;
    }

    @Override
    public String connection(URL url) throws NieMoznaSiePolaczycException, IOException {
        String response = null;
        LOGGER.info(MessageFormat.format("Łączę przez {0}", connectionsType));
        try {
            switch (connectionsType) {
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
                    return new NieMoznaSiePolaczycException().getMessage();
            }

        } catch (NieMoznaSiePolaczycException | IOException e) {
            LOGGER.error(e.getMessage());
        }

        return response;
    }

    @Override
    public String connection(URL url, String username, String password) throws NieMoznaSiePolaczycException, IOException {
        String response = null;
        LOGGER.info(MessageFormat.format("Łączę przez {0}", connectionsType));
        try {
            switch (connectionsType) {
                case HTTPCONNECTION:
                    response = new HttpConnectionMethod().connection(url, username, password);
                    break;
                case APACHEHTTPCLIENT:
                    response = new ApacheHttpClientConnectionMethod().connection(url, username, password);
                    break;
                case RESTTAMPLATE:
                    response = new RestTemplateConnectionMethod().connection(url, username, password);
                    break;
                case UNIREST:
                    response = new UniRestConnectionMethod().connection(url, username, password);
                    break;
                default:
                    return new NieMoznaSiePolaczycException().getMessage();
            }

        } catch (NieMoznaSiePolaczycException | IOException e) {
            LOGGER.error(e.getMessage());
        }


        return response;
    }
}
