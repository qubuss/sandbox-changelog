package pl.bazus.changelog.domain;

public class Bazus {
    private String idWersja;
    private String idParent;
    private String nazwaUUID;
    private String dataUstanowienia;
    private String wersjaBazy;
    private String tworca;

    public String getIdWersja() {
        return idWersja;
    }

    public void setIdWersja(String idWersja) {
        this.idWersja = idWersja;
    }

    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    public String getNazwaUUID() {
        return nazwaUUID;
    }

    public void setNazwaUUID(String nazwaUUID) {
        this.nazwaUUID = nazwaUUID;
    }

    public String getDataUstanowienia() {
        return dataUstanowienia;
    }

    public void setDataUstanowienia(String dataUstanowienia) {
        this.dataUstanowienia = dataUstanowienia;
    }

    public String getWersjaBazy() {
        return wersjaBazy;
    }

    public void setWersjaBazy(String wersjaBazy) {
        this.wersjaBazy = wersjaBazy;
    }

    public String getTworca() {
        return tworca;
    }

    public void setTworca(String tworca) {
        this.tworca = tworca;
    }
}
