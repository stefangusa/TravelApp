package Utils;

import Location.Location;
import java.util.Comparator;

public class PriceComparator implements Comparator<Location> {

    private static PriceComparator p = new PriceComparator();
    
    public static PriceComparator getInstance() {
        return p;
    }
    
    @Override
    public int compare(Location loc1, Location loc2) {
        if (loc1.getPrice() <= loc2.getPrice()) {
            return -1;
        }
        else {
            return 1;
        }
    }
    
}
