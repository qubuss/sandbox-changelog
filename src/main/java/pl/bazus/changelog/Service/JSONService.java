package pl.bazus.changelog.Service;


import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;

public class JSONService {
    private BufferedReader bufferedReader;
    private final static Logger LOGGER = Logger.getLogger(JSONService.class);



    public String getDodatkowyOpis(BufferedReader bufferedReader) throws Exception {
        this.bufferedReader = bufferedReader;
        String result = getCustomFields(getStringFromURL()).getString("value");
        return result;
    }

    private String getStringFromURL() throws Exception {
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();

        return response.toString();
    }

    public JSONObject getCustomFields(String zawartosc) throws Exception {
        JSONObject obj = new JSONObject(zawartosc);
        JSONObject object = obj.getJSONObject("issue");
        JSONArray s = object.getJSONArray("custom_fields");
        JSONObject result = s.getJSONObject(0);
        return result;
    }

}
