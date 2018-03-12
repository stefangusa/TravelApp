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

public class Application {
    private Map<String, Set<Location>> locations;
    private Map<String, Set<City>> cities;
    private Map<String, Set<County>> counties;
    private Map<String, Set<Country>> countries;
    private Map<String, Set<Location>> placesForActivity;
    
    public Application() {
        this.locations = new LinkedHashMap<String, Set<Location>>();
        this.cities = new LinkedHashMap<String, Set<City>>();
        this.counties = new LinkedHashMap<String, Set<County>>();
        this.countries = new LinkedHashMap<String, Set<Country>>();
        this.placesForActivity = new LinkedHashMap<String, Set<Location>>();
    }
    
    public void addLocation(Location location) {
        String hashcode = location.getName()
                .toLowerCase()
                .substring(0, 1);
        
        if (locations.containsKey(hashcode) == false) {
            locations.put(hashcode, 
                    new TreeSet<Location>(NameComparator.getInstance()));
        }
        locations.get(hashcode).add(location);
        
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
        
        hashcode = location.getCityName()
                .toLowerCase()
                .substring(0, 1);
        
        if (cities.containsKey(hashcode) == false) {
            cities.put(hashcode, 
                    new TreeSet<City>(NameComparator.getInstance()));
        }
        
        City city = findCity(cityName.toLowerCase());
        
        if (city == null) {
            city = new City(countryName, countyName, cityName);
            cities.get(hashcode).add(city);
        }
        city.addLocation(location);
        
        hashcode = location.getCountyName()
                .toLowerCase()
                .substring(0, 1);
        
        if (counties.containsKey(hashcode) == false) {
            counties.put(hashcode, 
                    new TreeSet<County>(NameComparator.getInstance()));
        }
        
        County county = findCounty(countyName.toLowerCase());
        
        if (county == null) {
            county = new County(countryName, countyName);
            counties.get(hashcode).add(county);
        }
        county.addLocation(location);
        
        hashcode = location.getCountryName()
                .toLowerCase()
                .substring(0, 1);
        
        if (countries.containsKey(hashcode) == false) {
            countries.put(hashcode, 
                    new TreeSet<Country>(NameComparator.getInstance()));
        }
        
        Country country = findCountry(countryName.toLowerCase());
        
        if (country == null) {
            country = new Country(countryName);
            countries.get(hashcode).add(country); 
        }       
        country.addLocation(location);
    }
    
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
    
    public void getCheapestPlace(String activity) {
        Set<Location> list = placesForActivity.get(activity);
        
        if (list != null) {
            Iterator<Location> it = list.iterator();
            Location loc = it.next();
            double price = loc.getPrice();
           
            getInfo(loc.getName().toLowerCase());
            
            while (it.hasNext()) {
                loc = it.next();
                if (loc.getPrice() > price) {
                    break;
                }
                System.out.println();
                getInfo(loc.getName().toLowerCase());
            }      
            System.out.println("TOTAL PRICE: " + price * 10);
        }
        else {
            System.out.println("There is no where you can "
                    + "practice this activity! :(");
        }
    }
    
    public void getInfo(String locationName) {
        if (locationName.length() == 0) {
            System.out.println("Inexistent location!");
            return;
        }
        
        boolean found = false;
        String hashcode = locationName.toLowerCase()
                .substring(0, 1);
        Set<Location> list = locations.get(hashcode);
        
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
        
    public void top5City(String cityName, MyDate start, MyDate end) {
        City city = this.findCity(cityName);
        Set<Location> top = city.getTop();
        showTop5(top, start, end);
    }
    
    public void top5County(String countyName, MyDate start, MyDate end) {
        County county = this.findCounty(countyName);
        Set<Location> top = county.getTop();
        showTop5(top, start, end);
    }
        
    public void top5Country(String countryName, MyDate start, MyDate end) {
        Country country = this.findCountry(countryName);
        Set<Location> top = country.getTop();
        showTop5(top, start, end);
    }
    
    public void showTop5(Set<Location> topLocations, 
            MyDate startDate, MyDate endDate) {
        int counter = 0;
        boolean overEndDate = false, overStartDate = false;
        List<Location> result = new ArrayList<Location>();
        
        for (Location loc : topLocations) {
            if (loc.getStartDate().compareTo(startDate) <= 0 &&
                    loc.getEndDate().compareTo(startDate) > 0) {
                result.add(loc);
                counter++;
                
                if (loc.getEndDate().compareTo(endDate) <= 0) {
                    if (overEndDate == false) {
                        overEndDate = true;
                        System.out.println("At least one offer ends earlier!");
                    }
                    counter--;
                }                
            }
            else if (loc.getStartDate().compareTo(endDate) < 0 &&
                        loc.getEndDate().compareTo(endDate) >= 0) {
                result.add(loc);
                
                if (overStartDate == false) {
                    overStartDate = true;
                    System.out.println("At least one offer begins later!");
                }
            }
            
            if (counter == 5) {
                break;
            }
        }
        
        for (Location loc : result) {
            loc.printDetails();
            System.out.println("");
        }
    }
}
