package pl.bazus.changelog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.domain.User;
import pl.bazus.changelog.service.SecurityService;

import java.security.Principal;

@RestController
public class SecureController {


    @Autowired
    private SecurityService securityService;

    @RequestMapping(path = "/whoami", method = RequestMethod.GET, produces = "application/json")
    public Principal who() {
        return (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @RequestMapping(path = "/secure/getUserDetails", method = RequestMethod.GET, produces = "application/json")
    public User getDetailsSecure() {

        return securityService.getUserDetails();
    }


//    private List<String> uprawnienia(String authoriti){
//        if(authoriti.equals("ROLE_ADMIN")){
//            Arrays.asList(
//                    "upr.moze.zamykac.issue",
//                    "upr.moze.przegladac.szczegoly"
//            );
//        }else if(authoriti.equals("ROLE_USER")){
//            Arrays.asList(
//                    "upr.moze.przegladac.szczegoly"
//            );
//        }
//
//    }

//    @RequestMapping(path = "/uprawnienia", method = RequestMethod.GET, produces = "application/json")
//    public List<String> getDetailsSecure(){
//        List<GrantedAuthority> roleUsera = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//
//        roleUsera.forEach(grantedAuthority -> {
//
//        });
//
//
//        throw new NotImplementedException();
//
//    }
}


//    {"credentialsNonExpired":true,"accountNonExpired":true,"authorities":[{"authority":"ROLE_ADMIN"},{"authority":"ROLE_USER"}],"enabled":true,"accountNonLocked":true,"username":"admin"}