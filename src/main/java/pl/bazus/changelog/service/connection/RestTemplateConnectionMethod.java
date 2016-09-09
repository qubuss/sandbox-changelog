package pl.bazus.changelog.service.connection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczycException;
import pl.bazus.changelog.service.api.Connection;
import pl.bazus.changelog.service.api.ConnectionsType;

import java.io.IOException;
import java.net.URL;

public class RestTemplateConnectionMethod implements Connection {
    final ConnectionsType connectionsType = ConnectionsType.RESTTAMPLATE;

    @Override
    public String connection(URL url) throws NieMoznaSiePolaczycException, IOException {

        RestTemplate restTemplate = new RestTemplate();
        JsonNode root = getResponse(url, restTemplate);
        return root.toString();
    }

    @Override
    public String connection(URL url, String username, String password) throws NieMoznaSiePolaczycException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(username, password));
        JsonNode root = getResponse(url, restTemplate);
        return root.toString();
    }

    private JsonNode getResponse(URL url, RestTemplate restTemplate) throws IOException {
        ResponseEntity<String> response = restTemplate.getForEntity(url.toString(), String.class);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(response.getBody());
    }
}
