package pl.bazus.changelog.bo.services;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import pl.bazus.changelog.domain.EAplikacja;
import pl.bazus.changelog.domain.IssueRedmine;
import pl.bazus.changelog.domain.WersjaAplikacjiBazus;
import pl.bazus.changelog.mockData.BazusMock;

import java.util.List;

/**
 * @author bogdan.mglowski on 2016-09-15.
 */
@Slf4j
public class WersjeServiceImpl implements WersjeService {

    @Override
    public List<WersjaAplikacjiBazus> getListaWersjiAplikacji(EAplikacja bazus) {
        return new BazusMock().getBazus();
    }

    @Override
    public List<IssueRedmine> szczegolyWersjiAplikacji(EAplikacja bazus, String idWersji, String idParent) {


        List<IssueRedmine> result = Lists.newArrayList();
        try {
            result = readFromRedmineService.getFieldFromAllIssue(field);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        result.forEach(issue -> log.info(issue.getIssueId()+" "+issue.getSubject()));
        return result;

    }
}
