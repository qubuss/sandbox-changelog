package pl.bazus.changelog.service.connection;

import pl.bazus.changelog.exceptions.NieMoznaSiePolaczycException;
import pl.bazus.changelog.service.api.Connection;
import pl.bazus.changelog.service.api.ConnectionsType;
import pl.bazus.changelog.service.utils.MyBasicAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpConnectionMethod implements Connection {
    final ConnectionsType connectionsType = ConnectionsType.HTTPCONNECTION;

// ConnectionProperties connectionProperties;

//    public HttpConnectionMethod() {
//        this.connectionProperties = BeanFactoryProvider.getBean(ConnectionProperties.class);
//    }

    @Override
    public String connection(URL url) throws NieMoznaSiePolaczycException, IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        return getResponse(con);
    }

    @Override
    public String connection(URL url, String username, String password) throws NieMoznaSiePolaczycException, IOException {

        String encode = new MyBasicAuth().loginWithBasicAuth(username, password);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Authorization", encode);
        con.setRequestMethod("GET");

        return getResponse(con);
    }

    private String getResponse(HttpURLConnection con) throws IOException {
        StringBuffer response = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        br.lines().forEach(s -> response.append(s)); //response::append
        br.close();

        return response.toString();
    }
}
