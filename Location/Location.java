package Location;

import Utils.MyDate;
import java.util.Arrays;
import java.util.List;

public class Location extends City {
    private String name;
    private double price;
    private List<String> activities;
    private MyDate startDate;
    private MyDate endDate;
    
    public Location(String countryName, String countyName,
                    String cityName, String locationName,
                    double averagePrice, String[] activities,
                    MyDate startDate, MyDate endDate) {
        super(countryName, countyName, cityName);
        this.name = locationName;
        this.price = averagePrice;
        this.activities = Arrays.asList(activities);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getName() {
        return this.name;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public List<String> getActivities() {
        return this.activities;
    }
    
    public String getPeriod() {
        String result = String.valueOf(startDate.getDay());
        result = result.concat("." + String.valueOf(startDate.getMonth()) +
                                "." + String.valueOf(startDate.getYear()) +
                                " - " + String.valueOf(endDate.getDay()) +
                                "." + String.valueOf(endDate.getMonth()) +
                                "." + String.valueOf(endDate.getYear()));
        return result;       
    }
}
