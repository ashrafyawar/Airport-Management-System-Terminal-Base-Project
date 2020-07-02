package Airline;

import java.util.Comparator;

/**
 * Informative class which has several properties about Aircraft.
 * @author Nevzat Seferoglu
 * @version 1.1
 */
public class Aircraft {

    /**Span of wing, affects flight score.*/
    final Double wingspan;

    /**Number of passengers, that can be in the plane at the same time.*/
    final Double passengerCapacity;

    /**Empy weight (kg) affects flight score.*/
    final Double emptyWeightAsKg;

    /**Max fuel cap can tell us how that plane can go far.*/
    final Double maxFuelCapacity;
    /*-----------------------------*/

    int compareScore;
    static int flightScore;

    /**Aircraft Constructor
     * @param wingspan
     * @param passengerCapacity
     * @param emptyWeightAsKg
     * @param maxFuelCapacity
     * */
    Aircraft(Double wingspan, Double passengerCapacity, Double emptyWeightAsKg, Double maxFuelCapacity) {
        this.wingspan = wingspan;
        this.passengerCapacity = passengerCapacity;
        this.emptyWeightAsKg = emptyWeightAsKg;
        this.maxFuelCapacity = maxFuelCapacity;
    }

    /**Returns the compareScore.
     * @return compareScore, as integer.*/
    public int getCompareScore() {
        return compareScore;
    }

    /**Returns the flightScore.
     * @return flightScore, as integer.*/
    public static int getFlightScore() {
        return flightScore;
    }

    /**Returns the emptyWeightAsKg.
     * @return emptyWeightAsKg, as double.*/
    public Double getEmptyWeightAsKg() {
        return emptyWeightAsKg;
    }

    /**Returns the passengerCapacity.
     * @return passengerCapacity, as double.*/
    public Double getPassengerCapacity() {
        return passengerCapacity;
    }

    /**Returns the wingspan.
     * @return wingspan, as double.*/
    public Double getWingspan() {
        return wingspan;
    }

    /**Sets a new score to aircraft's score.
     * @param flightScore to set.*/
    public static void setFlightScore(int flightScore) {Aircraft.flightScore = flightScore;}

    /**Method to see compare score to flight score.*/
    public void assignCompare(){
        compareScore=Math.abs(getFlightScore()-getScore());
    }

    @Override
    public String toString() {

        return  " Wingspan length : " + getWingspan() + '\n' +
                " Passenger capacity : " + getPassengerCapacity() + '\n' +
                " Empty weight : " + getEmptyWeightAsKg() + '\n';
    }

    /**
     * To get Comparator for using sort algorithms.
     * Switch structure used to determine which feature is
     * gonna be used in sorting
     * @return Comparator to make sorting
     */
    public static Comparator<Aircraft> getComparator(){
        Comparator<Aircraft> comparator = new Comparator<Aircraft>() {
            @Override
            public int compare(Aircraft aircraft, Aircraft t1) {
                return Math.abs(aircraft.getCompareScore()-t1.getCompareScore());
            }
        };
        return comparator;
    }

    @Override
    public boolean equals(Object obj) {
        if( obj ==  this ) {
            return true;
        } else if( !(obj instanceof Aircraft) ) {
            return true;
        }

        return  ((Aircraft) obj).wingspan.equals( this.wingspan ) &&
                ((Aircraft) obj).passengerCapacity.equals( this.passengerCapacity ) &&
                ((Aircraft) obj).emptyWeightAsKg.equals( this.emptyWeightAsKg ) &&
                ((Aircraft) obj).maxFuelCapacity.equals( this.maxFuelCapacity );
    }

    /**Method for getting score.
     * Score is calculated from wingspan, capacity etc.
     * it is between 0 and 30.
     * @return the total score.*/
    public int getScore(){

        int score = 0;
        int temp = 0;

        temp = (int)(maxFuelCapacity - 20)/15 ;
        if(temp < 0 ) temp = 0;
        else if(temp > 10) temp = 10;
        score +=  temp;

        temp = (int)(getPassengerCapacity() - 100) / 40;
        if(temp < 0 ) temp = 0;
        else if(temp > 10) temp = 10;
        score +=  temp;

        temp = (int)(getEmptyWeightAsKg() - 50) / 15;
        if(temp < 0 ) temp = 0;
        else if(temp > 10) temp = 10;
        score +=  temp;

        return score;
    }
}

