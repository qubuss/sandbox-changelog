package pl.bazus.changelog.service;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.bazus.changelog.domain.User;

@Service
public class SecurityService {
    private final static Logger LOGGER = Logger.getLogger(SecurityService.class);

    public User getUserDetails(){
        User user = new User();
        JSONObject object = new JSONObject(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        LOGGER.info(object);
        JSONArray array = object.getJSONArray("authorities");
        boolean czy = false;
        for(int i = 0; i<array.length(); i++){
            if(array.getJSONObject(i).getString("authority").equals("ROLE_ADMIN")){
                czy=true;
                break;
            }else {
                czy=false;
            }
        }
        String username = object.getString("username");
        user.setUsername(username);
        user.setCzyAdmin(czy);
        LOGGER.info(array);

        return user;
    }
}


// {"credentialsNonExpired":true,"accountNonExpired":true,"authorities":[{"authority":"ROLE_ADMIN"},{"authority":"ROLE_USER"}],"enabled":true,"accountNonLocked":true,"username":"admin"}