package pl.bazus.changelog.properties;

import org.springframework.beans.factory.annotation.Autowired;


public class MyConfig {

    @Autowired
    private ConnectionSerwisTest connectionSerwisTest;

    public String print(){
        return connectionSerwisTest.get();
    }
}
