package Airport;

import Airline.*;
import Client.User;
import Client.UserInterface;
import DataStructures.Edge;
import DataStructures.MapGraph;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/** AirportAdmin class for Admins of airport. Extended from Person Class.
 * This class administrates Airport business as flights, shops.. */
public class AirportAdmin extends User implements UserInterface , AirportAdminInterface {

    /**Helps to reach AirportSystemStorage class' data.*/
    private AirportSystemStorage airportSystemStorage;

    /** AirportAdmin constructor.
     * @param name As the name of user.
     * @param surname As the surname of user.
     * @param password As the password of the user, that helps logging in.
     * @param airportSystemStorage As current airport to be in.
     * @param SSN As the SSN of the user.
     * */
    public AirportAdmin(String name , String surname , final String SSN , final String password
            , final AirportSystemStorage airportSystemStorage  ) throws Exception {

        super(name, surname , SSN , password );
        this.airportSystemStorage = airportSystemStorage;

    }

    public void menu() throws Exception {

        int choice;
        Scanner in = new Scanner(System.in);
        boolean loop = true, loop2;


        while(loop){
            System.out.printf("1- Personal info menu \n");
            System.out.printf("2- Set SSN\n");
            System.out.printf("3- Set password.\n");
            System.out.printf("4- Airline administration.\n");
            System.out.printf("5- Airport Personnel administration.\n");
            System.out.printf("6- Shop administration.\n");
            System.out.printf("7- Exit.\n");

            choice = in.nextInt();

            switch (choice){
                case 1:
                    System.out.println(this);
                    break;
                case 2:
                    airportSystemStorage.changeSSNwithMenu(in);
                    break;
                case 3:
                    airportSystemStorage.changePasswordwithMenu(in);
                    break;
                case 4:
                    loop2 = true;
                    while (loop2){
                        System.out.printf("1- See all airlines.\n");
                        System.out.printf("2- Add an airline.\n");
                        System.out.printf("3- Remove an airline.\n");
                        System.out.printf("4- Set commission rate.\n");
                        System.out.printf("5- Exit.\n");

                        choice = in.nextInt();

                        switch (choice){
                            case 1:
                                System.out.println(airportSystemStorage.getAirlines().size());
                                for(Map.Entry<String,Airline> ele:airportSystemStorage.getAirlines().entrySet())
                                    System.out.println(ele.getValue());
                            break;
                            case 2:
                                System.out.print("Enter the name of admin : ");
                                String nameOfAdmin = in.next();

                                while (  nameOfAdmin == null || nameOfAdmin.equals("") ) {
                                    System.out.print("Enter the name of admin : ");
                                    nameOfAdmin = in.next();
                                }

                                System.out.print("Enter the surname of admin : ");
                                String surnameOfAdmin = in.next();

                                while (  surnameOfAdmin == null || surnameOfAdmin.equals("") ) {
                                    System.out.print("Enter the surname of admin : ");
                                    surnameOfAdmin = in.next();
                                }

                                System.out.print("Enter the SSN of admin : ");
                                String SSN = in.next();

                                while (  SSN == null || SSN.equals("") ) {
                                    System.out.print("Enter the SSN of admin : ");
                                    SSN = in.next();
                                }

                                User isExist = airportSystemStorage.getUserWithSSN( SSN );

                                if( isExist != null ) {
                                    System.out.println("There already exists an given SSN !");
                                    System.out.print("Enter the SSN of admin : ");
                                    SSN = in.next();
                                    while (  SSN == null || SSN.equals("") ) {
                                        System.out.print("Enter the SSN of admin : ");
                                        SSN = in.next();
                                    }
                                }

                                System.out.print("Enter the password of admin : ");
                                String password = in.next();

                                while (  password == null || password.equals("") ) {
                                    System.out.print("Enter the password of admin : ");
                                    password = in.next();
                                }

                                System.out.print("Enter the name of trademark of airline : ");
                                String nameOfAirline = in.next();

                                while (  nameOfAirline == null || nameOfAirline.equals("") ) {
                                    System.out.print("Enter the name of trademark of airline : ");
                                    nameOfAirline = in.next();
                                }

                                while ( airportSystemStorage.getAirlines().containsKey( nameOfAirline ) ) {
                                    System.out.println("There is already an airline with this trademark.");
                                    System.out.print("Enter the name of trademark of airline : ");
                                    nameOfAirline = in.next();
                                }

                                System.out.print("Enter the commission rateof airline : ");
                                double comissionRate = in.nextDouble();

                                Airline newAirline = new Airline( airportSystemStorage.destinations.get(0) , comissionRate , null
                                    , nameOfAirline , airportSystemStorage.ways , airportSystemStorage.destinations , airportSystemStorage );

                                newAirline.getAirlineSystemStorage().setAdmin( new AirlineAdmin(new User( nameOfAdmin , surnameOfAdmin, SSN , password ) , newAirline ));
                                airportSystemStorage.getAirlines().put(newAirline.getNameofTradeMark(),newAirline);
                                break;

                            case 3:
                                System.out.print("Enter the name of trademark of airline : ");
                                String removedAirlineName = in.next();

                                while (  removedAirlineName == null || removedAirlineName.equals("") ) {
                                    System.out.print("Enter the name of trademark of airline : ");
                                    removedAirlineName = in.next();
                                }

                                if( airportSystemStorage.getAirlines().containsKey( removedAirlineName ) ) {
                                    airportSystemStorage.getAirlines().remove(removedAirlineName);
                                } else {
                                    System.out.println("There is no airline with this name.");
                                }
                                break;

                            case 4:

                                System.out.print("Commission rate : ");
                                Double commissionRate = in.nextDouble();

                                System.out.print("Enter the name of trademark of airline : ");
                                String nameOfTradeMark = in.next();

                                while (  nameOfTradeMark == null || nameOfTradeMark.equals("") ) {
                                    System.out.print("Enter the name of trademark of airline : ");
                                    nameOfTradeMark = in.next();
                                }

                                if( airportSystemStorage.getAirlines().containsKey( nameOfTradeMark ) ) {
                                    airportSystemStorage.getAirlines().get(nameOfTradeMark).setCommissionRate(commissionRate);
                                } else {
                                    System.out.println("There is no airline with this name.");
                                }

                                break;

                            case 5: loop2 = false;
                                break;
                            default: System.out.printf("Error. Your input is invalid..\n");
                        }
                    }
                    break;
                case 5:
                    loop2 = true;
                    while (loop2){
                        System.out.printf("1- Add an airport personnel.\n");
                        System.out.printf("2- Remove an airport personnel.\n");
                        System.out.printf("3- See all airport personals.\n");
                        System.out.printf("4- Exit.\n");

                        choice = in.nextInt();

                        switch (choice){
                            case 1:
                                System.out.print("Enter the name of Airport Personnel : ");
                                String nameOfAirportPersonnel = in.next();

                                while (  nameOfAirportPersonnel == null || nameOfAirportPersonnel.equals("") ) {
                                    System.out.print("Enter the name of Airport Personnel : ");
                                    nameOfAirportPersonnel = in.next();
                                }

                                System.out.print("Enter the surname of Airport Personnel : ");
                                String surnameOfAirportPersonnel = in.next();

                                while (  surnameOfAirportPersonnel == null || surnameOfAirportPersonnel.equals("") ) {
                                    System.out.print("Enter the surname of Airport Personnel : ");
                                    surnameOfAirportPersonnel = in.next();
                                }

                                System.out.print("Enter the SSN of Airport Personnel : ");
                                String SSN = in.next();

                                while (  SSN == null || SSN.equals("") ) {
                                    System.out.print("Enter the SSN of Airport Personnel : ");
                                    SSN = in.next();
                                }

                                User isExist = airportSystemStorage.getUserWithSSN( SSN );

                                if( isExist != null ) {
                                    System.out.println("There already exists an given SSN !");
                                    System.out.print("Enter the SSN of Airport Personnel : ");
                                    SSN = in.next();
                                    while (  SSN == null || SSN.equals("") ) {
                                        System.out.print("Enter the SSN of Airport Personnel : ");
                                        SSN = in.next();
                                    }
                                }

                                System.out.print("Enter the password of Airport Personnel : ");
                                String password = in.next();

                                while (  password == null || password.equals("") ) {
                                    System.out.print("Enter the password of Airport Personnel : ");
                                    password = in.next();
                                }

                                airportSystemStorage.getAirportPersonnel().add( new AirportPersonnel( nameOfAirportPersonnel , surnameOfAirportPersonnel
                                        , SSN , password , airportSystemStorage ) );
                                break;

                            case 2:

                                System.out.print("Enter the SSN of Airport Personnel : ");
                                String dismissSSN = in.next();

                                while (  dismissSSN == null || dismissSSN.equals("") ) {
                                    System.out.print("Enter the SSN of Airport Personnel : ");
                                    dismissSSN = in.next();
                                }

                                if( airportSystemStorage.getAirportPersonnel().remove(airportSystemStorage.getUserWithSSN(dismissSSN)) ) {
                                    System.out.println("Airport personnel have been removed.");
                                } else {
                                    System.out.println("There is no Airport personnel with given name.");
                                }
                                break;

                            case 3:

                                for( AirportPersonnel airportPersonnel : airportSystemStorage.getAirportPersonnel() ) {
                                    System.out.println( airportPersonnel );
                                }
                                System.out.println();
                                break;

                            case 4: loop2 = false;
                                break;

                            default: System.out.printf("Error. Your input is invalid..\n");
                        }
                    }
                    break;
                case 6:
                    loop2 = true;
                    while (loop2){
                        System.out.printf("1- See all shops.\n");
                        System.out.printf("2- Add a shop.\n");
                        System.out.printf("3- Remove a shop.\n");
                        System.out.printf("4- Exit.\n");

                        choice = in.nextInt();

                        switch (choice){
                            case 1:
                                for( Place shop : airportSystemStorage.getPlaces() ) {
                                    if( shop instanceof Shop ) {
                                        System.out.println( shop );
                                    }
                                }
                                System.out.println();
                                break;

                            case 2:
                                System.out.print("Enter a name for the shop: ");
                                String shopName = in.next();
                                System.out.print("Please enter shop manager name: ");
                                String perName = in.next();
                                System.out.print("Please enter shop manager surname: ");
                                String perSurname = in.next();
                                System.out.print("Please enter an SSN: ");
                                String perSSN = in.next();
                                System.out.println("Please enter a password: ");
                                String perPass = in.next();
                                System.out.print("Please neter fee of the shop: ");
                                double shopFee = in.nextDouble();
                                ShopManager temp;
                                if(perName != null && perSurname!=null && !perName.equals("") && !perSurname.equals("")&& airportSystemStorage.getUserWithSSN(perSSN)==null){
                                    temp = new ShopManager(perName,perSurname,perSSN,perPass,shopFee,airportSystemStorage);
                                    airportSystemStorage.getPlaces().add(new Shop(shopName,temp,(int)shopFee,"14"));
                                }

                                break;

                            case 3:

                                System.out.print("Enter the shop name : ");
                                String shopNameVal = in.next();
                                while (  shopNameVal == null || shopNameVal.equals("") ) {
                                    System.out.print("Enter the shop name : ");
                                    shopNameVal = in.next();
                                }

                                for( Place place : airportSystemStorage.getPlaces() ) {
                                    if( place instanceof Shop || ((Shop)place).getName().equals(shopNameVal)) {
                                        airportSystemStorage.getPlaces().remove( place );
                                        break;
                                    }
                                }

                                break;
                            case 4: loop2 = false;
                                break;
                            default: System.out.printf("Error. Your input is invalid..\n");
                        }
                    }
                    break;
                case 7: loop = false;
                    break;
                default: System.out.printf("Error. Your input is invalid..\n");
            }
        }
    }

