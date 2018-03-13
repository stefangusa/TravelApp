package Utils;

import Location.County;
import java.util.Comparator;

/* Singleton class that comparest two counties by their name   */
public class CountyComparator implements Comparator<County> {

    private static CountyComparator p = new CountyComparator();
    
    public static CountyComparator getInstance() {
        return p;
    }
    
    @Override
    public int compare(County loc1, County loc2) {
        return loc1.getName().compareTo(loc2.getName());
    }
}
