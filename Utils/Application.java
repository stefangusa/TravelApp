package Utils;

import Location.City;
import Location.Country;
import Location.County;
import Location.Location;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*  Application class - where all the data from the app is stored   */
public class Application {
    private Map<String, Set<Location>> locations;   /* locations available  */
    private Map<String, Set<City>> cities;          /* cities with locations */
    private Map<String, Set<County>> counties;      /* counties with locations */
    private Map<String, Set<Country>> countries;    /* countries with locations */
    /*  Map with an activity as a key and a set of places where activity 
        is available as value   */
    private Map<String, Set<Location>> placesForActivity;
    
    public Application() {
        this.locations = new LinkedHashMap<String, Set<Location>>();
        this.cities = new LinkedHashMap<String, Set<City>>();
        this.counties = new LinkedHashMap<String, Set<County>>();
        this.countries = new LinkedHashMap<String, Set<Country>>();
        this.placesForActivity = new LinkedHashMap<String, Set<Location>>();
    }
    
    /*  Method that adds a location (an offer) to the app   */
    public void addLocation(Location location) {
        /* Hashcode is the first letter of the name (could pe first 2, 3 etc.)
            if there are many locations */
        String hashcode = location.getName()
                .toLowerCase()
                .substring(0, 1);
        
        /*  adds the location to the app storage   */
        if (locations.containsKey(hashcode) == false) {
            locations.put(hashcode, 
                    new TreeSet<Location>(LocationComparator.getInstance()));
        }
        locations.get(hashcode).add(location);
        
        /*  adds location in the placesForActivity map */
        for (String activity : location.getActivities()) {
            String formatted = activity.toLowerCase();
            
            if (placesForActivity.containsKey(formatted) == false) {
                placesForActivity.put(formatted, 
                        new TreeSet<Location>(PriceComparator.getInstance()));
            }
            placesForActivity.get(formatted).add(location);
        }
        
        String cityName = location.getCityName();
        String countyName = location.getCountyName();
        String countryName = location.getCountryName();
        
        /*  Adds the city to the cities if non existent    */
        hashcode = location.getCityName()
                .toLowerCase()
                .substring(0, 1);
        
        if (cities.containsKey(hashcode) == false) {
            cities.put(hashcode, 
                    new TreeSet<City>(CityComparator.getInstance()));
        }
        
        /*  takes the city from cities  */
        City city = findCity(cityName.toLowerCase());
        
        if (city == null) {
            city = new City(countryName, countyName, cityName);
            cities.get(hashcode).add(city);
        }
        /*  Adds location to top locations in the city  */
        city.addLocation(location);
        
        /*  Adds the county to the counties if non existent    */
        hashcode = location.getCountyName()
                .toLowerCase()
                .substring(0, 1);
        
        if (counties.containsKey(hashcode) == false) {
            counties.put(hashcode, 
                    new TreeSet<County>(CountyComparator.getInstance()));
        }
        /*  takes the county from counties  */
        County county = findCounty(countyName.toLowerCase());
        
        if (county == null) {
            county = new County(countryName, countyName);
            counties.get(hashcode).add(county);
        }
        /*  Adds location to top locations in the county  */
        county.addLocation(location);
        
        /*  Adds the country to the counties if non existent    */
        hashcode = location.getCountryName()
                .toLowerCase()
                .substring(0, 1);
        
        if (countries.containsKey(hashcode) == false) {
            countries.put(hashcode, 
                    new TreeSet<Country>(CountryComparator.getInstance()));
        }
        /*  takes the country from counties  */
        Country country = findCountry(countryName.toLowerCase());
        
        if (country == null) {
            country = new Country(countryName);
            countries.get(hashcode).add(country); 
        }       
        /*  Adds location to top locations in the country  */
        country.addLocation(location);
    }
    
    /**  Method that search for a city in cities map 
         if found, returns it, else null    */
    public City findCity(String cityName) {
        String hashcode = cityName.toLowerCase()
                .substring(0, 1);
        
        for (City c : cities.get(hashcode)) {
            if (c.getName().toLowerCase()
                    .compareTo(cityName) == 0) {
                return c;
            }
            else if (c.getName().toLowerCase()
                    .compareTo(cityName) > 0) {
                return null;
            }
        }
        return null;
    }
    
    /**  Method that search for a country in counties map 
         if found, returns it, else null    */
    public Country findCountry(String countryName) {
        String hashcode = countryName.toLowerCase()
                .substring(0, 1);
        
        for (Country c : countries.get(hashcode)) {
            if (c.getName().toLowerCase()
                    .compareTo(countryName) == 0) {
                return c;
            }
            else if (c.getName().toLowerCase()
                    .compareTo(countryName) > 0) {
                return null;
            }
        }
        return null;
    }

