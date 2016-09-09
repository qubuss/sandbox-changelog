package pl.bazus.changelog.service.connection;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import org.apache.log4j.Logger;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczycException;
import pl.bazus.changelog.service.api.Connection;
import pl.bazus.changelog.service.api.ConnectionsType;

import java.io.IOException;
import java.net.URL;

public class UniRestConnectionMethod implements Connection {
    private final static Logger LOGGER = Logger.getLogger(UniRestConnectionMethod.class);
    final ConnectionsType connectionsType = ConnectionsType.UNIREST;

    @Override
    public String connection(URL url) throws NieMoznaSiePolaczycException, IOException {

        GetRequest gr = Unirest.get(url.toString());
        return getResponse(gr);
    }

    @Override
    public String connection(URL url, String username, String password) throws NieMoznaSiePolaczycException, IOException {
        GetRequest gr = Unirest.get(url.toString());
        gr.basicAuth(username, password);

        return getResponse(gr);
    }

    private String getResponse(GetRequest gr) throws NieMoznaSiePolaczycException {
        String respone;
        try {
            respone = gr.asString().getBody();
        } catch (UnirestException e) {
            LOGGER.error(e.getMessage());
            throw new NieMoznaSiePolaczycException();
        }
        return respone;
    }
}
