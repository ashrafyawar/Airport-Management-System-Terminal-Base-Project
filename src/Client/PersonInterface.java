package Client;

/**Person interface that represents the methods of Person class.*/
public interface PersonInterface {

    /** Method for receiving name of person.
     * @return name as string.
     * */
    String getName();

    /** Method for receiving surname of person.
     * @return surname as string.
     * */
    String getSurname();

    /** Method for setting a new name.
     * @param name as a new name to assign.
     * */
    void setName( String name );

    /** Method for setting a new surname.
     * @param surname as a new surname to assign.
     * */
    void setSurname( String surname );

}
