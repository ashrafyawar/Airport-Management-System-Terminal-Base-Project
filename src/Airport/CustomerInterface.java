package Airport;

import Airline.Flight;
import Airline.FlightCompareTo;
import Airline.Ticket;

/**Customer interface used for Customers*/
public interface CustomerInterface {

   /**Method to buy ticket from a flight.
    * @param flight to buy ticket from.
    * @return a Ticket, represents a ticket from that flight.*/
   Ticket buyTickets( Flight flight );
}
