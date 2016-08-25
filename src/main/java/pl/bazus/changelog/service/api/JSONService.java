package pl.bazus.changelog.service.api;

import org.json.JSONArray;

public interface JSONService {
    String getFieldFromIssue(String field);
    JSONArray getALLIssues(String respons);
}
