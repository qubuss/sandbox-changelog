package pl.bazus.changelog.service;


import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.bazus.changelog.exceptions.BladJSONException;
import pl.bazus.changelog.service.api.JSONService;

import java.text.MessageFormat;

@Service
public class JSONServiceImpl implements JSONService {
    private final static Logger LOGGER = Logger.getLogger(JSONServiceImpl.class);
    private String response;

    public JSONServiceImpl() {
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String getFieldFromIssue(String field) throws BladJSONException {
        JSONObject responseJSONObject;
        try {
            String resultObject = "";
            if (!response.equals(" ")) {
                responseJSONObject = new JSONObject(response);
                JSONObject jsonObject = responseJSONObject.getJSONObject("issue");
                resultObject = jsonObject.getString(field);

            }
            return resultObject;

        } catch (JSONException e) {
            LOGGER.error(e.getMessage());
            return "Błąd z parsowaniem JSON";
        }

    }

    @Override
    public JSONArray getALLIssues(String respons) throws BladJSONException {
        JSONObject resultObj = new JSONObject(respons);
        JSONArray result = resultObj.getJSONArray("issues");
        LOGGER.info(MessageFormat.format("Issues count {0}", result.length()));
        return result;
    }


}
