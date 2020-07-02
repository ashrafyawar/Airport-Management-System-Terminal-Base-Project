package Airport;

import Client.Person;
import Client.User;
import Client.UserInterface;
import java.util.Iterator;
import java.util.Scanner;

/**ShopManager class, Manager of shops uses this class' methods.*/
public class ShopManager extends User
        implements UserInterface , ShopManagerInterface {

    /**Fee of the manager.*/
    private Double managedFee;

    /**Helps to reach AirportSystemStorage class' data.*/
    private AirportSystemStorage airportSystemStorage;

    /**Method to assign a shop's manager to this manager.
     * @param shop to change shop's manager.
     * @return boolean value, false if shop is not found.*/
    private boolean authenticateManager(Shop shop ) throws Exception {

        if( shop == null ) {
            throw new Exception("Shop cannot be null.");
        }

        return shop.getShopManager() == this;
    }

    /** ShopManager constructor.
     * @param name As the name of user.
     * @param surname As the surname of user.
     * @param password As the password of the user, that helps logging in.
     * @param SSN as new SSN.
     * @param managedFee as the managed fee of user.
     * @throws Exception if managed fee is negative.
     * */
    public ShopManager( String name, String surname , final String SSN , final String password , Double managedFee
            , final AirportSystemStorage airportSystemStorage ) throws Exception {
        super(name, surname , SSN , password );

        if ( airportSystemStorage == null ) {
            throw new Exception("airportSystemStorage cannot be null.");
        } else {
            this.airportSystemStorage = airportSystemStorage;
        }


        if( managedFee < 0 ) {
            throw new Exception("ManagedFee cannot be negative.");
        } else {
            this.managedFee = managedFee;
        }
    }

    public void menu() throws Exception {

        int choice;
        Scanner in = new Scanner(System.in);
        boolean loop = true;


        while(loop){

            System.out.printf("\nWelcome to airline personnel page, %s.\n",getName());

            System.out.printf("1- Personal info menu \n");
            System.out.printf("2- Change ssn\n");
            System.out.printf("3- Change password.\n");
            System.out.printf("4- Get managed total fee.\n");
            System.out.printf("5- See all shops.\n");
            System.out.printf("6- Open a shop.\n");
            System.out.printf("7- Close a shop.\n");
            System.out.printf("8- Exit.\n");

            choice = in.nextInt();

            switch (choice){
                case 1: System.out.print(super.toString());
                    break;
                case 2: airportSystemStorage.changeSSNwithMenu(in);
                    break;
                case 3: airportSystemStorage.changePasswordwithMenu(in);
                    break;
                case 4: System.out.printf("Managed total fee= %f",getManagedFee());
                    break;
                case 5:
                    int i = 0;
                    Iterator<Place> it = airportSystemStorage.getPlaces().iterator();
                    while (it.hasNext()){
                        System.out.printf("%d : %s.",i,it.toString());
                        i++;
                    }
                    break;
                case 6:
                        do{
                          System.out.printf("Enter a shop index to open it.");
                          choice = in.nextInt();
                        }while(choice > airportSystemStorage.getPlaces().size() || choice < 0);
                        openShop((Shop)airportSystemStorage.getPlaces().get(choice));

                    break;
                case 7:
                        do{
                            System.out.printf("Enter a shop index to close it.");
                            choice = in.nextInt();
                        }while(choice > airportSystemStorage.getPlaces().size() || choice < 0);
                        closeShop((Shop)airportSystemStorage.getPlaces().get(choice));

                    break;
                case 8: loop = false;
                    break;
                default: System.out.printf("Error. Your input is invalid..\n");
            }
        }
    }

    /**get method for managedFee.
     * @return double value of managedFee.*/
    public Double getManagedFee() {
        return managedFee;
    }

    /**set method for managedFee
     * @param managedFee as new managed fee.*/
    public void setManagedFee(double managedFee) {
        this.managedFee = managedFee;
    }

    /**
     * Open the shop.
     * @param shop to open it.
     * @throws Exception
     */
    @Override
    public void openShop( Shop shop ) throws Exception {

        if( authenticateManager( shop ) ) {
            throw new Exception("Authentication failed for shop manager.");
        }

        if( shop.isInService()  ) {
            System.out.println( shop.getName() + " is already in-service.");
        } else {
            shop.setInService( true );
            System.out.println( shop.getName() + " has been opened up.");
        }
    }

    /**
     * Close the shop.
     * @param shop to close it.
     * @throws Exception
     */
    @Override
    public void closeShop( Shop shop ) throws Exception {

        if( authenticateManager( shop ) ) {
            throw new Exception("Authentication failed for shop manager.");
        }

        if( shop.isInService()  ) {
            System.out.println( shop.getName() + " has been closed up.");
        } else {
            shop.setInService( true );
            System.out.println( shop.getName() + " is already out of service.");
        }

    }

    /**
     * Set the name of store.
     * @param shop to edit.
     * @param newName for its new name.
     * @throws Exception
     */
    @Override
    public void setName( Shop shop  , final String newName ) throws Exception {

        if( authenticateManager( shop ) ) {
            throw new Exception("Authentication failed for shop manager.");
        }

        if( newName.equals( shop.getName() )) {
            System.out.println( shop.getName() + " is already equivalent with given name.");
        } else {
            System.out.println( "Shop name changed from " + shop.getName() + " to " + newName );
            shop.setName( newName );
        }
    }

    /**
     * To compare shopManager
     * @param person Object to compare.
     * @return
     */
    @Override
    public int compareTo(Person person) {

        ShopManager shopManager;

        try {
            shopManager = (ShopManager) person;
        }
        catch ( Exception e ) {
            return 1;
        }

        return this.getManagedFee().compareTo( shopManager.getManagedFee() );
    }

}
