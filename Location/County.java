package Location;

import Utils.PriceComparator;
import java.util.Set;
import java.util.TreeSet;

/* County class - with its name and top locations by price    */
public class County {
    private final String countryName;
    private final String name;
    private Set<Location> topLocations;
    
    /*  Calls the constructor of county before 
        setting up local variables    */
    public County(String countryName, String countyName) {
        this.countryName = countryName;
        this.name = countyName;
        this.topLocations = new TreeSet<Location>(PriceComparator.getInstance());
    }
    
    /* Method that adds a location to the topLocations  */
    public void addLocation(Location location) {
        this.topLocations.add(location);
    }
    
    /* Method that returns county's name  */
    public String getName() {
        return this.name;
    }
 
    /* Method that returns country's (where the county is) name  */
    public String getCountryName() {
        return this.countryName;
    }

    /* Method that returns topLocations set */    
    public Set<Location> getTop() {
        return this.topLocations;
    }
}
