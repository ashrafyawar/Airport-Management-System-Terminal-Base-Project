package Airport;
import Airline.*;
import Client.Person;
import Client.User;
import Client.UserInterface;
import DataStructures.Edge;
import DataStructures.MapGraph;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**AirportSystemStorage class. Has various information and methods for airport.*/
public class AirportSystemStorage {

    /**AirportSystemStorage constructor.*/
    public AirportSystemStorage( AirportAdmin airportAdmin ) throws Exception {

        airlines            = new TreeMap< String , Airline>();
        airportPersonnel    = new ArrayList<AirportPersonnel>();
        customers           = new TreeMap< String , Customer>();
        places              = new ArrayList<Place>();
        shopManagers        = new PriorityQueue<ShopManager>();

        airportFund = 0;

       destinations = new ArrayList<Destination>();
       destinations.add(new Destination("Istanbul",0.0,0.0));
       destinations.add(new Destination("Ankara",50.0,25.0));
       destinations.add(new Destination("Izmir",80.0,10.0));

       ways = new MapGraph(15,false);

       if( airportAdmin == null ) {
           throw new Exception("AirportAdmin cannot be null.");
       }

       this.airportAdmin = airportAdmin;
    }

    /**
     * Airport Admin
     */
    AirportAdmin airportAdmin;

    /**airportFund, represents airport's total money.*/
    double airportFund;

    TreeMap< String , Airline >     airlines;
    ArrayList<AirportPersonnel>     airportPersonnel;
    TreeMap< String , Customer >    customers;
    ArrayList<Place>                places;         /* included shops */
    PriorityQueue<ShopManager>      shopManagers;
    ArrayList<Destination> destinations;
    MapGraph ways;

    /**
     * Check the existence of given way.
     * @param destination destianation.
     * @return
     */
    public boolean isWay(Destination destination) throws Exception {

        if( destination == null ) {
            throw new Exception("destination cannot be null.");
        }

        return ways.isEdge(0,destinations.indexOf(destination));
    }

    public boolean isValidSSN( String SSN ){

        AtomicBoolean a= new AtomicBoolean(true); // multi thread operationlar da falanda kullanılan bir veri yapısı
        getCustomers().forEach((k,v)->{
            if(SSN.equals(k))
                a.set(false);
        });

        return a.getPlain();

    }

    public double getAirportFund() {
        return airportFund;
    }

    public void setAirportFund(double airportFund) {
        this.airportFund = airportFund;
    }

    public TreeMap< String , Airline > getAirlines() {
        return airlines;
    }

    public void setAirlines(TreeMap< String , Airline > airlines) {
        this.airlines = airlines;
    }

    public ArrayList<AirportPersonnel> getAirportPersonnel() {
        return airportPersonnel;
    }

    public void setAirportPersonnel(ArrayList<AirportPersonnel> airportPersonnel) {
        this.airportPersonnel = airportPersonnel;
    }

    public TreeMap<String,Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(TreeMap<String,Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public PriorityQueue<ShopManager> getShopManagers() {
        return shopManagers;
    }

    public void setShopManagers(PriorityQueue<ShopManager> shopManagers) {
        this.shopManagers = shopManagers;
    }

    public AirportAdmin getAirportAdmin() {
        return airportAdmin;
    }

    private AirlinePersonnel getAirlinePersonWithSSN(String SSN){
        AirlinePersonnel temp = null;
        for(Map.Entry<String,Airline> ele:getAirlines().entrySet())
            temp = ele.getValue().getPersonWithSSN(SSN);

        return temp;
    }
    private AirlineAdmin getAirlineAdminWithSSN(String SSN){
        AirlineAdmin temp = null;
        for(Map.Entry<String,Airline> ele:getAirlines().entrySet())
            temp = ele.getValue().getAdminWithSSN(SSN);

        return temp;
    }
    private AirportAdmin getAirportAdminWithSSN(String SSN){
        if(getAirportAdmin().getSSN().equals(SSN))
            return airportAdmin;
        else
            return null;
    }
    private AirportPersonnel getAirportPersonnelWithSSN(String SSN){
        for(AirportPersonnel ele: getAirportPersonnel()){
            if (ele.getSSN().equals(SSN))
                return ele;
        }
        return null;

    }
    private Customer getCustomerWithSSN(String SSN){
        for(Map.Entry<String,Customer> ele: getCustomers().entrySet()){
            if(ele.getValue().getSSN().equals(SSN))
                return ele.getValue();
        }
        return null;
    }
    public User getUserWithSSN(String SSN){
        User temp = null;
        temp = getCustomerWithSSN(SSN);
        if(temp !=null)
            return temp;
        else{
            temp = getAirlinePersonWithSSN(SSN);
            if(temp!=null)
                return temp;
            else{
                temp = getAirlineAdminWithSSN(SSN);
                if (temp!=null)
                    return temp;
                else{
                    temp = getAirportPersonnelWithSSN(SSN);
                    if(temp!=null)
                        return temp;
                    else{
                        temp = getAirportAdminWithSSN(SSN);
                        return temp;
                    }
                }
            }
        }

    }

    /** Overall menu to change SSN for User type UI
     * @param in Scanner (must be System.in)
     * @return new SSN.
    */
    public String changeSSNwithMenu( Scanner in ) {

        boolean matched = false;
        String newSSN = null;
        while (!matched){

            System.out.print("Enter the new SSN : ");
            newSSN = in.nextLine();
            while ( newSSN == null || newSSN.equals("") ) {
                System.out.print("Enter the new SSN : ");
                newSSN = in.nextLine();
            }

            // SSN check will be here.
            if( getUserWithSSN(newSSN)!=null) {
                matched = true;
            }
        }
        return newSSN;
    }

    /** Overall menu to change password for User type UI
     * @param in Scanner (must be System.in)
     * @return new Password.
     */
    public String changePasswordwithMenu( Scanner in ) {

        System.out.print("Enter the new password : ");
        String password = in.nextLine();
        while ( password == null || password.equals("") ) {
            System.out.print("Enter the new password : ");
            password = in.nextLine();
        }
        return password;

    }


}
