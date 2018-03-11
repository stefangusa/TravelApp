package Utils;

import Location.Location;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Application {
    private Map<String, Map<String, Map<String, List<Location>>>> locations;
    private Map<String, Set<Location>> placesForActivity;
    
    public Application() {
        this.locations = new LinkedHashMap<String, Map<String, Map<String, List<Location>>>>();
        this.placesForActivity = new LinkedHashMap<String, Set<Location>>();
    }
    
    public void addLocation(Location location) {
        String country = location.getCountry();
        String county = location.getCounty();
        String city = location.getCity();
        
        if (locations.containsKey(country) == false) {
            locations.put(country, new LinkedHashMap<String, Map<String, List<Location>>>());
        }
        if (locations.get(country).containsKey(county) == false) {
            locations.get(country).put(county, new LinkedHashMap<String, List<Location>>());
        }
        if (locations.get(country).get(county).containsKey(city) == false) {
            locations.get(country).get(county).put(city, new ArrayList<Location>());
        }
        locations.get(country).get(county).get(city).add(location);
        
        for (String activity : location.getActivities()) {
            String formatted = activity.toLowerCase();
            if (placesForActivity.containsKey(formatted) == false) {
                placesForActivity.put(formatted, new TreeSet<Location>(new PriceComparator()));
            }
            placesForActivity.get(formatted).add(location);
        }
    }
    
    public void getCheapestPlace(String activity) {
        Set<Location> list = placesForActivity.get(activity);
        
        if (list != null) {
            Iterator<Location> it = list.iterator();
            Location loc = it.next();
            double price = loc.getPrice();
            getInfo(loc.getName());
            
            while (it.hasNext()) {
                loc = it.next();
                if (loc.getPrice() > price) {
                    break;
                }
                System.out.println();
                getInfo(loc.getName());
            }
                        
            System.out.println("TOTAL PRICE: " + price * 10);
        }
        else {
            System.out.println("There is no where you can practice this activity! :(");
        }
    }
    
    public void getInfo(String name) {
        Character index = name.toUpperCase().charAt(0);
        Location location = null;
        List<Location> list = locations.get(index);
        
        if (list != null) {
            for (Location loc : list) {
                if (loc.getName().toLowerCase().compareTo(name.toLowerCase()) == 0) {
                    location = loc;
                    break;
                }
            }
        }
        
        if (location == null) {
            System.out.println("Inexistent location!");
        }
        else {
            System.out.println("Name: " + location.getName());
            System.out.println("City: " + location.getCity());
            System.out.println("County: " + location.getCounty());
            System.out.println("Country: " + location.getCountry());
            System.out.println("Average price per day: " + location.getPrice() + " lei");
            
            List<String> activities = location.getActivities();
            String result = "";
            for (String activity : activities) {
                result = result.concat(activity + ", ");
            }
            System.out.println("Activities: " + result.substring(0, result.length() - 2));
            System.out.println("Available period: " + location.getPeriod());
        }
    }
}
