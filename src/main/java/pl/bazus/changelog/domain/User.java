package pl.bazus.changelog.domain;

import lombok.Data;

import java.util.List;

@Data
public class User {
    String username;
    boolean czyAdmin;
}
