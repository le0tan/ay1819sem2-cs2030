import java.util.*;

public class CruiseCenter {
    List<Loader> loaders;
    
    CruiseCenter() {
        loaders = new ArrayList<Loader>();
    }

    public void serve(Cruise c) {
        boolean isBig = false;
        if(isBig) {

        } else {
            Loader used = null;
            for(Loader l: loaders) {
                if(l.isAvailable(c.time)) {
                    used = l;
                    l.setAvailable(c.time, new Time(0,30), c);
                    break;
                }
            }
            if(used==null) {
                Loader newLoader = new Loader(false, c.time);
                newLoader.setAvailable(c.time, new Time(0,30), c);
                loaders.add(newLoader);
            }
        }
    }

    public List<Loader> getLoaders() {
        return loaders;
    }
}


