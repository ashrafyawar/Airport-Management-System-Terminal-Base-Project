package Airport;

import Airline.*;

/**AirportAdminInterface that is used by AirportAdmin.
 * This interface has various methods that Admins should have.*/
public interface AirportAdminInterface {

    /**Method that adds way to MapGraph.
     * @param destination to add destination for graph.
     * */
    public void addWay(Destination destination) throws Exception;

    /** Method that adds an Airport Personnel from AirportPersonnel class.
     * @param airportPersonnel The personnel is going to be added.
     * @return boolean value,based on if adding was succesfull(true) or not(false).
     * */
    boolean addAirportPersonnel( AirportPersonnel airportPersonnel ) throws Exception;

    /** Method that removes an Airport Personnel from AirportPersonnel class.
     * @param airportPersonnel The personnel that is going to be removed.
     * @return boolean value,based on if removing was succesfull(true) or not(false).
     * */
    boolean removeAirportPersonnel( AirportPersonnel airportPersonnel ) throws Exception;

    /** Method for shops that is going to be added.
     * @param shop A new shop to be added.
     * @return boolean value,based on if adding was succesfull(true) or not(false).
     * */
    boolean addShop( Shop shop ) throws Exception;

    /** Method for shops that is going to be removed.
     * @param shop Shop to be removed.
     * @return boolean value,based on if removing was succesfull(true) or not(false).
     * */
    boolean removeShop( Shop shop ) throws Exception;

    /** Method for airlines that is going to be added.
     * @param airline A new airline to be added.
     * @return boolean value,based on if adding was succesfull(true) or not(false).
     * */
    boolean addAirline( Airline airline ) throws Exception;

    /** Method for airlines that is going to be removed.
     * @param airline Airline to be removed.
     * @return boolean value,based on if removing was succesfull(true) or not(false).
     * */
    boolean removeAirline( Airline airline ) throws Exception;

    /** Method that adds a new destination from destinations.
     * @param destination As the destination to be added.
     * @return boolean value,based on if adding was succesfull(true) or not(false).
     * */
    boolean addDestination( Destination destination ) throws Exception;

    /** Method that removes a destination from destinations.
     * @param destination As the destination to be removed.
     * @return boolean value,based on if removing was succesfull(true) or not(false).
     * */
    boolean removeDestination( Destination destination ) throws Exception;

    /** Method that sets commission rate to given airline.
     * @param airline The airline that is going to have commision rate.
     * @return boolean value, Returns false if airline is not found.
     * */
    boolean setCommissionRate( Airline airline , final double commissionRate ) throws Exception;

    /** Method that return airports Funding.
     * @return double value of money , as 1000x $.
     * */
    double getAirportFund();

}
