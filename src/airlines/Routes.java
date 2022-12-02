package airlines;

import java.util.HashSet;
import java.util.HashMap;
import java.io.*;

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
    public boolean backtrack(String source, String destination, String method, String outputPath) {

        // spacer();
        // System.out.println("Backtracking from " + source + " to " + destination + "...");

        // // Make sure both cities exist
        // if (!(cityExist(source) && cityExist(destination))) {

        //     System.out.println("Either the source or destination did not exist when backtracking.");
        //     return false;

        // }

        // Stack stack = new Stack();
        // Stack currentRoute = new Stack();
        // HashSet<String> visited = new HashSet<>();

        // HashMap<String, Double> resultVector = new HashMap<>();

        // City start = new City(source, 0, 0);
        // start.setAdjacentCity(getAdjacent(source));
        // stack.push(start);

        // // While there is a route to check
        // while(!stack.isEmpty()) {

        //     City tmp = stack.peek();
        //     if (visited.contains(tmp.getName())) {

        //         if (currentRoute.peek() != null && currentRoute.peek().getName().equals(stack.peek().getName())) {
                    
        //             currentRoute.pop();

        //         }

        //         stack.pop();

        //         continue;

        //     }

        //     City currentCity = new City(tmp.getName(), tmp.getFlightTime(), tmp.getFlightCost());
        //     currentCity.setAdjacentCity(getAdjacent(currentCity.getName()));

        //     currentRoute.push(currentCity);
        //     spacer();
        //     System.out.println(stack);
        //     System.out.println(currentRoute);

        //     // If this is our destination pop back to another city that had another route
        //     if (currentCity.getName().equals(destination)) {

        //         System.out.println("^^^^^^^^");
        //         if (method.equals("T")) {

        //             resultVector.put(currentRoute.toString(), currentRoute.getTotalTime());

        //         } else {

        //             resultVector.put(currentRoute.toString(), currentRoute.getTotalCost());

        //         }         

        //         while (currentRoute.peek() != null && currentRoute.peek().getName().equals(stack.peek().getName())) {

        //             System.out.println("SECOND POP " + stack.peek().getName() + " and " + currentRoute.peek().getName());
        //             stack.pop();
        //             currentRoute.pop();

        //         }

        //         continue;

        //     }

        //     // Mark this city as visited and add adjacent cities that need to be searched
        //     visited.add(currentCity.getName());

        //     City adjacent = getAdjacent(currentCity.getName());
        //     while (adjacent != null) {

        //         if (!visited.contains(adjacent.getName())) {

        //             City tmp2 = new City(adjacent.getName(), adjacent.getFlightTime(), adjacent.getFlightCost());
        //             tmp2.setAdjacentCity(getAdjacent(tmp2.getName()));
        //             stack.push(tmp2);

        //         }

        //         adjacent = adjacent.getNextCity();

        //     }

        // }

        // saveBest(resultVector, method, outputPath);

        // System.out.println("Backtracking from " + source + " to " + destination + " done.");

        spacer();
        if (!(cityExist(source) && cityExist(destination))) {

            System.out.println("Either the source or destination did not exist when backtracking.");
            return false;

        }

        Stack stack = new Stack();
        Stack currentRoute = new Stack();
        HashSet<String> visited = new HashSet<>();
        HashMap<String, Double> flightTimes = new HashMap<>();
        HashMap<String, Double> flightCosts = new HashMap<>();

        City start = new City(source, 0, 0);
        start.setAdjacentCity(getAdjacent(source));
        stack.push(start);

        while (!stack.isEmpty()) {

            City tmp = stack.peek();

            City currentCity = new City(tmp.getName(), tmp.getFlightTime(), tmp.getFlightCost());
            currentCity.setAdjacentCity(getAdjacent(currentCity.getName()));

            currentRoute.push(currentCity);

            if (currentCity.getName().equals(destination)) {

                System.out.println(currentRoute);

                flightTimes.put(currentRoute.toString(), currentRoute.getTotalTime());
                flightCosts.put(currentRoute.toString(), currentRoute.getTotalCost());
                
                while (currentRoute.peek() != null && currentRoute.peek().getName().equals(stack.peek().getName())) {

                    visited.remove(stack.peek().getName());
                    stack.pop();
                    currentRoute.pop();
                    

                }

                continue;

            }

            visited.add(currentCity.getName());

            int countAdded = 0;
            City adjacent = getAdjacent(currentCity.getName());
            while (adjacent != null) {

                if (!visited.contains(adjacent.getName())) {

                    City tmp2 = new City(adjacent.getName(), adjacent.getFlightTime(), adjacent.getFlightCost());
                    tmp2.setAdjacentCity(getAdjacent(tmp2.getName()));
                    stack.push(tmp2);
                    countAdded += 1;

                }

                adjacent = adjacent.getNextCity();

            }

            if (countAdded == 0) {
                
                while (currentRoute.peek() != null && currentRoute.peek().getName().equals(stack.peek().getName())) {

                    visited.remove(stack.peek().getName());
                    stack.pop();
                    currentRoute.pop();
                    

                }

            }

        }

        saveBest(source, destination, flightTimes, flightCosts, method, outputPath);

        System.out.println("Backtracking from " + source + " to " + destination + " done.");

        return true;

    }

    // Save best results from backtracking
    private void saveBest(String source, String destination, HashMap<String, Double> flightTimes, HashMap<String, Double> flightCosts, String method, String outputPath) {

        // Save top 3 times and costs to file
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath, true));

            // Top 3 times
            writer.write("Flight " + source + "->" + destination + " (" + method + ")");
            writer.newLine();

            if (method.equals("T")) {

                for (int i = 0; i < 3; i ++) {

                    double min = Double.MAX_VALUE;
                    String minKey = "";
                    for (String key : flightTimes.keySet()) {

                        if (flightTimes.get(key) < min) {

                            min = flightTimes.get(key);
                            minKey = key;

                        }

                    }


                    if (!minKey.equals("")) {

                        System.out.println("Saving " + minKey + " " + min);

                        writer.write("Path " + i + ": " + minKey + " " + flightTimes.get(minKey) + " minutes" + " $" + flightCosts.get(minKey));
                        writer.newLine();
                        flightTimes.remove(minKey);

                    }

                }

            } else {

                for (int i = 0; i < 3; i ++) {

                    double min = Double.MAX_VALUE;
                    String minKey = "";
                    for (String key : flightCosts.keySet()) {

                        if (flightCosts.get(key) < min) {

                            min = flightCosts.get(key);
                            minKey = key;

                        }

                    }


                    if (!minKey.equals("")) {

                        System.out.println("Saving " + minKey + " " + min);

                        writer.write("Path " + i + ": " + minKey + " " + flightTimes.get(minKey) + " minutes" + " $" + flightCosts.get(minKey));
                        writer.newLine();
                        flightCosts.remove(minKey);

                    }

                }

            }

            writer.close();
            

        } catch (IOException e) {

            e.printStackTrace();

        }

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