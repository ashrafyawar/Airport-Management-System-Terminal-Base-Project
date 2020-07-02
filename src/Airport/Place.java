package Airport;
import java.util.Objects;

/**Subclass of Shop.*/
public class Place implements  PlaceInterface , Comparable<Place>{

    /**PlaceType holds as enum; as parks, restrooms..*/
    PlaceType type;

    /**Every place has an id.*/
    String id;

    /**Place constructor.
     * @param type as new Place type.
     * @param id as new id.
     * */
    public Place( PlaceType type , final String id ) {

        this.type = type;
        this.id = id;

    }

    /**toString method for Place. It just prints id and place type.
     * @return string format.*/
    public String toString(){
        return type.toString() + ",id:" + getID();
    }

    @Override
    public String getType() {
        return type.toString();
    }

    @Override
    public String getID() {
        return id;
    }

    /**Method to set a new ID to place.
     * @param id as new id.*/
    public void setId(String id) {
        this.id = id;
    }

    /**Method to set a new Type to place.
     * @param type as new type for place.*/
    public void setType(PlaceType type) {
        this.type = type;
    }

    /** Method that retursn hashcode of object.
     * @return hashCode, as integer.*/
    @Override
    public int hashCode() {
        return Objects.hash( type, id);
    }

    /** Method to compare an object to this to check if they are equal.
     * @param o Object to compare.
     * @return true if they are equal.*/
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        return type == place.type &&
                id.equals(place.id);
    }


    /**Compare Method to place.
     * @param place to compare,
     * @return integer value of comparison.
     * */
    public int compareTo( Place place ) {

        if( place != null ) {
            return this.getType().compareTo( place.getType() );
        } else {
            return 1;
        }

    }
}
