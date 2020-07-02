package Airline;

public enum TicketType {

    ECONOMY_CLASS ("ECONOMY_CLASS"),
    BUSINESS_CLASS("BUSINESS_CLASS"),
    FIRST_CLASS("FIRST_CLASS");

    private final  String nameOfClass;
    private TicketType(String nameOfClass) {
        this.nameOfClass = nameOfClass;
    }
    public String getNameOfClass() {
        return nameOfClass;
    }

}
