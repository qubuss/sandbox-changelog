package pl.bazus.changelog.service.controller;

import pl.bazus.changelog.domain.Issue;
import pl.bazus.changelog.exceptions.NieMoznaPobracDanychZGitException;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczycException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author bogdan.mglowski on 2016-09-15.
 */
public interface ChangelogGitService {
    List<Issue> connectAndGetAllIssue(URL url) throws NieMoznaPobracDanychZGitException, NieMoznaSiePolaczycException, IOException;
}
