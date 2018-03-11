package Location;

public class City extends County {
    private final String name;
    
    public City(String countryName, 
                String countyName,
                String cityName) {
        super(countryName, countyName);
        this.name = cityName;
    }
    
    public String getCity() {
        return this.name;
    }    
}
