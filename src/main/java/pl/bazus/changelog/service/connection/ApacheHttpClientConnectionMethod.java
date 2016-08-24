package pl.bazus.changelog.service.connection;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;
import pl.bazus.changelog.service.api.Connection;
import pl.bazus.changelog.service.api.ConnectionsType;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ApacheHttpClientConnectionMethod implements Connection {
    final ConnectionsType connectionsType = ConnectionsType.APACHEHTTPCLIENT;

    @Override
    public String connection(URL url) throws NieMoznaSiePolaczyc, IOException {
        String user = "jakub.fryga:daniel.12";
        String encode = "Basic " + new BASE64Encoder().encode(user.getBytes());

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url.toString());
        httpGet.addHeader("Authorization", encode);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        BufferedReader br = new BufferedReader(
                new InputStreamReader((httpResponse.getEntity().getContent())));

        StringBuffer response = new StringBuffer();
        br.lines().forEach(s -> response.append(s));
        br.close();

        return response.toString();
    }
}
