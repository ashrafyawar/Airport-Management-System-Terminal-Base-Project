package Airline;

public class DateTime implements Comparable<DateTime>{
    private Date date;
    private Time time;

    public DateTime(){
        date = new Date();
        time = new Time();
    }

    public DateTime(Date d_val, Time t_val){
        date = d_val;
        time = t_val;
    }

    public Time getTime() {
        return time;
    }
    public Date getDate() {
        return date;
    }
    public void setTime(Time time) {
        this.time = time;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public DateTime(int year,int month,int day, int hour, int minute){
        try {
            date = new Date(year,month,day);
            time = new Time(hour,minute);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void setCurrentDateTime(){
        getDate().setCurrentDate();
        getTime().setCurrentTime();
    }

    @Override
    public int compareTo(DateTime dateTime) {return 24*getDate().compareTo(dateTime.getDate())+getTime().compareTo(dateTime.getTime());}

    @Override
    public String toString() {
        return getDate().toString()+" - "+getTime().toString();
    }

}
