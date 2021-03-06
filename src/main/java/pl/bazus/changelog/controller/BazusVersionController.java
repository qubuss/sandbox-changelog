package pl.bazus.changelog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.domain.WersjaAplikacjiBazus;
import pl.bazus.changelog.service.BazusVersionService;

import java.util.List;

@RestController
public class BazusVersionController {

    @Autowired
    private BazusVersionService bazusVersionService;

    @Secured("ROLE_ADMIN")
    @RequestMapping(path = "/bazus/getBazusVersion", method = RequestMethod.GET, produces = "application/json")
    public List<WersjaAplikacjiBazus> getBazusVersion(){
        return bazusVersionService.getBazusVersion();
    }
}
