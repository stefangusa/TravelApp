
import Utils.Application;
import Utils.MyDate;
import Location.Location;
import static Utils.Parser.parseTop5;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/* Main class - contains main method only!  */
public class Main {

    public static void main(String[] args) {
        BufferedReader buf;
        Application app = new Application();
        
        /* Firstly, read from input file the locations in the offer */
        try {
            buf = new BufferedReader(new FileReader("input.txt"));
            String line;
            String[] words;
            
            /* Reading is line by line  */
            while ((line = buf.readLine()) != null) {
                /* fields are separated by pipe, so we split the line */
                words = line.split("\\|");
                
                /* take the info */
                String locationName = words[0];
                /*  position is given in (city, county, country) form   */
                String[] position = words[1].split(", ");
                double price = Double.parseDouble(words[2]);
                /*  acrivities are separated by comma   */
                String[] activities = words[3].split(", ");
                /*  available period in separated by line   */
                String[] dates = words[4].split(" - "); 
                /*  one date is given in (day, month, year) form    */
                String[] start = dates[0].split("\\.");
                String[] end = dates[1].split("\\.");
                
                /*  create date objects using a class created by me  */
                MyDate startDate = new MyDate(Integer.parseInt(start[2]),
                                            Integer.parseInt(start[1]),
                                            Integer.parseInt(start[0]));
                
                MyDate endDate = new MyDate(Integer.parseInt(end[2]),
                                        Integer.parseInt(end[1]),
                                        Integer.parseInt(end[0]));
                
                /*  add location to the app */
                app.addLocation(new Location(position[2], position[1], 
                                    position[0], locationName, price, 
                                    activities, startDate, endDate));
            }
        } catch (FileNotFoundException ex) {
            System.exit(-1); 
        } catch (IOException ex) {
            System.exit(-1); 
        }
        
        /* Begins the "user interface"  */
        System.out.println("============ WELCOME ! =============");
        System.out.println("Commands: ");
        System.out.println("info - Reveals information about a location.");
        System.out.println("top5 - Shows the top 5 offers in a "
                + "city/county/country.");
        System.out.println("ten-day - Reveals the location having "
                + "the cheapest cost for a 10-day trip.");
        System.out.println("exit: Close the application.");
        
        /* the application loop */

        Scanner sc = new Scanner(System.in);
        while (true) {
            /* first insert a command   */
            System.out.println();
            System.out.println("Insert one command (info, "
                    + "top5, ten-day, exit): ");
            
            String command = sc.nextLine();
            
            /* execute the command taken    */
            switch (command.toLowerCase()) {
                /*  requires a location name and prints the details of it   */
                case "info":
                    System.out.println("Insert a location: ");
                    app.getInfo(sc.nextLine().toLowerCase());
                    break;
                    
                /*  parses the input with a specific method and prints the top5 */    
                case "top5":
                    parseTop5(app, sc);
                    break;
                    
                /*  requires an activity and prints details of the place 
                    with the cheapest price for a ten-day trip  */
                case "ten-day":
                    System.out.println("Insert an activity: ");
                    app.getCheapestPlace(sc.nextLine().toLowerCase());
                    break;
                    
                /*  exits the app   */
                case "exit":
                    System.out.println("Thank you for using our product!");
                    System.exit(0);
                    break;
                
                default:
                    System.out.println("Inexistent command");
            }          
        }
    }
}
