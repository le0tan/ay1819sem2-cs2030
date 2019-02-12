import java.util.*;

public class CruiseCenter {
    List<Loader> loaders;
    
    CruiseCenter() {
        loaders = new ArrayList<Loader>();
    }

    public void serve(Cruise c) {
        boolean isBig = c.isBig;
        Time serviceTime = new Time(0,30);
        int found = 0;
        int need = 1;
        if(isBig) {
            serviceTime = new Time(0,60);
            need = 2;
        }
        for(Loader l: loaders) {
            if(l.isAvailable(c.time)) {
                found++;
                l.setAvailable(c.time, serviceTime, c);
                if(found>=need) break;
            }
        }
        if(found<need) {
            while(found<need){
                Loader newLoader = new Loader(false, c.time);
                newLoader.setAvailable(c.time, serviceTime, c);
                loaders.add(newLoader);
                found++;
            }
        }
    }

    public List<Loader> getLoaders() {
        return loaders;
    }
}


