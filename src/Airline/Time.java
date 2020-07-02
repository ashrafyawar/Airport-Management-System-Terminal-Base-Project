package Airline;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Time class for operating flight time issues
 * which implements comparable for managing time
 * comparing issues
 * @author Bilal Bayrakdar
 */
public class Time implements Comparable<Time>{
    int hour,minute;

    Time(){setCurrentTime();}

    Time(int hour,int minute){
        this.hour=hour;
        this.minute=minute;
    }

    public void setCurrentTime(){
        GregorianCalendar cal = new GregorianCalendar();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
    }

    @Override
    public int compareTo(Time time) {
        return 60*(hour-time.hour)+minute-minute;
    }

    @Override
    public boolean equals(Object obj) {
        try{
            Time temp = (Time) obj;
            return temp.hour==hour && temp.minute==minute;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%d:%d",hour,minute);
    }
}
