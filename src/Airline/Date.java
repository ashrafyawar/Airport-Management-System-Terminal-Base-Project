package Airline;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date implements Comparable<Date>{
    private int year,month,day;

    Date(){setCurrentDate();}

    Date(int y_val, int m_val, int d_val) throws Exception {
        year = y_val; // neagtiflik kontrol edilecek
        if(month>0 && month<13)
            month = m_val;
        else
            throw (new Exception("Wrong month value entered!"));
        if(day<=monthDay() && day>0)
            day = d_val;
        else
            throw (new Exception("wrong day value entered!"));
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

    public void setDay(int day) {
        this.day = day;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;
    }

    private int monthDay(){
        if(month==1 ||month==3||month==5||month==7||month==9||month==11)
            return 31;
        else if(month == 2 && year%4==0)
            return 28;
        else if(month == 2)
            return 27;
        else
            return 30;
    }

    public void setCurrentDate(){
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int compareTo(Date date) {
        return 365*(year- date.getYear())+monthDay()-date.monthDay()+day-date.day;
    }

    @Override
    public String toString() {
        return year+"/"+month+"/"+day;
    }
}
