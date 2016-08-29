package pl.bazus.changelog.service.connection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;
import pl.bazus.changelog.service.api.Connection;
import pl.bazus.changelog.service.api.ConnectionsType;
import pl.bazus.changelog.service.utils.MyBasicAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ApacheHttpClientConnectionMethod implements Connection {
    final ConnectionsType connectionsType = ConnectionsType.APACHEHTTPCLIENT;
    private URL url;

    @Override
    public String connection(URL url) throws NieMoznaSiePolaczyc, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url.toString());
        String response = getResponse(httpClient, httpGet);

        return response;
    }

    @Override
    public String connection(URL url, String username, String password) throws Exception {

        String encode = new MyBasicAuth().doBasicAuth(username, password);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url.toString());
        httpGet.addHeader("Authorization", encode);
        String response = getResponse(httpClient, httpGet);

        return response;
    }

    private String getResponse(HttpClient httpClient, HttpGet httpGet) throws IOException {
        HttpResponse httpResponse = httpClient.execute(httpGet);
        BufferedReader br = new BufferedReader(
                new InputStreamReader((httpResponse.getEntity().getContent())));

        StringBuffer response = new StringBuffer();
        br.lines().forEach(s -> response.append(s));
        br.close();
        return response.toString();
    }
}
