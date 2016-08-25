package pl.bazus.changelog.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "connection")
public class ConnectionProperties {


    String username;
    String password;
    String urlRedmine;
    String urlChangelogGit;
    String urlChangelogGit150;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlRedmine() {
        return urlRedmine;
    }

    public void setUrlRedmine(String urlRedmine) {
        this.urlRedmine = urlRedmine;
    }

    public String getUrlChangelogGit() {
        return urlChangelogGit;
    }

    public void setUrlChangelogGit(String urlChangelogGit) {
        this.urlChangelogGit = urlChangelogGit;
    }

    public String getUrlChangelogGit150() {
        return urlChangelogGit150;
    }

    public void setUrlChangelogGit150(String urlChangelogGit150) {
        this.urlChangelogGit150 = urlChangelogGit150;
    }

    @Override
    public String toString() {
        return "ConnectionProperties{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", urlRedmine='" + urlRedmine + '\'' +
                ", urlChangelogGit='" + urlChangelogGit + '\'' +
                ", urlChangelogGit150='" + urlChangelogGit150 + '\'' +
                '}';
    }
}
