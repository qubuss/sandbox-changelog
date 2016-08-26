package pl.bazus.changelog.service.connection;

import org.apache.log4j.Logger;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;
import pl.bazus.changelog.properties.ConnectionProperties;
import pl.bazus.changelog.service.api.Connection;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;


public class ChangeLogGit implements Connection {
    private final static Logger LOGGER = Logger.getLogger(ChangeLogGit.class);

    @Resource
    private ConnectionProperties connectionProperties;

    @Override
    public String connection(URL url) throws NieMoznaSiePolaczyc, IOException {
        System.out.println(connectionProperties.toString());
        String respone;
        HttpConnectionMethod httpConnectionMethod = new HttpConnectionMethod();
        respone = httpConnectionMethod.connection(url);
        LOGGER.info("Połączyłem się do ChangeLog git");
        return respone;
    }

    @Override
    public String connection(URL url, String username, String password) throws Exception {
        return null;
    }

}
