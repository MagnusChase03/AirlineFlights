package airlines;

protected class City {

    // City Data
    private String name;
    private int flightTime;
    private double flightCost;

    // Pointers for adjacency list
    private City nextCity;
    private City adjacentCity;

    // Constructors
    protected City(String name) {

        this.name = name;
        this.flightTime = 0;
        this.flightCost = 0.0;

        this.nextCity = null;
        this.adjacentCity = null;

    }

    protected City(String name, int flightTime, double flightCost) {

        this.name = name;
        this.flightTime = flightTime;
        this.flightCost = flightCost;

        this.nextCity = null;
        this.adjacentCity = null;

    }

    // Getters
    protected String getName() {return name;}
    protected int getFlightTime() {return flightTime;}
    protected double getFlightCost() {return flightCost;}

    protected City getNextCity() {return nextCity;}
    protected City getAdjacentCity() {return adjacentCity;}

    // Setters
    protected void setName(String name) {this.name = name;}
    protected void setFlightTime(int flightTime) {this.flightTime = flightTime;}
    protected void setFlightCost(double flightCost) {this.flightCost = flightCost;}

    protected void setNextCity(City nextCity) {this.nextCity = nextCity;}
    protected void setAdjacentCity(City adjacentCity) {this.adjacentCity = adjacentCity;}

}