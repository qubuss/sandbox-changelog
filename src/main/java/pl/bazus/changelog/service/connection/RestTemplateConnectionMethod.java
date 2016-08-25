package pl.bazus.changelog.service.connection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;
import pl.bazus.changelog.properties.ConnectionProperties;
import pl.bazus.changelog.service.api.Connection;
import pl.bazus.changelog.service.api.ConnectionsType;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;

public class RestTemplateConnectionMethod implements Connection {
    final ConnectionsType connectionsType = ConnectionsType.RESTTAMPLATE;

    @Resource
    private ConnectionProperties connectionProperties;

    @Override
    public String connection(URL url) throws NieMoznaSiePolaczyc, IOException {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(connectionProperties.getUsername(), connectionProperties.getPassword()));
        ResponseEntity<String> response = restTemplate.getForEntity(url.toString(), String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());

        return root.toString();
    }
}
