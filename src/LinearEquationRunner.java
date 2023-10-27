import java.util.Scanner;

class LinearEquationRunner {
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Linear Equation Calculator!");

        String coordinate1 = getCoordinateFromUser(scanner, 1);
        String coordinate2 = getCoordinateFromUser(scanner, 2);

        LinearEquation equation = createLinearEquation(coordinate1, coordinate2);

        if (equation == null) {
            System.out.println("The line is vertical and cannot be represented by y = mx + b.");
            return;
        }

        System.out.println(equation.lineInfo());

        System.out.print("Enter an x value: ");
        double xValue = scanner.nextDouble();
        String coordinate = equation.coordinateForX(xValue);
        System.out.println("The coordinate for x = " + xValue + " is " + coordinate);
    }

    private String getCoordinateFromUser(Scanner scanner, int pointNumber) {
        System.out.print("Enter coordinate " + pointNumber + " (in the format '(x, y)'): ");
        return scanner.next();
    }

    private LinearEquation createLinearEquation(String coordinate1, String coordinate2) {
        int[] point1 = extractCoordinates(coordinate1);
        int[] point2 = extractCoordinates(coordinate2);

        if (point1[0] == point2[0]) {
            System.out.println("The line is vertical and cannot be represented by y = mx + b.");
            return null;
        }

        return new LinearEquation(point1[0], point1[1], point2[0], point2[1]);
    }

    private int[] extractCoordinates(String coordinate) {
        int commaIndex = coordinate.indexOf(",");
        int x = Integer.parseInt(coordinate.substring(1, commaIndex).trim());
        int y = Integer.parseInt(coordinate.substring(commaIndex + 1, coordinate.length() - 1).trim());
        return new int[] { x, y };
    }
}