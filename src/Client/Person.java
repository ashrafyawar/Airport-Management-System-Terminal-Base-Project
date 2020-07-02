package Client;

import java.util.Objects;

/**Person class that is comparable.*/
public class Person implements PersonInterface , Comparable<Person> {

    /**Name of the user.*/
    private String name;

    /**Surname of the user.*/
    private String surname;

    /**Constructor of the Person
     * @param name As the name of the user.
     * @param surname as the surname of the user.*/
    public Person( final String name , final String surname ) throws Exception {

        if( name == null || surname == null ) {
            throw new Exception("name of surname cannot be null.");
        }

        this.name = name;
        this.surname = surname;

    }

    /**
     * Return the name of person.
     * @return name of the person.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Return the surname of the person.
     * @return surname of the person.
     */
    @Override
    public String getSurname() {
        return surname;
    }

    /**
     * Set the name of the person.
     * @param name as a new name to assign.
     */
    @Override
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * Set the surname of the person.
     * @param surname as a new surname to assign.
     */
    @Override
    public void setSurname( String surname ) {
        this.surname = surname;
    }


    /** Method to compare an object to this.
     * @param o Object to compare.
     * @return true if they are equal.*/
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        Person person = (Person) o;

        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname);
    }


    /** Method that retursn hashcode of object.
     * @return hashCode, as integer.*/
    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }


    /** toString method to represent the object in form of string.
     * @return String format.*/
    @Override
    public String toString() {
        return name +"  " + surname ;
    }


    /** Method to compare an object to this.
     * @param person Object to compare.
     * @return int value of the comparison.*/
    @Override
    public int compareTo(Person person) {

        if( person != null ) {
            return this.getName().compareTo( person.getName() );
        } else {
            return 1;
        }

    }
}
