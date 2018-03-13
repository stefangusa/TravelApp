package Location;

import Utils.PriceComparator;
import java.util.Set;
import java.util.TreeSet;

/* City class - with its name and top locations by price    */
public class City extends County {
    private final String name;
    private Set<Location> topLocations;
    
    /*  Calls the constructor of county and then country before 
        setting up local variables    */
    public City(String countryName, 
                String countyName,
                String cityName) {
        super(countryName, countyName);
        this.name = cityName;
        this.topLocations = new TreeSet<Location>(PriceComparator.getInstance());
    }
    
    /* Method that adds a location to the topLocations  */
    @Override
    public void addLocation(Location location) {
        this.topLocations.add(location);
    }
        
    /* Method that returns city's name  */
    @Override
    public String getName() {
        return this.name;
    }
  
    /* Method that returns county's (where the city is) name  */
    public String getCountyName() {
        return super.getName();
    }
     
    /* Method that returns country's (where the city is) name  */
    @Override
    public String getCountryName() {
        return super.getCountryName();
    }
    
    /* Method that returns topLocations set */
    @Override
    public Set<Location> getTop() {
        return this.topLocations;
    }
}
