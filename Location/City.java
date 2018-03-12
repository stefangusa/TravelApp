package Location;

import Utils.PriceComparator;
import java.util.Set;
import java.util.TreeSet;

public class City extends County {
    private final String name;
    private Set<Location> topLocations;
    
    public City(String countryName, 
                String countyName,
                String cityName) {
        super(countryName, countyName);
        this.name = cityName;
        this.topLocations = new TreeSet<Location>(PriceComparator.getInstance());
    }
    
    @Override
    public void addLocation(Location location) {
        this.topLocations.add(location);
    }
        
    @Override
    public String getName() {
        return this.name;
    }
    
    public String getCountyName() {
        return super.getName();
    }
        
    @Override
    public String getCountryName() {
        return super.getCountryName();
    }
    
    @Override
    public Set<Location> getTop() {
        return this.topLocations;
    }
}
