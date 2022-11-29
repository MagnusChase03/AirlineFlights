import airlines.Routes;

import java.io.*;

public class Main {

    // Create graph from file
    // Returns airline routes graph or null if failed somewhere
    private static Routes createAirlineRoutes(String filepath) {

        try {
            
            System.out.println("Creating airlineRoutes...");
            Routes airlineRoutes = new Routes();
            BufferedReader read = new BufferedReader(new FileReader(filepath));

            // Skip first line
            read.readLine();

            // Read all subseqent lines and insert cities into the airlineRoutes
            String line = "";
            while ((line = read.readLine()) != null) {

                String[] data = line.split("\\|");
                if (data.length < 4) {

                    System.out.println("Data is incorrect format, from " + line);
                    return null;

                }

                String source = data[0];
                String destination = data[1];
                double flightCost = Double.parseDouble(data[2]);
                int flightTime = Integer.parseInt(data[3]);

                if (airlineRoutes.cityExist(source) == false) {

                    airlineRoutes.addCity(source);

                }

                if (airlineRoutes.cityExist(destination) == false) {

                    airlineRoutes.addCity(destination);

                }

                // Undirected graph
                if (airlineRoutes.routeExist(source, destination) == false) {

                    airlineRoutes.addRoute(source, destination, flightTime, flightCost);

                }

                if (airlineRoutes.routeExist(destination, source) == false) {

                    airlineRoutes.addRoute(destination, source, flightTime, flightCost);

                }

            }

            System.out.println("Created airlineRoutes...");
            return airlineRoutes;

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;

    }

    public static void main(String[] args) {

        Routes airlineRoutes = createAirlineRoutes("routes.txt");
        airlineRoutes.getRoutes();
        airlineRoutes.backtrack("Austin", "Dallas", "./test.dat");
        airlineRoutes.getRoutes();

    }

}