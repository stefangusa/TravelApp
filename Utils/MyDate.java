package Utils;

/*  Class MyDate - creates a date only with day, month, year
    and no constraints (should be previously checked if the date exists)    */
public class MyDate {
    private final int day;
    private final int month;
    private final int year;
    
    public MyDate(int year, int month, int day) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }    
    
    public int getMonth() {
        return month;
    }
    
    public int getYear() {
        return year;
    }
    
    /*  Compares two dates  */
    public int compareTo(MyDate date) {
        if (this.year - date.year != 0) {
            return this.year - date.year;
        }
        
        if (this.month - date.month != 0) {
            return this.month - date.month;
        }
        
        return this.day - date.day;
    }
}
