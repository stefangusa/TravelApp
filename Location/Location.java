package Location;

import Utils.MyDate;
import java.util.Arrays;
import java.util.List;

/*  Location class */
public class Location extends City {
    private final String name;
    private final double price;
    private final List<String> activities;
    private final MyDate startDate;
    private MyDate endDate;
    
    /*  Calls the constructor of city, county and then country before 
        setting up local variables    */
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
       
    /*  Method that returns activities in a location */
    public List<String> getActivities() {
        return this.activities;
    }
        
   /* Method that returns city's (where the location is) name  */
    public String getCityName() {
        return super.getName();
    }
    
   /* Method that returns county's (where the location is) name  */
    @Override
    public String getCountyName() {
        return super.getCountyName();
    }
    
   /* Method that returns country's (where the location is) name  */
    @Override
    public String getCountryName() {
        return super.getCountryName();
    }
    
    /*  Method that returns the last day in the offer  */
    public MyDate getEndDate() {
        return this.endDate;
    }
    
    /* Method that returns location's name  */
    @Override
    public String getName() {
        return this.name;
    }
    
    
    /*  Method that returns the available period as a String    */
    public String getPeriod() {
        String result = String.valueOf(startDate.getDay());
        
        result = result.concat("." + String.valueOf(startDate.getMonth()) +
                                "." + String.valueOf(startDate.getYear()) +
                                " - " + String.valueOf(endDate.getDay()) +
                                "." + String.valueOf(endDate.getMonth()) +
                                "." + String.valueOf(endDate.getYear()));
        return result;       
    }
     
    /*  Method that returns the price   */
    public double getPrice() {
        return this.price;
    }
    
    /*  Method that returns the first day in the offer  */
    public MyDate getStartDate() {
        return this.startDate;
    }
    
    /*  Method that prints the details of the offer */
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
