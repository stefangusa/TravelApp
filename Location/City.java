package Location;

import Utils.PriceComparator;
import java.util.Set;
import java.util.TreeSet;

/* City class - with its name and top locations by price    */
public class City {
    private final String countyName;
    private final String countryName;
    private final String name;
    private Set<Location> topLocations;
    
    /*  Calls the constructor of county and then country before 
        setting up local variables    */
    public City(String countryName, 
                String countyName,
                String cityName) {
        this.countryName = countryName;
        this.countyName = countyName;
        this.name = cityName;
        this.topLocations = new TreeSet<Location>(PriceComparator.getInstance());
    }
    
    /* Method that adds a location to the topLocations  */
    public void addLocation(Location location) {
        this.topLocations.add(location);
    }
        
    /* Method that returns city's name  */
    public String getName() {
        return this.name;
    }
  
    /* Method that returns county's (where the city is) name  */
    public String getCountyName() {
        return this.countyName;
    }
     
    /* Method that returns country's (where the city is) name  */
    public String getCountryName() {
        return this.countyName;
    }
    
    /* Method that returns topLocations set */
    public Set<Location> getTop() {
        return this.topLocations;
    }
}
