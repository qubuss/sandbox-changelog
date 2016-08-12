package pl.bazus.changelog.Service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//TODO przeniesc do absrtact / inter , refactor

public class RedmineIssueService implements ConnectionInter {
    private final static Logger LOGGER = Logger.getLogger(RedmineIssueService.class);
    private Integer idIssue;
    private static String URL_ISSUES = "http://10.0.10.192/issues/";
    private static URL url;
    final String USER_AGENT = "Mozilla/5.0";
    private JSONService jsonService;

    public RedmineIssueService(Integer idIssue) throws MalformedURLException {
        this.idIssue = idIssue;
        URL_ISSUES += idIssue + ".json";
        url = new URL(URL_ISSUES);
        jsonService = new JSONService();

    }

    @Override
    public void HttpConn() throws Exception {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String s = jsonService.getDodatkowyOpis(br);
        LOGGER.info(s);

    }

    @Override
    public void ApacheHttpClient() throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL_ISSUES);
        HttpResponse response = httpClient.execute(httpGet);
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        String s = jsonService.getDodatkowyOpis(br);
        LOGGER.info(s);

    }


    @Override
    public void RestTemplate() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
//        Issue issue = restTemplate.getForObject(URL_ISSUES, Issue.class);

        ResponseEntity<String> response = restTemplate.getForEntity(URL_ISSUES, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JSONObject a = jsonService.getCustomFields(root.toString());
        String result = a.getString("value");

        LOGGER.info(result);
    }

    @Override
    public void Unirest() throws Exception {
        GetRequest gr = Unirest.get(URL_ISSUES);

        JSONObject a = jsonService.getCustomFields(gr.asJson().getBody().getObject().toString());
        String result = a.getString("value");

        LOGGER.info(result);
    }
}
