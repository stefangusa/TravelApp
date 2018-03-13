package Utils;

import Location.City;
import java.util.Comparator;

/* Singleton class that comparest two cities by their name   */
public class CityComparator implements Comparator<City> {

    private static CityComparator p = new CityComparator();
    
    public static CityComparator getInstance() {
        return p;
    }
    
    @Override
    public int compare(City loc1, City loc2) {
        return loc1.getName().compareTo(loc2.getName());
    }
}
