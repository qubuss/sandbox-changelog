package pl.bazus.changelog.service.connection;

import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;
import pl.bazus.changelog.service.api.Connection;
import pl.bazus.changelog.service.api.ConnectionsType;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpConnectionMethod implements Connection {
    final ConnectionsType connectionsType = ConnectionsType.HTTPCONNECTION;
    final String USER_AGENT = "Mozilla/5.0";

// ConnectionProperties connectionProperties;

//    public HttpConnectionMethod() {
//        this.connectionProperties = BeanFactoryProvider.getBean(ConnectionProperties.class);
//    }

    @Override
    public String connection(URL url) throws NieMoznaSiePolaczyc, IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");


        StringBuffer response = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        br.lines().forEach(s -> response.append(s));
        br.close();

        return response.toString();
    }

    @Override
    public String connection(URL url, String username, String password) throws Exception {

        String user = username+":"+password;
        String encode = "Basic " + new BASE64Encoder().encode(user.getBytes());

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Authorization", encode);
        con.setRequestMethod("GET");


        StringBuffer response = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        br.lines().forEach(s -> response.append(s));
        br.close();

        return response.toString();
    }
}
