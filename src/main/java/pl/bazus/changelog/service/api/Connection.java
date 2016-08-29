package pl.bazus.changelog.service.api;

import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;

import java.io.IOException;
import java.net.URL;

public interface Connection {
    String connection(URL url) throws NieMoznaSiePolaczyc, IOException;

    String connection(URL url, String username, String password) throws Exception;
}
