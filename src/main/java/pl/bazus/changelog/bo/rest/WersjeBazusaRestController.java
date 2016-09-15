package pl.bazus.changelog.bo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bazus.changelog.bo.services.WersjeService;
import pl.bazus.changelog.domain.EAplikacja;
import pl.bazus.changelog.domain.IssueRedmine;
import pl.bazus.changelog.domain.WersjaAplikacjiBazus;
import pl.bazus.changelog.exceptions.NieMoznaPobracDanychZGitException;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczycException;

import java.io.IOException;
import java.util.List;

/**
 * @author bogdan.mglowski on 2016-09-15.
 */

@RestController
public class WersjeBazusaRestController {

    private WersjeService wersjeService;

    @Autowired
    public WersjeBazusaRestController(WersjeService wersjeService) {
        this.wersjeService = wersjeService;
    }

    @RequestMapping(path = "/bazus/listaWersjiAplikacjiBazus", method = RequestMethod.GET, produces = "application/json")
    public List<WersjaAplikacjiBazus> getListaWersjiAplikacjiBazus(){
        return wersjeService.getListaWersjiAplikacji(EAplikacja.BAZUS);
    }


    @RequestMapping(path = "/bazus/szczegolyWersjiAplikacjiBazus", method = RequestMethod.GET, produces = "application/json")
    public List<IssueRedmine> getSzczegolyWersjiAplikacjiBazus(@RequestParam(value = "idWersji") String idWersji,
                                                               @RequestParam(value = "idParent") String idParent) throws NieMoznaPobracDanychZGitException, IOException, NieMoznaSiePolaczycException {


        return wersjeService.szczegolyWersjiAplikacji(EAplikacja.BAZUS, idWersji, idParent);
    }





}
