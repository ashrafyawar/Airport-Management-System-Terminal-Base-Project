package Airline;

import Airport.Airport;
import Airport.AirportSystemStorage;
import Airport.Customer;
import DataStructures.MapGraph;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Airline management class.
 * @author Nevzat Seferoglu and Bilal Bayrakdar
 * @version 1.5
 */
public class Airline {

    /**
     * Current airport.
     */
    Destination localAirport;

    /**
     * General data storage of airline
     */
    private final AirlineSystemStorage airlineSystemStorage;

    private final AirportSystemStorage airportSystemStorage;

    /**
     * Local commition rate.
     */
    private double commissionRate;

    /**
     * Get pilots.
     * @return pilots container.
     */
    public Queue<AirlinePersonnel> getPilots() {
        return airlineSystemStorage.getPilots();
    }

    /**
     * Get CabinCrew.
     * @return CabinCrew container.
     */
    public Queue<AirlinePersonnel> getCabinCrew() {
        return airlineSystemStorage.getCabin_crew();
    }

    /**
     * Get admin.
     * @return admin container.
     */
    public AirlineAdmin getAdmin() {
        return airlineSystemStorage.getAdmin();
    }


    public Airline(Destination localAirport, double commissionRate, AirlineAdmin admin, String nameOfTrademarkAsIdentifier, MapGraph ways, ArrayList<Destination> dests, AirportSystemStorage airportSystemStorage) {

        this.localAirport = localAirport;
        this.airportSystemStorage = airportSystemStorage;
        airlineSystemStorage = new AirlineSystemStorage(admin,nameOfTrademarkAsIdentifier,ways,dests);
        this.commissionRate = commissionRate;

    }

    /**
     * Create ticket
     * @param customer customer.
     * @param flight flight.
     * @return new ticket included pnr.
     * @throws Exception
     */
    public Ticket createTicket(Customer customer,Flight flight) throws Exception {
        if(flight.isTicketAble())
            return new Ticket(flight,customer,airlineSystemStorage);
        else
            throw(new Exception("Choosen filght's tickets are currently unavailable! "));
    }

    /**
     * Delete existing ticket.
     * @param ticket ticket.
     */
    public void deleteticket(Ticket ticket){
        ticket.deleteTicket();
    }

    /**
     * General airline system storage.
     * @return
     */
    public AirlineSystemStorage getAirlineSystemStorage() {
        return airlineSystemStorage;
    }

    /**
     * Set commission rate.
     * @param commissionRate commission rate.
     * @throws Exception
     */
    public void setCommissionRate( double commissionRate ) throws Exception {
        if( commissionRate < 0 ) {
            throw new Exception("CommissionRate cannot be negative.");
        }

        this.commissionRate = commissionRate;
    }


    /**
     * Return AirlineAdmin with SSN.
     * @param SSN SSN.
     * @return airline admin exists with matching SSN otherwise return null.
     * @throws Exception
     */
    public AirlineAdmin getAdminWithSSN(String SSN){
        if(getAdmin().getSSN().equals(SSN))
            return getAdmin();
        else
            return null;
    }

    /**
     * Return airline personnel with SSN.
     * @param SSN SSN.
     * @return airline personnel exists with matching SSN otherwise return null.
     * @throws Exception
     */
    public AirlinePersonnel getPersonWithSSN(String SSN){
        for(AirlinePersonnel ele:airlineSystemStorage.getPilots()){
            if(ele.getSSN().equals(SSN))
                return ele;
        }
        for(AirlinePersonnel ele: airlineSystemStorage.getCabin_crew()){
            if(ele.getSSN().equals(SSN))
                return ele;
        }
        return null;
    }

    /**
     * Return commition rate.
     * @return commition rate.
     */
    public double getCommissionRate() {
        return commissionRate;
    }

    /**
     * Return local airport.
     * @return local airport.
     */
    public Destination getLocalAirport() {
        return localAirport;
    }

    public AirportSystemStorage getAirportSystemStorage() {
        return airportSystemStorage;
    }


    public String getNameofTradeMark(){
        return airlineSystemStorage.getNameOfTrademarkAsIdentifier();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Airline)
            return getNameofTradeMark().equals(((Airline) obj).getNameofTradeMark());
        else
            return false;
    }

    /**
     * String representation of Airline.
     * @return
     */
    @Override
    public String toString() {
        return "Company Name : " + airlineSystemStorage.getNameOfTrademarkAsIdentifier()+"\t"
                +"Commission Rate : " + this.commissionRate;
    }
}
