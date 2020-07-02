package Airline;
import Airport.Customer;


/**
 * Ticket Information.
 *
 * Ticket data kaldırılacak
 */
public class Ticket {
    private int PNR;
    private TicketData data;
    private AirlineSystemStorage airlineSystemStorage;

    private class TicketData{
        private Flight flight;
        private Customer customer;

        public TicketData( Flight flight , Customer customer) throws Exception {

            if( flight == null || customer == null || airlineSystemStorage == null ) {
                throw new Exception("Flight or customer or systemStorage cannot be null.");
            }

            this.flight = flight;
            this.flight.incUsedCapacity();  //o uçuşa biletlenen insan increment edildi
            this.customer = customer;

        }

        public Customer getCustomer() {
            return customer;
        }
        public Flight getFlight() {
            return flight;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }
        public void setFlight(Flight flight) {
            this.flight = flight;
        }

        @Override
        public int hashCode() {
            return (customer.toString()+flight.toString()).hashCode();
        }

        @Override
        public String toString() {
            return "Customer Informations:\n" + customer + "Flight Details:\n" + flight;
        }
    }
    public Ticket(Flight f_val, Customer c_val, AirlineSystemStorage s_val){// oluşan her ticket o airline in ticket list'ine ekleniyor
        try{
            data = new TicketData(f_val,c_val);
            PNR = data.hashCode();
            airlineSystemStorage = s_val;
            airlineSystemStorage.getMapOfTicket().put(PNR,this); // adds new created value to map
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteTicket(){
            airlineSystemStorage.getMapOfTicket().remove(this);
            getData().flight.decUsedCapacity();
    }

    public int getPNR() {
        return PNR;
    }
    public TicketData getData() {
        return data;
    }
    public AirlineSystemStorage getAirlineSystemStorage() {
        return airlineSystemStorage;
    }


    @Override
    public String toString() {
        return "PNR: " + PNR + "\n" + data.toString();
    }

    @Override
    public int hashCode() {
        return PNR;
    }
}
