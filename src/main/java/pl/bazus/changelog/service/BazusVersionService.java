package pl.bazus.changelog.service;

import org.springframework.stereotype.Service;
import pl.bazus.changelog.domain.WersjaAplikacjiBazus;
import pl.bazus.changelog.mockData.BazusMock;

import java.util.List;

@Service
public class BazusVersionService {

    public List<WersjaAplikacjiBazus> getBazusVersion(){
        return new BazusMock().getBazus();
    }
}
