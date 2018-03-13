package Utils;

import Location.Location;
import java.util.Comparator;

/* Singleton class that comparest two counties by their name   */
public class LocationComparator implements Comparator<Location> {

    private static LocationComparator p = new LocationComparator();
    
    public static LocationComparator getInstance() {
        return p;
    }
    
    @Override
    public int compare(Location loc1, Location loc2) {
        return loc1.getName().compareTo(loc2.getName());
    }
}