    public void addWay(Destination destination) throws Exception {
        Edge e = new Edge(0,airportSystemStorage.destinations.indexOf(destination));
        airportSystemStorage.ways.insertEdge(e);
    }

    @Override
    public boolean addAirportPersonnel( AirportPersonnel airportPersonnel) throws Exception {

        if( airportPersonnel == null ) {
            throw new Exception("AirportPersonnel cannot be null.");
        }

        if( airportSystemStorage.airportPersonnel.contains( airportPersonnel ) ) {
            return false;
        } else {
            airportSystemStorage.airportPersonnel.add( airportPersonnel );
            return true;
        }

    }

    @Override
    public boolean removeAirportPersonnel(AirportPersonnel airportPersonnel) throws Exception {

        if( airportPersonnel == null ) {
            throw new Exception("AirportPersonnel cannot be null.");
        }

        return airportSystemStorage.airportPersonnel.remove(airportPersonnel);
    }

    @Override
    public boolean addShop(Shop shop) throws Exception {

        if( shop == null ) {
            throw new Exception("Shop cannot be null.");
        }

        if( airportSystemStorage.places.contains( shop ) ) {
            return false;
        } else {
            airportSystemStorage.places.add( shop );
            return true;
        }

    }

    @Override
    public boolean removeShop(Shop shop) throws Exception {

        if( shop == null ) {
            throw new Exception("Shop cannot be null.");
        }

        return airportSystemStorage.places.remove(shop);
    }

