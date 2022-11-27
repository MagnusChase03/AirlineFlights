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

    public boolean isEmpty() {return top == null;}

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