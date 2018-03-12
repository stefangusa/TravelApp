package Location;

import Utils.PriceComparator;
import java.util.Set;
import java.util.TreeSet;

public class Country {
    private final String name;
    private Set<Location> topLocations;
    
    public Country(String countryName) {
        this.name = countryName;
        this.topLocations = new TreeSet<Location>(PriceComparator.getInstance());
    }
        
    public String getName() {
        return this.name;
    }
        
    public void addLocation(Location location) {
        this.topLocations.add(location);
    }
    
    public Set<Location> getTop() {
        return this.topLocations;
    }
}
