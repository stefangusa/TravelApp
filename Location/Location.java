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
     
    public double getPrice() {
        return this.price;
    }
    
    public MyDate getStartDate() {
        return this.startDate;
    }
    
    public MyDate getEndDate() {
        return this.endDate;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    public String getCityName() {
        return super.getName();
    }
    
    @Override
    public String getCountyName() {
        return super.getCountyName();
    }
    
    public String getCountryName() {
        return super.getCountryName();
    }
    
    public void printDetails() {
        System.out.println("Name: " + getName());
        System.out.println("City: " + getCityName());
        System.out.println("County: " + getCountyName());
        System.out.println("Country: " + getCountryName());
        System.out.println("Average price per day: " + getPrice() + " lei");

        String result = "";
        
        for (String activity : activities) {
            result = result.concat(activity + ", ");
        }
        System.out.println("Activities: " 
                + result.substring(0, result.length() - 2));
        System.out.println("Available period: " + getPeriod());
    }
}
