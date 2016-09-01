package pl.bazus.changelog.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import pl.bazus.changelog.domain.Bazus;
import pl.bazus.changelog.mockData.BazusMock;

import java.util.List;

@Service
public class BazusVersionService {

    public List<Bazus> getBazusVersion(){
        List<Bazus> result = Lists.newArrayList();

        result = new BazusMock().getBazus();

        return result;
    }
}