    @Override
    public boolean addAirline(Airline airline) throws Exception {

        if( airline == null ) {
            throw new Exception("Airline cannot be null.");
        }

        if( airportSystemStorage.airlines.containsValue( airline ) ) {
            return false;
        } else {
            airportSystemStorage.airlines.put( airline.getAirlineSystemStorage().getNameOfTrademarkAsIdentifier() , airline );
            return true;
        }

    }

    @Override
    public boolean removeAirline(Airline airline) throws Exception {

        if( airline == null ) {
            throw new Exception("Airline cannot be null.");
        } else {
            return airportSystemStorage.airlines.remove( airline.getNameofTradeMark() , airline );
        }
    }

    @Override
    public double getAirportFund() {
        return airportSystemStorage.airportFund;
    }

    @Override
    public boolean setCommissionRate( Airline airline , final double commissionRate ) throws Exception {

        if( airline == null ) {
            throw new Exception("Airline cannot be null.");
        }

        if( commissionRate < 0 ) {
            throw new Exception("Commission-Rate cannot be negative number.");
        }

        if( !airportSystemStorage.airlines.containsValue( airline ) ) {
            return false;
        } else {
            airline.setCommissionRate( commissionRate );
            return true;
        }
    }

    @Override
    public boolean addDestination(Destination destination) throws Exception {

        if( destination == null ) {
            throw new Exception("Destination cannot be null.");
        }
        else {
            airportSystemStorage.destinations.add(destination);
            return true;
        }

    }

    @Override
    public boolean removeDestination(Destination destination) throws Exception {

        if( destination == null ) {
            throw new Exception("Destination cannot be null.");
        }
        else {
            airportSystemStorage.destinations.remove(destination);
            return true;
        }
    }

    public AirportSystemStorage getAirportSystemStorage() {
        return airportSystemStorage;
    }

    public void setAirportSystemStorage(AirportSystemStorage airportSystemStorage) {
        this.airportSystemStorage = airportSystemStorage;
    }
}
