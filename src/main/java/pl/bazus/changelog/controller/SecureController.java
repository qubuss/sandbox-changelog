package pl.bazus.changelog.controller;

import jdk.nashorn.internal.runtime.options.LoggingOption;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
public class SecureController {

    private final static Logger LOGGER = Logger.getLogger(SecureController.class);

    @RequestMapping(path = "/whoami", method = RequestMethod.GET, produces = "application/json")
    public Principal who(){
        return (Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    private List<String> uprawnienia(String authoriti){
        if(authoriti.equals("ROLE_ADMIN")){
            Arrays.asList(
                    "upr.moze.zamykac.issue",
                    "upr.moze.przegladac.szczegoly"
            );
        }else if(authoriti.equals("ROLE_USER")){
            Arrays.asList(
                    "upr.moze.przegladac.szczegoly"
            );
        }
    }


//    @RequestMapping(path = "/secure", method = RequestMethod.GET, produces = "application/json")
//    public boolean getDetailsSecur(){
//        JSONObject object = new JSONObject(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        JSONArray array = object.getJSONArray("authorities");
//        boolean czy = false;
//        for(int i = 0; i<array.length(); i++){
//            if(array.getJSONObject(i).getString("authority").equals("ROLE_ADMIN")){
//                czy=true;
//                break;
//            }else {
//                czy=false;
//            }
//        }
//        LOGGER.info(array);
//        return czy;
//    }
//
//
    @RequestMapping(path = "/uprawnienia", method = RequestMethod.GET, produces = "application/json")
    public List<String> getDetailsSecur(){
        List<GrantedAuthority> roleUsera = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        roleUsera.forEach(grantedAuthority -> {


        });


        throw new NotImplementedException();

    }
}


//    Granted Authorities: ROLE_ADMIN,ROLE_USER