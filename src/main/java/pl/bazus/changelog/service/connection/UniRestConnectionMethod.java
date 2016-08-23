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
        gr.basicAuth("jakub.fryga", "daniel.12");
        String respone = null;
        try {
            respone = gr.asJson().getBody().getObject().toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }


        return respone;
    }
}
