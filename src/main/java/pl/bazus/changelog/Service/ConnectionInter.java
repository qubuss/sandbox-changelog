package pl.bazus.changelog.Service;

public interface ConnectionInter {
    void HttpConn() throws Exception;
    void ApacheHttpClient() throws Exception;
    void RestTemplate() throws Exception;
    void Unirest() throws Exception;
}
