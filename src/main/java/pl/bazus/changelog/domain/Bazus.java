package pl.bazus.changelog.domain;

import lombok.Data;

@Data
public class Bazus {
    String idWersja;
    String idParent;
    String nazwaUUID;
    String dataUstanowienia;
    String wersjaBazy;
    String tworca;

}
