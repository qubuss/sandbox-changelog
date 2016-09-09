package pl.bazus.changelog.service.connection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczycException;
import pl.bazus.changelog.service.api.Connection;
import pl.bazus.changelog.service.api.ConnectionsType;
import pl.bazus.changelog.service.utils.MyBasicAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ApacheHttpClientConnectionMethod implements Connection {
    final ConnectionsType connectionsType = ConnectionsType.APACHEHTTPCLIENT;

    @Override
    public String connection(URL url) throws NieMoznaSiePolaczycException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url.toString());

        return getResponse(httpClient, httpGet);
    }

    @Override
    public String connection(URL url, String username, String password) throws NieMoznaSiePolaczycException, IOException {

        String encode = new MyBasicAuth().loginWithBasicAuth(username, password);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url.toString());
        httpGet.addHeader("Authorization", encode);

        return getResponse(httpClient, httpGet);
    }

    private String getResponse(HttpClient httpClient, HttpGet httpGet) throws IOException {
        HttpResponse httpResponse = httpClient.execute(httpGet);
        BufferedReader br = new BufferedReader(
                new InputStreamReader((httpResponse.getEntity().getContent())));

        StringBuilder response = new StringBuilder();
        br.lines().forEach(response::append);
        br.close();
        return response.toString();
    }
}
