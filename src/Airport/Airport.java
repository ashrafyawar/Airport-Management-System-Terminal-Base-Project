package Airport;

import Client.Person;
import Client.User;

import java.util.Objects;

/**Airport Class that holds various information about airline.*/
public class Airport {



    /**Each airport has an AirportSystemStorage.*/
    AirportSystemStorage airportSystemStorage;

    /**Name of the airport.*/
    final String name;

    /**Airport Constructor.
     * @throws if given name is not avaiable.
     * @param name as name of the Airport.*/
    public Airport(String name , User airportAdmin) throws Exception {
        if(name!=null && !name.equals("")){
            this.name=name;
            airportSystemStorage = new AirportSystemStorage( new AirportAdmin( airportAdmin.getName() , airportAdmin.getSurname(),airportAdmin.getSSN()
                    ,airportAdmin.getPassword() , null) );
            airportSystemStorage.getAirportAdmin().setAirportSystemStorage(airportSystemStorage);
        }
        else  {
            throw (new Exception("Given name is not proper!"));
        }
    }

    // made public
    /**get method of Name,
     *  @return name.*/
    public String getName() {
        return name;
    }

    /**get method of AirportSystemStorage,
     * @return airportSystemStorage.*/
    public AirportSystemStorage getAirportSystemStorage() {
        return airportSystemStorage;
    }

    /**set method of AirportSystemStorage,
     * @param airportSystemStorage as new.*/
    public void setAirportSystemStorage(AirportSystemStorage airportSystemStorage) {
        this.airportSystemStorage = airportSystemStorage;
    }

    /** Method to control if given object is equal.
     * @param o to compare.
     * @return true, if equal.
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return name.equals(airport.name);
    }

    /**@return hashCode of name.*/
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**@return toString method of Airport.*/
    @Override
    public String toString() {
        return name;
    }
}
