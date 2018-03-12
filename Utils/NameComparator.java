package Utils;

import Location.Country;
import java.util.Comparator;

public class NameComparator implements Comparator<Country> {

    private static NameComparator p = new NameComparator();
    
    public static NameComparator getInstance() {
        return p;
    }
    
    @Override
    public int compare(Country loc1, Country loc2) {
        return loc1.getName().compareTo(loc2.getName());
    }
}
