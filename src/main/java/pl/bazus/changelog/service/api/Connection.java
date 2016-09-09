package pl.bazus.changelog.service.api;

import pl.bazus.changelog.exceptions.NieMoznaSiePolaczycException;

import java.io.IOException;
import java.net.URL;

public interface Connection {
    String connection(URL url) throws NieMoznaSiePolaczycException, IOException;

    String connection(URL url, String username, String password) throws NieMoznaSiePolaczycException, IOException;
}
