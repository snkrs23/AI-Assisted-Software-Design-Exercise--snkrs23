import java.util.Scanner;

public class LinearApproximation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the polynomial expression: ");
        String expression = scanner.nextLine();

        // Remove whitespace and convert to lowercase for easier processing
        expression = expression.replaceAll("\\s", "").toLowerCase();

        // Split the expression into individual terms
        String[] terms = expression.split("\\+");

        StringBuilder derivative = new StringBuilder();

        for (String term : terms) {
            String[] parts = term.split("x");

            if (parts.length == 0) {
                // The term does not contain 'x', so it's a constant term
                continue;
            } else if (parts.length == 1) {
                // The term is of the form 'cx' (e.g., 3x)
                int coefficient = Integer.parseInt(parts[0]);
                int power = 1;

                derivative.append(coefficient * power);
            } else {
                // The term is of the form 'c*x^n' (e.g., 2x^3)
                int coefficient = Integer.parseInt(parts[0]);
                int power = Integer.parseInt(parts[1].substring(1));

                derivative.append(coefficient * power);
                derivative.append("x^");
                derivative.append(power - 1);
            }

            derivative.append(" + ");
        }

        // Remove the trailing "+"
        derivative.setLength(derivative.length() - 3);

        System.out.println("The derivative of the polynomial is: " + derivative.toString());

        scanner.close();
    }
}
