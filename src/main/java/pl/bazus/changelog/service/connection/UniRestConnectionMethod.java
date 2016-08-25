package pl.bazus.changelog.service.connection;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.oracle.javafx.jmx.json.JSONException;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;
import pl.bazus.changelog.properties.ConnectionProperties;
import pl.bazus.changelog.service.api.Connection;
import pl.bazus.changelog.service.api.ConnectionsType;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;

public class UniRestConnectionMethod implements Connection {
    final ConnectionsType connectionsType = ConnectionsType.UNIREST;

    @Resource
    private ConnectionProperties connectionProperties;
    @Override
    public String connection(URL url) throws NieMoznaSiePolaczyc, IOException {
        GetRequest gr = Unirest.get(url.toString());
        gr.basicAuth(connectionProperties.getUsername(), connectionProperties.getPassword());
        String respone = null;
        try {
            respone = gr.asString().getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (JSONException ej){

        }

        return respone;
    }
}
