package pl.bazus.changelog.bo.services;

import pl.bazus.changelog.domain.EAplikacja;
import pl.bazus.changelog.domain.IssueRedmine;
import pl.bazus.changelog.domain.WersjaAplikacjiBazus;

import java.util.List;

/**
 * @author bogdan.mglowski on 2016-09-15.
 */
public interface WersjeService {
    List<WersjaAplikacjiBazus> getListaWersjiAplikacji(EAplikacja bazus);
    List<IssueRedmine> szczegolyWersjiAplikacji(EAplikacja bazus, String idWersji, String idParent);
}
