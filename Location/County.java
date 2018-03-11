package Location;

public class County extends Country {
    private final String name;
    
    public County(String countryName, String countyName) {
        super(countryName);
        this.name = countyName;
    }
    
    public String getCounty() {
        return name;
    }
}
