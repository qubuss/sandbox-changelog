package pl.bazus.changelog.service.connection;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;
import pl.bazus.changelog.service.api.Connection;
import pl.bazus.changelog.service.api.ConnectionsType;

import java.io.IOException;
import java.net.URL;

public class UniRestConnectionMethod implements Connection {
    final ConnectionsType connectionsType = ConnectionsType.UNIREST;

    @Override
    public String connection(URL url) throws NieMoznaSiePolaczyc, IOException {

        GetRequest gr = Unirest.get(url.toString());
        String respone = null;
        try {
            respone = gr.asString().getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        System.out.println(respone);
        return respone;
    }

    @Override
    public String connection(URL url, String username, String password) throws Exception {
        GetRequest gr = Unirest.get(url.toString());
        gr.basicAuth(username, password);
        String respone = null;
        try {
            respone = gr.asString().getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return respone;
    }
}