    /**  Method that search for a county in counties map 
         if found, returns it, else null    */
    public County findCounty(String countyName) {
        String hashcode = countyName.toLowerCase()
                .substring(0, 1);
        
        for (County c : counties.get(hashcode)) {
            if (c.getName().toLowerCase()
                    .compareTo(countyName) == 0) {
                return c;
            }
            else if (c.getName().toLowerCase()
                    .compareTo(countyName) > 0) {
                return null;
            }
        }
        return null;
    }
    
    /*  Method that prints details of the cheapest place 
        to spend ten days doing an activity */
    public void getCheapestPlace(String activity) {
        Set<Location> list = placesForActivity.get(activity);
        
        /*  prints the info of the first element in the set */
        if (list != null) {
            Iterator<Location> it = list.iterator();
            Location loc = it.next();
            double price = loc.getPrice();
           
            getInfo(loc.getName().toLowerCase());
            
            /* if there are more at the same price, prints them too */
            while (it.hasNext()) {
                loc = it.next();
                if (loc.getPrice() > price) {
                    break;
                }
                System.out.println();
                getInfo(loc.getName().toLowerCase());
            }      
            /* prints the total price   */
            System.out.println("TOTAL PRICE: " + price * 10);
        }
        else {
            System.out.println("There is no where you can "
                    + "practice this activity! :(");
        }
    }
    
    /*  Methods that prints details of a location   */
    public void getInfo(String locationName) {
        if (locationName.length() == 0) {
            System.out.println("Inexistent location!");
            return;
        }
        
        boolean found = false;
        String hashcode = locationName.toLowerCase()
                .substring(0, 1);
        Set<Location> list = locations.get(hashcode);
        
        /*  finds the location in the list (if existant)    
            and prints the details with the specific method in the class    */
        if (list != null) {
            for (Location location : list) {
                String locName = location.getName()
                        .toLowerCase();

                if (locName.compareTo(locationName) == 0) {
                    found = true;
                    location.printDetails();
                }

                if (locName.compareTo(locationName) > 0) {
                    break;
                }
            }
        }      
        if (found == false) {
            System.out.println("Inexistent location!");
        }
    }
        
    /*  Method calls the method that prints the top5 locations with 
        the city's topLocation argument    */
    public void top5City(String cityName, MyDate start, MyDate end) {
        City city = this.findCity(cityName);
        Set<Location> top = city.getTop();
        showTop5(top, start, end);
    }
 
    /*  Method calls the method that prints the top5 locations with 
        the county's topLocation argument    */
    public void top5County(String countyName, MyDate start, MyDate end) {
        County county = this.findCounty(countyName);
        Set<Location> top = county.getTop();
        showTop5(top, start, end);
    }
       
    /*  Method calls the method that prints the top5 locations with 
        the country's topLocation argument    */
    public void top5Country(String countryName, MyDate start, MyDate end) {
        Country country = this.findCountry(countryName);
        Set<Location> top = country.getTop();
        showTop5(top, start, end);
    }
    
    /*  Method prints top5 locations, by price, from the list, that are 
        available in the given period   */
    public void showTop5(Set<Location> topLocations, 
            MyDate startDate, MyDate endDate) {
        int counter = 0;
        boolean overEndDate = false, overStartDate = false;
        List<Location> result = new ArrayList<Location>();
        
        /* iterates through the set */
        for (Location loc : topLocations) {
            /*  compares the start date of the offer to be in the 
                period available, and if so adds the offer to the result
                and increments counter */
            if (loc.getStartDate().compareTo(startDate) <= 0 &&
                    loc.getEndDate().compareTo(startDate) > 0) {
                result.add(loc);
                counter++;
                
                /*  if the last day of the requested trip is after the 
                    offer expires, the counter is decremented   */
                if (loc.getEndDate().compareTo(endDate) <= 0) {
                    
                    /*  check if the message was previously printed */
                    if (overEndDate == false) {
                        overEndDate = true;
                        System.out.println("At least one offer ends earlier!");
                    }
                    counter--;
                }                
            }
            /*  if the first day of the requested trip is before the offer 
                starts, but the last day is insider the available offer, 
                adds the offer to the result  */
            else if (loc.getStartDate().compareTo(endDate) < 0 &&
                        loc.getEndDate().compareTo(endDate) >= 0) {
                result.add(loc);
                
                /*  check if the message was previously printed */
                if (overStartDate == false) {
                    overStartDate = true;
                    System.out.println("At least one offer begins later!");
                }
            }
            
            /*  when the counter is 5, the top5 is full */
            if (counter == 5) {
                break;
            }
        }
        
        /*  print details of the locations in the result list   */
        for (Location loc : result) {
            loc.printDetails();
            System.out.println("");
        }
    }
}
