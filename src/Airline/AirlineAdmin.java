package Airline;

import Client.UserInterface;
import Client.User;
import java.util.*;

public class AirlineAdmin extends User implements AirlineAdminInterface, UserInterface {

    Airline airline;

    public AirlineAdmin( User user , Airline airline ) throws Exception {

        super(user.getName(),user.getSurname(),user.getSSN(),user.getPassword());
        if( airline == null ) {
            throw new Exception("Airline cannot be null.");
        }
        this.airline = airline;
    }

    public void menu(){

        int choice;
        Scanner in = new Scanner(System.in);
        boolean loop = true;

        while(loop){

            System.out.printf("1- Recruit Personnel.\n");
            System.out.printf("2- Dismiss Personnel\n");
            System.out.printf("3- Add Destination.\n");
            System.out.printf("4- Remove Destination.\n");
            System.out.printf("5- Add Aircraft.\n");
            System.out.printf("6- Remove Aircraft\n");
            System.out.printf("7- Add Flight.\n");
            System.out.printf("8- Remove Flight.\n");
            System.out.printf("9 -Exit from administration page.\n");

            choice = in.nextInt();

            switch (choice){
                case 1:
                    System.out.print("Please enter personnel name: ");
                    String perName = in.next();
                    System.out.print("Please enter personnel surname: ");
                    String perSurname = in.next();
                    System.out.print("Please enter an SSN: ");
                    String perSSN = in.next();
                    System.out.println("Please enter a password: ");
                    String perPass = in.next();
                    if(perName != null && perSurname!=null && !perName.equals("") && !perSurname.equals("")&& getAirline().getAirportSystemStorage().getUserWithSSN(perSSN)==null)
                        try{
                            System.out.println("Is the new employee pilot? (Y/N)");
                            String ans = in.next();
                            AirlinePersonnel airlinePersonnel;
                            if(ans.equals("Y") || ans.equals("y"))
                                airlinePersonnel = new AirlinePersonnel(perName,perSurname,true,perSSN,perPass);
                            else
                                airlinePersonnel = new AirlinePersonnel(perName,perSurname,false,perSSN,perPass);
                                recruitPersonnel(airlinePersonnel);
                        }catch (Exception e){
                            System.out.println(e);
                        }
                    break;
                case 2:
                    System.out.println("Is the employee who will be removed pilot ?(Y/N)");
                    String ans = in.next();
                    if(ans.equals("Y") || ans.equals("y")){
                        for(AirlinePersonnel ele:getAirline().getAirlineSystemStorage().getPilots())
                            System.out.println(ele);
                        System.out.print("Please enter the SSN of the employee who is gonna be removed: ");
                        String remSSN = in.next();
                        try{
                            AirlinePersonnel airTemp = new AirlinePersonnel("","",false,remSSN,"");
                            getAirline().getAirlineSystemStorage().getPilots().removeIf( k-> (k.equals(airTemp)));
                        }catch (Exception e){
                            System.out.println(e);
                        }
                    }
                    else{
                        for(AirlinePersonnel ele:getAirline().getAirlineSystemStorage().getCabin_crew())
                            System.out.println(ele);
                        System.out.print("Please enter the SSN of the employee who is gonna be removed: ");
                        String remSSN = in.next();
                        try{
                            AirlinePersonnel airTemp = new AirlinePersonnel("","",true,remSSN,"");
                            getAirline().getAirlineSystemStorage().getCabin_crew().removeIf( k-> (k.equals(airTemp)));
                        }catch (Exception e){
                            System.out.println(e);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter the name of the destination: ");
                    String destName = in.next();
                    System.out.print("Enter the latitude of destination: ");
                    Double destLat = in.nextDouble();
                    System.out.print("Enter the longtitude of destination: ");
                    Double destLong = in.nextDouble();
                    Destination dst = new Destination(destName,destLat,destLong);
                    if(!getAirline().getAirlineSystemStorage().getListOfDestination().contains(dst))
                        getAirline().getAirlineSystemStorage().getListOfDestination().add(dst);
                    break;
                case 4:
                    System.out.println("Destinations are showed in below, please select an index:\n");
                    for(int i=0; i< getAirline().getAirlineSystemStorage().getListOfDestination().size(); i++)
                        System.out.println(i+"- "+getAirline().getAirlineSystemStorage().getListOfDestination().get(i));
                        getAirline().getAirlineSystemStorage().getListOfDestination().removeIf(k->(k.equals(getAirline().getAirlineSystemStorage().getListOfDestination().get(in.nextInt()))));
                    break;
                case 5:
                    System.out.print("Enter wingspan of aircraft: ");
                    Double craftWingSpan = in.nextDouble();
                    System.out.print("Enter passenger of aircraft: ");
                    Double craftPassengerCap = in.nextDouble();
                    System.out.print("Enter empty weight of aircraft: ");
                    Double  craftEmpty= in.nextDouble();
                    System.out.print("Enter max fuel capacity of aircraft: ");
                    Double  crafFuelCap= in.nextDouble();
                    getAirline().getAirlineSystemStorage().getListOfAirCraft().add(new Aircraft(craftWingSpan,craftPassengerCap,craftEmpty,crafFuelCap));
                    break;
                case 6:
                    System.out.println("Aircrafts are showed in below, please select an index:\n");
                    for(int i=0; i< getAirline().getAirlineSystemStorage().getListOfAirCraft().size(); i++)
                        System.out.println(i+"- "+getAirline().getAirlineSystemStorage().getListOfAirCraft().get(i));
                    if(getAirline().getAirlineSystemStorage().getListOfAirCraft().removeIf(k->(k.equals(getAirline().getAirlineSystemStorage().getListOfAirCraft().get(in.nextInt())))))
                        System.out.println("Removal completed successfully.");
                    else
                        System.out.println("Entered index is wrong!");
                    break;
                case 7:
                    System.out.println("Destinations are showed in below, please select an index:\n");
                    Destination target=null;
                    while (target==null){
                        for(int i=0; i<getAirline().getAirlineSystemStorage().getListOfDestination().size(); i++)
                            System.out.print(i+"- "+getAirline().getAirlineSystemStorage().getListOfDestination().get(i));
                        int ind = in.nextInt();
                        if(ind>0 && ind<getAirline().getAirlineSystemStorage().getListOfDestination().size())
                            target=getAirline().getAirlineSystemStorage().getListOfDestination().get(ind);
                    }
                    System.out.print("Enter the capacity of the flight: ");
                    int capacity = in.nextInt();
                    System.out.print("Enter the price of the flight: ");
                    int price = in.nextInt();
                    addFlight(target,capacity,price);
                    break;
                case 8:
                    System.out.println("Flights are showed in below, please select an index:\n");
                    for(int i=0; i< getAirline().getAirlineSystemStorage().getListOfFlight().size(); i++)
                        System.out.println(i+"- "+getAirline().getAirlineSystemStorage().getListOfFlight().get(i));
                    if(getAirline().getAirlineSystemStorage().getListOfFlight().removeIf(k->(k.equals(getAirline().getAirlineSystemStorage().getListOfFlight().get(in.nextInt())))))
                        System.out.print("Removal completed successfully!");
                    else
                        System.out.print("Entered index is wrong!");
                    break;
                case 9: loop = false;
                    break;
                default: System.out.println("Error. Your input is invalid.");

            }
        }
        System.out.println("Exiting...");
    }

    public Airline getAirline() {
        return airline;
    }

    public boolean dismissPersonnel(AirlinePersonnel airlinePersonnel ) throws Exception {

        if( airlinePersonnel == null ) {
            throw new Exception("AirlinePersonnel cannot be null.");
        }

        if( !(airline.getAirlineSystemStorage().getCabin_crew().contains( airlinePersonnel ) ||
                airline.getAirlineSystemStorage().getPilots().contains( airlinePersonnel )) ) {
            return false;
        }

        if( airlinePersonnel.isPilot ) {

            try {
                airline.getAirlineSystemStorage().getPilots().remove(airlinePersonnel);
            }
            catch ( Throwable t ) {
                return false;
            }
        } else {
            try {
                airline.getAirlineSystemStorage().getCabin_crew().remove(airlinePersonnel);
            }
            catch ( Throwable t ) {
                return false;
            }
        }

        return true;

    }

    @Override
    public boolean recruitPersonnel( AirlinePersonnel airlinePersonnel ) throws Exception {

        if( airlinePersonnel == null ) {
            throw new Exception("AirlinePersonnel cannot be null.");
        }

        if( airline.getAirlineSystemStorage().getCabin_crew().contains( airlinePersonnel ) ||
                airline.getAirlineSystemStorage().getPilots().contains( airlinePersonnel ) ) {
            return false;
        }

        if( airlinePersonnel.isPilot ) {

            try {
                airline.getAirlineSystemStorage().getPilots().add( airlinePersonnel );
            }
            catch ( Throwable t ) {
                return false;
            }
        } else {
            try {
                airline.getAirlineSystemStorage().getCabin_crew().add( airlinePersonnel );
            }
            catch ( Throwable t ) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addDestination(Destination destination) {
        return airline.getAirlineSystemStorage().getListOfDestination().add(destination);
    }

    @Override
    public boolean removeDestination(Destination destination) {
        return airline.getAirlineSystemStorage().getListOfDestination().removeIf(ele ->ele.equals(destination));
    }

    /**
     * Get a new aircraft.
     * @return Not clear and specified.
     */
    @Override
    public boolean add_aircraft( Double wingspan,Double passengerCapacity,Double emptyWeightAsKg , Double maxFuelCapacity ) {

        Aircraft temp = new Aircraft(wingspan, passengerCapacity, emptyWeightAsKg, maxFuelCapacity);

        if(passengerCapacity<0 || emptyWeightAsKg<0 || maxFuelCapacity>0)
            return false;

        for(Aircraft ele : airline.getAirlineSystemStorage().getListOfAirCraft() ){
            if(ele.equals(temp))
                return false;
        }

        return airline.getAirlineSystemStorage().getListOfAirCraft().add( temp );
    }


    @Override
    public boolean remove_aircraft(Aircraft aircraft){
        return airline.getAirlineSystemStorage().getListOfAirCraft().removeIf(ele->ele.equals(aircraft));
    }

    /**
     * @inheritDoc
     */
    public void addFlight(Destination target,int capacity,int price) {

        if( airline.getAirlineSystemStorage().ways.isEdge(0,airline.getAirlineSystemStorage().dests.indexOf(target)));

        String company = airline.getAirlineSystemStorage().getNameOfTrademarkAsIdentifier();
        String nameTrademark = airline.getAirlineSystemStorage().getNameOfTrademarkAsIdentifier();
        DateTime dateTime = new DateTime();
        Aircraft aircraft;
        // Collection<AirlinePersonnel> pilots, cabin_crew;

        Flight flight = new Flight(company,nameTrademark,airline.getAirlineSystemStorage().dests.get(0),target,dateTime,capacity,price,assignPilot(),assingCabincrew());
        try {
            flight.setAircraft(assignAircraft(flight));

        }catch (Exception e){
            System.out.println(e);
            removeFlight(flight);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean removeFlight(Flight flight) {
        return airline.getAirlineSystemStorage().getListOfFlight().removeIf(ele -> ele.equals(flight));
    }

    /**
     * Sets the argument flight's to aircrafts static flight score,
     * then re-calculate aircraft's score then sorts them according to
     * this scores. In the end assigns the top value in the list to the
     * given flight as aircraft
     * @param flight Flight which we look up for most optimum aircraft
     * @return the found aircraft if there are aircraft
     * @throws Exception Throws an exception when there is no aircraft in the hangar
     */
    private Aircraft assignAircraft(Flight flight) throws Exception {
        if(airline.getAirlineSystemStorage().getListOfAirCraft().size()!=0){
            Aircraft.setFlightScore(flight.getScore());
            for(Aircraft ele:airline.getAirlineSystemStorage().getListOfAirCraft())
                ele.assignCompare();
            airline.getAirlineSystemStorage().getListOfAirCraft().sort(Aircraft.getComparator());
            return airline.getAirlineSystemStorage().getListOfAirCraft().get(0);
        }
        else
            throw(new Exception("There is no aircraft in stock"));

    }

    private List<AirlinePersonnel> assignPilot(){
      List<AirlinePersonnel> pilots = new LinkedList<AirlinePersonnel>();

      AirlinePersonnel temp = airline.getAirlineSystemStorage().getPilots().poll();
      pilots.add(temp);
      airline.getAirlineSystemStorage().getPilots().offer(temp);

      temp = airline.getAirlineSystemStorage().getPilots().poll();
      pilots.add(temp);
      airline.getAirlineSystemStorage().getPilots().offer(temp);

      return pilots;
    }

    private List<AirlinePersonnel> assingCabincrew(){
        List<AirlinePersonnel> crew = new LinkedList<AirlinePersonnel>();

        AirlinePersonnel temp = airline.getAirlineSystemStorage().getCabin_crew().poll();
        crew.add(temp);
        airline.getAirlineSystemStorage().getCabin_crew().offer(temp);

        temp = airline.getAirlineSystemStorage().getCabin_crew().poll();
        crew.add(temp);
        airline.getAirlineSystemStorage().getCabin_crew().offer(temp);

        return crew;
    }

}
