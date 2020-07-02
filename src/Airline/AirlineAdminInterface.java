package Airline;

public interface AirlineAdminInterface {

    /**
     * Recruit a personnel.
     * @param personnel Airline Personnel.
     * @return true if personnel added , otherwise return false.
     */
    public boolean recruitPersonnel( AirlinePersonnel personnel ) throws Exception;

    /**
     * Dismiss a personnel.
     * @param personnel Airline Personnel.
     * @return true if removed otherwise return false.
     */
    public boolean dismissPersonnel( AirlinePersonnel personnel ) throws Exception;

    /**
     * Add a new Destination.
     * @param destination a new destination.
     * @return Not clear and specified.
     */
    public boolean addDestination( Destination destination );

    /**
     * Remove a destination from the system.
     * @param destination destination.
     * @return
     */
    public boolean removeDestination(Destination destination);

    /**
     * Get a new aircraft.
     * @return Not clear and specified.
     */
    public boolean add_aircraft( Double wingspan,Double passengerCapacity,Double emptyWeightAsKg , Double maxFuelCapacity );

    /**
     * Remove an aircraft from the system.
     * @return true if airline does exist otherwise return false.
     */
    public boolean remove_aircraft(Aircraft aircraft);

    /**
     * Adds new flight with given parameters
     * Assigns the most optimum available aircraft
     * to flight automatically.
     * @param target Target destination of the flight
     * @param capacity Maximum passenger capacity of the flight
     * @param price Price of a ticket for this flight
     */
    public void addFlight(Destination target,int capacity,int price);

    /**
     * Removes the given flight from
     * system storage, if it can be found
     * @param flight flight to remove
     * @return true if it can be found in the container
     */
    public boolean removeFlight(Flight flight);

}
