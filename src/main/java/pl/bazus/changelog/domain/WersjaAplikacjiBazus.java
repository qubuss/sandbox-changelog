package pl.bazus.changelog.domain;

import lombok.Data;

@Data
public class WersjaAplikacjiBazus {
    String idWersja;
    String idParent;
    String nazwaUUID;
    String dataUstanowienia;
    String wersjaBazy;
    String tworca;

}
