package airlines;

public class Stack {

    private City top;

    public Stack() {

        top = null;

    }

    // Pushes a new city on top of the stack
    public boolean push(City city) {

        if (top == null) {

            top = city;
            return true;

        }

        if (city.getNextCity() == null) {

            city.setNextCity(top);
            top = city;
            return true;

        } else {

            System.out.println("The city that was being pushed had non null next.");
            return false;

        }

    }

    // Pops city off of the stack
    public City pop() {

        if (top == null) {

            return null;

        }

        City tmp = top;
        top = top.getNextCity();
        return tmp;

    }

    public City peek() {

        return top;

    }

    public boolean isEmpty() {return top == null;}

    public double getTotalTime() {

        if (top == null) {

            return 0;

        }

        double flightTime = 0.0;
        City head = top;
        while (head != null) {

            flightTime += head.getFlightTime();
            head = head.getNextCity();

        }

        return flightTime;

    }

    public double getTotalCost() {

        if (top == null) {

            return 0;

        }

        double flightCost = 0.0;
        City head = top;
        while (head != null) {

            flightCost += head.getFlightCost();
            head = head.getNextCity();

        }

        return flightCost;

    }

    public String toString() {

        String result = "";
        City head = top;
        while (head != null) {

            result += head.getName() + " <- ";
            head = head.getNextCity();

        }

        return result;

    }

}