package Utils;

import Location.Country;
import java.util.Comparator;

/* Singleton class that comparest two countries by their name   */
public class CountryComparator implements Comparator<Country> {

    private static CountryComparator p = new CountryComparator();
    
    public static CountryComparator getInstance() {
        return p;
    }
    
    @Override
    public int compare(Country loc1, Country loc2) {
        return loc1.getName().compareTo(loc2.getName());
    }
}
