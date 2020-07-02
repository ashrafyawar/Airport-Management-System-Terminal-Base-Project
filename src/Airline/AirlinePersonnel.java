package Airline;

import Client.Person;
import Client.UserInterface;
import Client.User;
import java.util.Objects;
import java.util.Scanner;

/**
 * Backbone of airline personnel .
 * In case of needed  other personnel type can be derived.
 * @author Nevzat Seferoglu
 * @version 1.0
 */
public class AirlinePersonnel extends User implements UserInterface {

    boolean isPilot;
    AirlineSystemStorage airlineSystemStorage;

    public AirlinePersonnel(String name, String surname , boolean isPilot , String SSN , String password ) throws Exception {

        super(name, surname, SSN, password);
        this.isPilot = isPilot;

        if( SSN == null ) {
            throw new Exception("SSN cannot be null.");
        }

        if( password == null ) {
            throw new Exception("Password cannot be null.");
        }
    }

    public void menu(){

        int choice;
        Scanner in = new Scanner(System.in);
        boolean loop = true;


        while(loop){

            System.out.printf("1- Personal info menu \n");
            System.out.printf("2- Change password.\n");
            System.out.printf("3- Exit.\n");

            choice = in.nextInt();

            switch (choice){
                case 1:
                    System.out.println(this);
                    break;
                case 2:
                    airlineSystemStorage.changePasswordwithMenu(in);
                    break;
                case 3: loop = false;
                    break;
                default: System.out.printf("Error. Your input is invalid..\n");
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "is pilot:"+isPilot;
    }

}
