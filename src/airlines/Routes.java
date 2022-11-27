package airlines;

import java.util.HashSet;

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

            if (head.getName().equals(name)) {

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
        while (head != null && !head.getName().equals(source)) {

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

            if (adjacent.getName().equals(destination)) {

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

    // Backtracking
    // Returns weither the backtrack was successful or not
    public boolean backtrack(String source, String destination) {

        spacer();
        System.out.println("Backtracking from " + source + " to " + destination + "...");

        // Make sure both cities exist
        if (!(cityExist(source) && cityExist(destination))) {

            System.out.println("Either the source or destination did not exist when backtracking.");
            return false;

        }

        Stack stack = new Stack();
        Stack currentRoute = new Stack();
        int routeLength = 0;
        HashSet<String> visited = new HashSet<>();

        City start = new City(source, 0, 0);
        start.setAdjacentCity(getAdjacent(source));
        stack.push(start);

        while(!stack.isEmpty()) {

            City currentCity = stack.pop();
            currentCity.setNextCity(null);

            currentRoute.push(currentCity);
            routeLength += 1;

            if (currentCity.getName().equals(destination)) {

                System.out.println(currentRoute);

                for (int i = 1; i < routeLength; i++) {

                    currentRoute.pop();

                }

                routeLength = 1;

                continue;

            }

            if (!visited.contains(currentCity.getName())) {

                System.out.println(" To " + currentCity.getName());
                visited.add(currentCity.getName());

            }

            City adjacent = getAdjacent(currentCity.getName());
            while (adjacent != null) {

                if (!visited.contains(adjacent.getName())) {

                    City tmp = new City(adjacent.getName(), adjacent.getFlightTime(), adjacent.getFlightCost());
                    tmp.setAdjacentCity(getAdjacent(tmp.getName()));
                    stack.push(tmp);

                }

                adjacent = adjacent.getNextCity();

            }

            // System.out.println("Stack " + stack);

        }

        System.out.println("Backtracking from " + source + " to " + destination + " done.");
        return true;

    }

    // Finds

    // Returns weither a city exist in the adjacency list
    public boolean cityExist(String name) {

        City head = routes;

        while (head != null && !head.getName().equals(name)) {

            head = head.getNextCity();

        }

        return head != null;

    }

    // Returns weither a route exist in the adjacency list
    public boolean routeExist(String source, String destination) {

        City head = routes;

        while (head != null && !head.getName().equals(source)) {

            head = head.getNextCity();

        }

        if (head == null) {

            return false;

        }

        City adjacent = head.getAdjacentCity();
        while (adjacent != null && !adjacent.getName().equals(destination)) {

            adjacent = adjacent.getNextCity();

        }

        return adjacent != null;

    }

    // Getters
    private City getCity(String name) {

        City head = routes;
        while (head != null) {

            if (head.getName().equals(name)) {

                return head;

            }
            head = head.getNextCity();

        }

        return null;

    }

    private City getAdjacent(String name) {

        City head = routes;
        while (head != null) {

            if (head.getName().equals(name)) {

                return head.getAdjacentCity();

            }
            head = head.getNextCity();

        }

        return null;

    }

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