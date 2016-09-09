package pl.bazus.changelog.service.api;

import org.json.JSONArray;
import pl.bazus.changelog.exceptions.BladJSONException;

public interface JSONService {
    String getFieldFromIssue(String field) throws BladJSONException;

    JSONArray getALLIssues(String respons) throws BladJSONException;
}
