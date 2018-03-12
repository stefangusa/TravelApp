
import Utils.Application;
import Utils.MyDate;
import Location.Location;
import static Utils.Parser.parseTop5;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BufferedReader buf;
        Application app = new Application();
        
        try {
            buf = new BufferedReader(new FileReader("input.txt"));
            String line;
            String[] words;
            while ((line = buf.readLine()) != null) {
                words = line.split("\\|");
                
                String locationName = words[0];
                String[] position = words[1].split(", ");
                double price = Integer.parseInt(words[2]);
                String[] activities = words[3].split(", ");
                String[] dates = words[4].split(" - "); 
                
                String[] start = dates[0].split("\\.");
                String[] end = dates[1].split("\\.");
                
                MyDate startDate = new MyDate(Integer.parseInt(start[2]),
                                            Integer.parseInt(start[1]),
                                            Integer.parseInt(start[0]));
                
                MyDate endDate = new MyDate(Integer.parseInt(end[2]),
                                        Integer.parseInt(end[1]),
                                        Integer.parseInt(end[0]));
                
                app.addLocation(new Location(position[2], position[1], 
                                    position[0], locationName, price, 
                                    activities, startDate, endDate));
            }
        } catch (FileNotFoundException ex) {
            System.exit(-1); 
        } catch (IOException ex) {
            System.exit(-1); 
        }
        
        System.out.println("============ WELCOME ! =============");
        System.out.println("Commands: ");
        System.out.println("info - Reveals information about a location.");
        System.out.println("top5 - Shows the top 5 offers in a "
                + "city/county/country.");
        System.out.println("ten-day - Reveals the location having "
                + "the cheapest cost for a 10-day trip.");
        System.out.println("exit: Close the application.");
        
        while (true) {
            System.out.println();
            System.out.println("Insert one command (info, "
                    + "top5, ten-day, exit): ");
            
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            
            switch (command.toLowerCase()) {
                case "info":
                    System.out.println("Insert a location: ");
                    app.getInfo(sc.nextLine().toLowerCase());
                    break;
                    
                case "top5":
                    parseTop5(app, sc);
                    System.out.println("");
                    break;
                    
                case "ten-day":
                    System.out.println("Insert an activity: ");
                    app.getCheapestPlace(sc.nextLine().toLowerCase());
                    break;
                    
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
