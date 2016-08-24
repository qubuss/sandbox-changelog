package pl.bazus.changelog.properties;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ConnectionSerwisTest {

    @Autowired
    private ConnectionProperties connectionProperties;


    public String get(){
        return connectionProperties.getUsername();
    }
}
