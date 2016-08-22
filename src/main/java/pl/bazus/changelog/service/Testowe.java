package pl.bazus.changelog.service;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class Testowe {
    private final static Logger LOGGER = Logger.getLogger(Testowe.class);

    URL url = new URL("http://git.bazus.pl:8100/?repo=projekty/bazus.git&count=150");
    final String USER_AGENT = "Mozilla/5.0";
    private JSONService jsonService;

    public Testowe() throws Exception {
    }

    public void HttpConn() throws Exception {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();

        List<String> lista = Lists.newArrayList();
        String pattern = "(#{1}[0-9]{1,9})";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(response.substring(response.indexOf("OUTPUT:")));
        while (m.find()) {

            lista.add(m.group());

        }

    }
}
