package pl.bazus.changelog.service.api;

import org.json.JSONArray;
import pl.bazus.changelog.exceptions.BladJSON;

public interface JSONService {
    String getFieldFromIssue(String field) throws BladJSON;

    JSONArray getALLIssues(String respons) throws BladJSON;
}
