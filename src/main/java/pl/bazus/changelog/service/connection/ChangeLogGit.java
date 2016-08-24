package pl.bazus.changelog.service.connection;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import pl.bazus.changelog.domein.Issue;
import pl.bazus.changelog.domein.IssueChangeLog;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczyc;
import pl.bazus.changelog.service.api.Connection;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChangeLogGit implements Connection {
    private final static Logger LOGGER = Logger.getLogger(ChangeLogGit.class);

    @Override
    public String connection(URL url) throws NieMoznaSiePolaczyc, IOException {
        String respone;
        HttpConnectionMethod httpConnectionMethod = new HttpConnectionMethod();
        respone = httpConnectionMethod.connection(url);
        LOGGER.info("Połączyłem się do ChangeLOO git");
        return respone;
    }

    public List<Issue> getAllIssues(String response) {
        Issue issue;
        List<Issue> lista = Lists.newArrayList();
        String pattern = "(#{1}[0-9]{1,9})";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(response.substring(response.indexOf("OUTPUT:")));

        while (m.find()) {
            issue = new IssueChangeLog(m.group());
            lista.add(issue);

        }

        LOGGER.info("Ilość issue w changeLOG git "+ lista.size());

        return lista;
    }

}
