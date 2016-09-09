package pl.bazus.changelog.service;

import org.springframework.stereotype.Service;
import pl.bazus.changelog.domain.Bazus;
import pl.bazus.changelog.mockData.BazusMock;

import java.util.List;

@Service
public class BazusVersionService {

    public List<Bazus> getBazusVersion(){

        return new BazusMock().getBazus();
    }
}
