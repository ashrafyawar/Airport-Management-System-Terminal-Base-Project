package Airport;

import Client.UserInterface;
import Client.User;
import java.util.Map;
import java.util.Scanner;

/**AirportPersonnel class, The class that administrates users.*/
public class AirportPersonnel extends User implements UserInterface , AirportPersonnelInterface {

    /**Helps to reach AirportSystemStorage class' data.*/
    private AirportSystemStorage airportSystemStorage;

    /** AirportPersonnel constructor.
     * @param name As the name of user.
     * @param surname As the surname of user.
     * @param password As the password of the user, that helps logging in.
     * @param SSN As the SSN of the user.
     * @param airportSystemStorage As current airport to be in.
     * */
    public AirportPersonnel( String name , String surname , final String SSN , final String password
            , final AirportSystemStorage airportSystemStorage  ) throws Exception {
        super(name, surname ,SSN , password);

        if( airportSystemStorage == null ) {
            throw new Exception("airlineSystemStorage cannot be null.");
        } else {
            this.airportSystemStorage = airportSystemStorage;
        }

    }

    /**
     * AirportPersonnel Menu
     */
    public void menu(){

        int choice;
        Scanner in = new Scanner(System.in);
        boolean loop = true;

        while(loop){

            System.out.printf("1- Personal info menu \n");
            System.out.printf("2- Change ssn\n");
            System.out.printf("3- Change password.\n");
            System.out.printf("4- Dismiss a customer.\n");
            System.out.printf("5- Exit.\n");

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
                    for(Map.Entry<String,Customer> ele :airportSystemStorage.getCustomers().entrySet())
                        System.out.println(ele.getValue());
                    System.out.print("Enter the SSN of the customer: ");
                    String removeSSN = in.next();
                    User temp = airportSystemStorage.getUserWithSSN(removeSSN);
                    if(temp!=null){
                        if(temp instanceof Customer)
                            airportSystemStorage.getCustomers().remove(removeSSN);
                        else{
                            System.out.println("This SSN does not belong a customer!");
                        }
                    }
                    else
                        System.out.println("This SSN is not proper!");

                    break;
                case 5: loop = false;
                    break;
                default:

            }
        }
    }

    /**
     * Dismiissing customer.
     * @param SSN SSN of customer
     * @return true if customer have been removed.
     * @throws Exception
     */
    @Override
    public boolean dismissCustomer( final String SSN ) throws Exception {

        if( SSN == null ) {
            throw new Exception("SSN cannot be null.");
        }

        if( SSN.equals("") ) {
            throw new Exception("SSN cannot be empty.");
        }

        if( airportSystemStorage.customers.containsKey( SSN )) {
            airportSystemStorage.customers.remove( SSN );
            return true;
        } else {
            return false;
        }
    }

}
