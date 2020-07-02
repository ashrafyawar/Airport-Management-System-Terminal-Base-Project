package Airline;
import java.lang.Math.*;
import java.util.Objects;

/**
 * Destination properties class which is designed
 * according to perspective of current airport.
 * @author Nevzat Seferoglu and Bilal Bayrakdar
 * @version 1.0
 */
public class Destination {


    private String name;
    private Double latitude;
    private Double longitude;

    public Destination( String name , Double latitude , Double longitude) {

        //exception handling.
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;

    }
    public Double calculateDistance( Destination targetDestination ) {

        return Math.sqrt(Math.pow(targetDestination.getLongitude() - getLongitude(),2));
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public String getName() {
        return name;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object obj) {

        if( obj == this ) {
            return true;
        }

        if( !(obj instanceof Destination) ) {
            return false;
        }

        return ((Destination) obj).latitude.equals(latitude) &&
                ((Destination) obj).longitude.equals(longitude);

    }

    @Override
    public String toString() {
        return "Name : " + name + " \n" +
                "Latitude : " + latitude + " \n" +
                "Longitude : " + longitude + " \n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude);
    }
}
