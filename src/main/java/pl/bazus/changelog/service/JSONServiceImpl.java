package pl.bazus.changelog.service;


import com.oracle.javafx.jmx.json.JSONException;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import pl.bazus.changelog.service.api.JSONService;

public class JSONServiceImpl implements JSONService {
    private final static Logger LOGGER = Logger.getLogger(JSONServiceImpl.class);
    private String response;

    public JSONServiceImpl(){
        LOGGER.info("Tworzę JSONSerwis");
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String getFieldFromIssue(String field) {
        JSONObject responseJSONObject;// = new JSONObject(response);
        try{
            responseJSONObject = new JSONObject(response);
            JSONObject jsonObject = responseJSONObject.getJSONObject("issue");
            String resultObject = jsonObject.getString(field);
            return resultObject.toString();
        }catch (JSONException e){
            return "nie moge pobrac";
        }

    }
}
