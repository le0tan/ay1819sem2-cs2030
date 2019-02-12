import java.util.*;
public class Loader {
    int no;
    public boolean isRecycled;
    Time availableAt;
    List<Cruise> servedCruises;
    public static int numOfLoaders = 0;
    public static int numOfRecycledLoaders = 0;

    Loader(boolean b, Time cur) {
        isRecycled = b;
        if(isRecycled) numOfRecycledLoaders++;
        numOfLoaders++;
        no = numOfLoaders;
        servedCruises = new ArrayList<Cruise>();
        availableAt = cur;
    }

    public boolean isAvailable(Time cur) {
        return cur.laterThan(availableAt);
    }

    public void setAvailable(Time cur, Time serviceTime, Cruise servedCruise) {
    //    if(!isAvailable(cur))
    //        throw Exception("Loader is not available!");
    //    else {
        servedCruises.add(servedCruise);
        if(isRecycled) {
            availableAt = cur.plus(new Time(1,0)).plus(serviceTime);
        } else {
            availableAt = cur.plus(serviceTime);
        }
    //    }
    }

    public List<Cruise> getServed() {
        return servedCruises;
    }

    public int getNo() {
        return no;
    }

    public void printServed() {
        if(!isRecycled) System.out.printf("Loader %d serves:\n", no);
        else System.out.printf("Loader %d (recycled) serves:\n", no);
        for(Cruise c: servedCruises) {
            System.out.print("    ");
            System.out.println(c);
        }
    }
}
