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

    // Backtracks using created graph to print best routes into the output file
    private static void findBestRoutes(Routes airlineRoutes, String filepath, String outputFilePath) {

        try {

            BufferedReader read = new BufferedReader(new FileReader(filepath));

            // Skip first line
            read.readLine();

            // Backtrack each line given
            String line = "";
            while ((line = read.readLine()) != null) {

                String[] data = line.split("\\|");
                if (data.length < 3) {

                    System.out.println("Backtracking routes in incorrect format, " + line);
                    return;

                }

                airlineRoutes.backtrack(data[0], data[1], data[2], outputFilePath);

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public static void main(String[] args) {

        if (args.length < 3) {

            System.out.println("Usage: java Main.java [Graph Input File] [Routes File] [Output File]");
            return;

        }

        Routes airlineRoutes = createAirlineRoutes(args[0]);
        airlineRoutes.getRoutes();
        findBestRoutes(airlineRoutes, args[1], args[2]);

    }

}