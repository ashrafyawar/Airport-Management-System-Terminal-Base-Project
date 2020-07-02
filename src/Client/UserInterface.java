package Client;

/**UserInterface used for all users of the system.*/
public interface UserInterface {

    /** Method for receiving SSN.
     * @return SSN as string.
     * */
    String getSSN();

    /** Method for setting a new SSN.
     * @param SSN as a new SSN to assign.
     * */
    void setSSN( String SSN ) throws Exception;

    /** Method for receiving password.
     * @return password as String.
     * */
    String getPassword();

    /** Method for setting a new password.
     * @param password as a new password to assign.
     * */
    void setPassword( String password ) throws Exception;

}
