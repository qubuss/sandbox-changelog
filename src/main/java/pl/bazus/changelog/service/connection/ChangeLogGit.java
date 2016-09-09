package pl.bazus.changelog.service.connection;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczycException;
import pl.bazus.changelog.service.api.Connection;

import java.io.IOException;
import java.net.URL;

@Service
public class ChangeLogGit implements Connection {
    private final static Logger LOGGER = Logger.getLogger(ChangeLogGit.class);


    @Override
    public String connection(URL url) throws NieMoznaSiePolaczycException, IOException {
        String respone;
        HttpConnectionMethod httpConnectionMethod = new HttpConnectionMethod();
        respone = httpConnectionMethod.connection(url);
        LOGGER.info("Połączyłem się do ChangeLog git");
        return respone;
    }

    @Override
    public String connection(URL url, String username, String password) throws NieMoznaSiePolaczycException, IOException {
        return null;
    }

}
