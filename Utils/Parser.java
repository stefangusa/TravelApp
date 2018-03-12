package Utils;

import java.util.Scanner;

public class Parser {

    public static void parseTop5(Application app, Scanner sc) {
        int day, month, option, year;
        MyDate startDate, endDate;
        String line, place;

        while (true) {
            System.out.println("Choose 1 - for country");
            System.out.println("2 - for county");
            System.out.println("3 - for city");
            System.out.print("Your choice: ");

            line = sc.nextLine();
            try {
                option = Integer.parseInt(line);
                if (option < 1 || option > 3) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter 1, 2 or 3!");
                continue;
            }

            if (option == 1) {
                System.out.println("Choose a country: ");
                place = sc.nextLine().toLowerCase();
                if (app.findCountry(place) == null) {
                    System.out.println("Country not in our offer!");
                    continue;
                }
            } else if (option == 2) {
                System.out.println("Choose a county: ");
                place = sc.nextLine().toLowerCase();
                if (app.findCounty(place) == null) {
                    System.out.println("County not in our offer!");
                    continue;
                }
            } else {
                System.out.println("Choose a city: ");
                place = sc.nextLine().toLowerCase();
                if (app.findCity(place) == null) {
                    System.out.println("City not in our offer!");
                    continue;
                }
            }

            System.out.println("Choose a period: (day, month, year)");
            System.out.println("Day in: (numeral)");
            line = sc.nextLine();
            try {
                day = Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number!");
                continue;
            }

            System.out.println("Month in: (numeral)");
            line = sc.nextLine();
            try {
                month = Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number!");
                continue;
            }

            System.out.println("Year in: (numeral)");
            line = sc.nextLine();
            try {
                year = Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number!");
                continue;
            }

            if (day < 1 || day > 31 || month < 1 || month > 12
                    || year < 2018 || day > 28 && month == 2
                    && year % 4 != 0 || day > 29 && month == 2
                    && year % 4 == 0 || day > 30 && (month == 4
                    || month == 6 || month == 9 || month == 1)) {
                System.out.println("Please enter a valid date in!");
                continue;
            }

            startDate = new MyDate(year, month, day);

            System.out.println("Day out: (numeral)");
            line = sc.nextLine();
            try {
                day = Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number!");
                continue;
            }

            System.out.println("Month out: (numeral)");
            line = sc.nextLine();
            try {
                month = Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number!");
                continue;
            }

            System.out.println("Year out: (numeral)");
            line = sc.nextLine();
            try {
                year = Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number!");
                continue;
            }

            if (day < 1 || day > 31 || month < 1 || month > 12
                    || year < 2018 || day > 28 && month == 2
                    && year % 4 != 0 || day > 29 && month == 2
                    && year % 4 == 0 || day > 30 && (month == 4
                    || month == 6 || month == 9 || month == 1)) {
                System.out.println("Please enter a valid date!");
                continue;
            }

            endDate = new MyDate(year, month, day);

            if (startDate.compareTo(endDate) >= 0) {
                System.out.println("Date out should be later than date in!");
                continue;
            }

            switch (option) {
                case 1:
                    app.top5Country(place, startDate, endDate);
                    break;
                case 2:
                    app.top5County(place, startDate, endDate);
                    break;
                default:
                    app.top5City(place, startDate, endDate);
                    break;
            }
            break;
        }
    }
}
