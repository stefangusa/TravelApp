package Location;

import Utils.PriceComparator;
import java.util.Set;
import java.util.TreeSet;

/* Country class - with its name and top locations by price    */
public class Country {
    private final String name;
    private Set<Location> topLocations;
    
    /* Sets up only own variables (no super)    */
    public Country(String countryName) {
        this.name = countryName;
        this.topLocations = new TreeSet<Location>(PriceComparator.getInstance());
    }
        
     /* Method that adds a location to the topLocations  */
    public void addLocation(Location location) {
        this.topLocations.add(location);
    }
        
    /* Method that returns country's name  */
    public String getName() {
        return this.name;
    }

    /* Method that returns topLocations set */    
    public Set<Location> getTop() {
        return this.topLocations;
    }
}
