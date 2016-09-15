package pl.bazus.changelog.mockData;

import com.google.common.collect.Lists;
import pl.bazus.changelog.domain.WersjaAplikacjiBazus;

import java.util.List;

public class BazusMock {
    public List<WersjaAplikacjiBazus> getBazus(){
        List<WersjaAplikacjiBazus> result = Lists.newArrayList();
        WersjaAplikacjiBazus wersjaAplikacjiBazus;

        for(int i = 0; i<10; i++){
            wersjaAplikacjiBazus = new WersjaAplikacjiBazus();
            wersjaAplikacjiBazus.setDataUstanowienia("wersjaAplikacjiBazus "+i);
            wersjaAplikacjiBazus.setIdParent("wersjaAplikacjiBazus "+i);
            wersjaAplikacjiBazus.setIdWersja("wersjaAplikacjiBazus "+i);
            wersjaAplikacjiBazus.setNazwaUUID("wersjaAplikacjiBazus "+i);
            wersjaAplikacjiBazus.setTworca("wersjaAplikacjiBazus "+i);
            wersjaAplikacjiBazus.setWersjaBazy("wersjaAplikacjiBazus "+i);

            result.add(wersjaAplikacjiBazus);
        }

        return result;
    }
}
