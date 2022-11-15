package airlines;

public class City {

    // City Data
    private String name;
    private int flightTime;
    private double flightCost;

    // Pointers for adjacency list
    private City nextCity;
    private City adjacentCity;

    // Constructors
    public City(String name) {

        this.name = name;
        this.flightTime = 0;
        this.flightCost = 0.0;

        this.nextCity = null;
        this.adjacentCity = null;

    }

    public City(String name, int flightTime, double flightCost) {

        this.name = name;
        this.flightTime = flightTime;
        this.flightCost = flightCost;

        this.nextCity = null;
        this.adjacentCity = null;

    }

    // Getters
    public String getName() {return name;}
    public int getFlightTime() {return flightTime;}
    public double getFlightCost() {return flightCost;}

    public City getNextCity() {return nextCity;}
    public City getAdjacentCity() {return adjacentCity;}

    // Setters
    public void setName(String name) {this.name = name;}
    public void setFlightTime(int flightTime) {this.flightTime = flightTime;}
    public void setFlightCost(double flightCost) {this.flightCost = flightCost;}

    public void setNextCity(City nextCity) {this.nextCity = nextCity;}
    public void setAdjacentCity(City adjacentCity) {this.adjacentCity = adjacentCity;}

}