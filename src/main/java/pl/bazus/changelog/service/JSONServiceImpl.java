package pl.bazus.changelog.service;


import com.oracle.javafx.jmx.json.JSONException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.bazus.changelog.service.api.JSONService;

import java.text.MessageFormat;

public class JSONServiceImpl implements JSONService {
    private final static Logger LOGGER = Logger.getLogger(JSONServiceImpl.class);
    private String response;

    public JSONServiceImpl() {
        LOGGER.info("Tworzę JSONSerwis");
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String getFieldFromIssue(String field) {
        JSONObject responseJSONObject;// = new JSONObject(response);
        try {
            String resultObject = "";
            if (!response.equals(" ")) {
                responseJSONObject = new JSONObject(response);
                JSONObject jsonObject = responseJSONObject.getJSONObject("issue");
                resultObject = jsonObject.getString(field);

            }
            return resultObject;

        } catch (JSONException e) {
            return "nie moge pobrac";
        }

    }

    @Override
    public JSONArray getALLIssues(String respons) {
        JSONObject resultObj = new JSONObject(respons);
        JSONArray result = resultObj.getJSONArray("issues");
        LOGGER.info(MessageFormat.format("Issues count {0}", result.length()));
        return result;
    }


}
