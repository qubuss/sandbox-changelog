package pl.bazus.changelog.service.api;

import net.minidev.json.JSONObject;

public interface RedmineIssue {

    JSONObject getIssue(Integer idIssue, String field);
}
