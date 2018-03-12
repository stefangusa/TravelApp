package Location;

import Utils.PriceComparator;
import java.util.Set;
import java.util.TreeSet;

public class County extends Country {
    private final String name;
    private Set<Location> topLocations;
    
    public County(String countryName, String countyName) {
        super(countryName);
        this.name = countyName;
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
    
    public String getCountryName() {
        return super.getName();
    }
       
    @Override
    public Set<Location> getTop() {
        return this.topLocations;
    }
}
