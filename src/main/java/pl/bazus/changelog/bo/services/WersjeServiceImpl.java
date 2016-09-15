package pl.bazus.changelog.bo.services;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bazus.changelog.domain.EAplikacja;
import pl.bazus.changelog.domain.IssueRedmine;
import pl.bazus.changelog.domain.WersjaAplikacjiBazus;
import pl.bazus.changelog.exceptions.NieMoznaPobracDanychZGitException;
import pl.bazus.changelog.exceptions.NieMoznaSiePolaczycException;
import pl.bazus.changelog.mockData.BazusMock;
import pl.bazus.changelog.service.controller.ReadFromRedmineService;

import java.io.IOException;
import java.util.List;

/**
 * @author bogdan.mglowski on 2016-09-15.
 */
@Slf4j
public class WersjeServiceImpl implements WersjeService {

    private ReadFromRedmineService readFromRedmineService;

    @Autowired
    public WersjeServiceImpl(ReadFromRedmineService readFromRedmineService) {
        this.readFromRedmineService = readFromRedmineService;
    }

    @Override
    public List<WersjaAplikacjiBazus> getListaWersjiAplikacji(EAplikacja bazus) {
        return new BazusMock().getBazus();
    }

    @Override
    public List<IssueRedmine> szczegolyWersjiAplikacji(EAplikacja bazus, String idWersji, String idParent) throws NieMoznaPobracDanychZGitException, IOException, NieMoznaSiePolaczycException {
        List<IssueRedmine> result = readFromRedmineService.getFieldFromAllIssue("subject");
        result.forEach(issue -> log.info(issue.getIssueId()+" "+issue.getSubject()));
        return result;
    }


}
