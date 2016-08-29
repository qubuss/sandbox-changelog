package pl.bazus.changelog.service.utils;

import sun.misc.BASE64Encoder;

public class MyBasicAuth {

    public String doBasicAuth(String username, String password) {
        String user = username + ":" + password;
        return "Basic " + new BASE64Encoder().encode(user.getBytes());
    }
}
