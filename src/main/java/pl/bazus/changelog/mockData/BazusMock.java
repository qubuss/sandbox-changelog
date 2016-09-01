package pl.bazus.changelog.mockData;

import com.google.common.collect.Lists;
import pl.bazus.changelog.domain.Bazus;

import java.util.List;

public class BazusMock {
    public List<Bazus> getBazus(){
        List<Bazus> result = Lists.newArrayList();
        Bazus bazus;

        for(int i = 0; i<10; i++){
            bazus = new Bazus();
            bazus.setDataUstanowienia("bazus "+i);
            bazus.setIdParent("bazus "+i);
            bazus.setIdWersja("bazus "+i);
            bazus.setNazwaUUID("bazus "+i);
            bazus.setTworca("bazus "+i);
            bazus.setWersjaBazy("bazus "+i);

            result.add(bazus);
        }

        return result;
    }
}
