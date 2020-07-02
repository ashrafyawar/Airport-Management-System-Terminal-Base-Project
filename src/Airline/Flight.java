package Airline;

import java.util.Collection;
import java.lang.Math;
import java.util.Comparator;
import java.util.List;

/**
 * Class for flight representation
 * @author Bilal Bayrakdar
 * version 1.0
 */
public class Flight implements  Comparable<Flight>{

    private static FlightCompareTo flightCompareTo;

    private Aircraft aircraft;
    private Destination source;
    private Destination destination;
    private String company;
    private String nameTrademark;
    private DateTime dateTime;
    private int capacity;
    private int usedCapacity;
    private int price;

    private List<AirlinePersonnel> pilots;
    private List<AirlinePersonnel> cabin_crew;


    /**
     * Flight Constructor which requires all parameters
     * @param company company name
     * @param destination
     * @param capacity
     * @param price
     * @param crew
     * @param pilots
     */
    public Flight(String company, String nameTrademark, Destination source, Destination destination,DateTime dateTime, int capacity, int price,
                  List<AirlinePersonnel> pilots, List<AirlinePersonnel> crew){
        this.usedCapacity = 0;
        this.company = company;
        this.nameTrademark = nameTrademark;
        this.source = source;
        this.destination=destination;
        this.dateTime = dateTime;
        this.capacity=capacity;
        this.price=price;

        this.pilots = pilots;
        this.cabin_crew =crew;

        // this.pilots=pilots;// array copy should be performed
        // this.cabin_crew=cabin_crew; // array copy should be performed
    }

    public static FlightCompareTo getFlightCompareTo() {
        return flightCompareTo;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public Destination getSource() {
        return source;
    }

    public Destination getDestination() {
        return destination;
    }

    public String getCompany() {
        return company;
    }

    public String getNameTrademark() {
        return nameTrademark;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getUsedCapacity() {
        return usedCapacity;
    }

    public int incUsedCapacity() {return ++usedCapacity;}

    public int decUsedCapacity() {return --usedCapacity;}

    public int getPrice() {
        return price;
    }

    public Collection<AirlinePersonnel> getCabin_crew() {
        return cabin_crew;
    }

    public Collection<AirlinePersonnel> getPilots() {
        return pilots;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setnameTrademark(String nameTrademark) {
        this.nameTrademark = nameTrademark;
    }

    public void setAircraft(Aircraft aircraft){
        this.aircraft = aircraft;
    }

    public void setSource(Destination source) {
        this.source = source;
    }
    public void setDestination(Destination destination) {
        this.destination = destination;
    }
    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setPilots(List<AirlinePersonnel> pilots) {
        this.pilots = pilots;
    }
    public void setCabin_crew(List<AirlinePersonnel> cabin_crew) {
        this.cabin_crew = cabin_crew;
    }
    public static void setFlightCompareTo(FlightCompareTo flightCompareTo) {
        Flight.flightCompareTo = flightCompareTo;
    }

    @Override
    public boolean equals(Object obj) {
        try{
            Flight temp = (Flight)obj;
            if(temp.nameTrademark.equals(nameTrademark) && temp.aircraft.equals(aircraft) && temp.capacity ==capacity && temp.dateTime.equals(dateTime))
                return true;
            return false;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public String toString() {
        return "Company:\t" + company + "\n" +
                "Plane type:\t" + aircraft + "\n" +
                "Destination:\t" + destination + "\n" +
                "DateTime:\t" + dateTime + "\n" +
                "Capacity:\t" + capacity + "\n" +
                "Price:\t" + price + "\n" +
                "Pilots:\t" + pilots + "\n" +
                "Cabin Crew:\t" + cabin_crew + "\n";
    }

    public String customerShow(){
        return "Company:\t" + company + " " +
                "Plane type:\t" + aircraft + " " +
                "Destination:\t" + destination + " " +
                "DateTime:\t" + dateTime + " " +
                "Capacity:\t" + capacity + " " +
                "Price:\t" + price + " ";
    }

    public boolean isTicketAble() /*throws Exception*/ {
        DateTime crnt = new DateTime();
        return (crnt.compareTo(getDateTime())<1) && getUsedCapacity()<getCapacity();
    }

    public int getScore(){

        int score = 0;
        int temp;

        temp = (int) distance()/1000;

        if( temp < 0 ) temp = 0;
        else if( temp > 10) temp = 10;
        score += temp;

        temp = getCapacity()/50;

        if( temp < 0 ) temp = 0;
        else if( temp > 10) temp = 10;
        score += temp;

        temp = getPrice()/200;

        if( temp < 0 ) temp = 0;
        else if( temp > 10) temp = 10;
        score += temp;

        return score;
    }

    /**
     * Calculates the distance between source and destination points
     * @return the calculate distance
     */
    public double distance() {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        double lon1 = Math.toRadians(source.getLongitude());
        double lon2 = Math.toRadians(destination.getLongitude());
        double lat1 = Math.toRadians(source.getLatitude());
        double lat2 = Math.toRadians(source.getLatitude());

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }

    @Override
    public int compareTo(Flight flight) {
        switch (getFlightCompareTo()){
            case ACCORDING_TO_DATE:
                return dateTime.compareTo(flight.getDateTime());
            default:
            case ACCORDING_TO_ALPHA:
                return customerShow().compareTo(flight.customerShow());
            case ACCORDING_TO_PRICE:
                return getPrice()-flight.getPrice();
            case ACCORDING_TO_REV_ALPHA:
                return -dateTime.compareTo(flight.getDateTime());
        }

    }

    /**
     * To get Comparator for using sort algorithms.
     * Switch structure used to determine which feature is
     * gonna be used in sorting
     * @return Comparator to make sorting
     */
    public static Comparator<Flight> getComparator(){
        Comparator<Flight> comparator = new Comparator<Flight>() {
            @Override
            public int compare(Flight t0, Flight t1) {
                switch (getFlightCompareTo()){
                    default:
                    case ACCORDING_TO_ALPHA:
                        return t0.customerShow().compareTo(t1.customerShow());
                    case ACCORDING_TO_REV_ALPHA:
                        return -t0.customerShow().compareTo(t1.customerShow());
                    case ACCORDING_TO_PRICE:
                        return t0.getPrice()-t1.getPrice();
                    case ACCORDING_TO_DATE:
                        return t0.getDateTime().compareTo(t1.getDateTime());
                }
            }
        };
        return comparator;
    }
}