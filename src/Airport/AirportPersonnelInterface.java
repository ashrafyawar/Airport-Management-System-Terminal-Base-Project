package Airport;

/**AirportPersonnelInterface that is used by AirportPersonnel.*/
public interface AirportPersonnelInterface {

    /** Method that dismisses given username.
     * @param username to dismiss.
     * @return boolean value,based on if dismissing was succesfull(true) or not(false).
     * */
    boolean dismissCustomer( final String username ) throws Exception;

}
