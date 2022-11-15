package airlines;

/*

THE REASON IM ADDING AT END OF LINKED LIST IS TO CHECK FOR DUPLICATED AND PREVENT IT FROM HAPPENING,
THIS DOES PREVENT O(1) TIME FOR INSERT, BUT IT IS TO KEEP DATA CLEAN AND PREVENT ERRORS, DEFENSIVE PROGRAMMING

*/

public class Routes {

    private City routes;

    // Constructors
    public Routes() {

        this.routes = null;

    }

    // Adds a new city to the routes
    // O(V)
    // Returns true if the city was added, false if city already exists
    public boolean addCity(String name) {

        System.out.println("Adding " + name + " to the adjacency list...");
        City newCity = new City(name);

        if (routes == null) {

            routes = newCity;
            System.out.println("Added " + name + " to the adjacency list.");
            return true;

        }

        // Traverse routes until end to add new city
        City head = routes;
        while (head.getNextCity() != null) {

            if (head.getName() == name) {

                System.out.println("City " + name + " already exits in adjacency list, did not add it.");
                return false;

            }

            head = head.getNextCity();

        }

        // Next city should be null
        head.setNextCity(newCity);
        System.out.println("Added " + name + " to the adjacency list.");

        return true;

    }

    // Adds a new routes from a city
    // O(V + E)
    // Returns true if route was added, false if route already existed or source city doesnt exit
    public boolean addRoute(String source, String destination, int flightTime, double flightCost) {

        System.out.println("Adding route from " + source + " to " + destination + " to routes...");
        City newCity = new City(destination, flightTime, flightCost);

        // Traverse routes until the source city
        City head = routes;
        while (head != null && head.getName() != source) {

            head = head.getNextCity();

        }

        if (head == null) {

            System.out.println("City " + source + " does not exist in adjacency list when adding to routes.");
            return false;

        }

        // Head should be source city
        City adjacent = head.getAdjacentCity();
        if (adjacent == null) {

            head.setAdjacentCity(newCity);
            System.out.println("Added route from " + source + " to " + destination + " to routes.");
            return true;

        }

        // Loop to end of adjacent cities to add route
        while (adjacent.getNextCity() != null) {

            if (adjacent.getName() == destination) {

                System.out.println("Route from " + source + " to " + destination + " already exits in adjacency list, did not add it.");
                return false;

            }

            adjacent = adjacent.getNextCity();

        }

        // Adjacent.next should be null, set to new city with route information
        adjacent.setNextCity(newCity);

        System.out.println("Added route from " + source + " to " + destination + " to routes.");

        return true;

    }

    // Finds

    // Returns weither a city exist in the adjacency list
    public boolean cityExist(String name) {

        City head = routes;

        while (head != null && head.getName() != name) {

            head = head.getNextCity();

        }

        return head != null;

    }

    // Returns weither a route exist in the adjacency list
    public boolean routeExist(String source, String destination) {

        City head = routes;

        while (head != null && head.getName() != source) {

            head = head.getNextCity();

        }

        if (head == null) {

            return false;

        }

        City adjacent = head.getAdjacentCity();
        while (adjacent != null && adjacent.getName() != destination) {

            adjacent = adjacent.getNextCity();

        }

        return adjacent != null;

    }

    // Getters

    // O(V)
    public void getCities() {

        System.out.println("Cities in adjacency list");
        spacer();

        // Print all cities in adjacency list
        City head = routes;
        while (head != null) {

            System.out.println(head.getName());
            head = head.getNextCity();

        }

    }

    // O(V + E)
    public void getRoutes() {

        System.out.println("Routes in adjacency list");
        spacer();

        // Print all cities and routes in adjacency list
        City head = routes;
        while (head != null) {

            System.out.print(head.getName());

            if (head.getAdjacentCity() != null) {

                // Print all adjacent cities
                City adjacent = head.getAdjacentCity();
                while (adjacent != null) {

                    System.out.print(" -> " + adjacent.getName());
                    adjacent = adjacent.getNextCity();

                }

            }

            System.out.println();
            head = head.getNextCity();

        }

    }

    // Helper spacer function
    private void spacer() {

        for (int i = 0; i < 20; i++) {

            System.out.print("=");

        }

        System.out.println();

    }

}