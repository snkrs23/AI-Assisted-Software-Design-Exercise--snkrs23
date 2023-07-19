import java.util.Scanner;

public class Derivative {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the polynomial expression: ");
        String expression = scanner.nextLine();

        double[] coefficients = parseCoefficients(expression);
        double[] derivativeCoefficients = calculateDerivative(coefficients);

        System.out.println("Derivative: " + formatPolynomial(derivativeCoefficients));
    }

    private static double[] parseCoefficients(String expression) {
        String[] terms = expression.split("\\s+");

        int highestPower = 0;
        for (String term : terms) {
            if (term.matches(".*x\\^\\d+.*")) {
                String[] parts = term.split("x\\^");
                int power = Integer.parseInt(parts[1]);
                highestPower = Math.max(highestPower, power);
            }
        }

        double[] coefficients = new double[highestPower + 1];
        for (String term : terms) {
            if (term.matches(".*x\\^\\d+.*")) {
                String[] parts = term.split("x\\^");
                int power = Integer.parseInt(parts[1]);
                double coefficient = parseCoefficient(parts[0]);
                coefficients[power] = coefficient;
            } else if (term.matches(".*x.*")) {
                double coefficient = parseCoefficient(term.split("x")[0]);
                coefficients[1] = coefficient;
            } else {
                double coefficient = parseCoefficient(term);
                coefficients[0] = coefficient;
            }
        }

        return coefficients;
    }

    private static double parseCoefficient(String term) {
        term = term.trim();
        if (term.equals("+")) {
            return 1.0;
        } else if (term.equals("-")) {
            return -1.0;
        } else {
            return Double.parseDouble(term);
        }
    }

    private static double[] calculateDerivative(double[] coefficients) {
        int n = coefficients.length;
        double[] derivativeCoefficients = new double[n - 1];

        for (int i = 1; i < n; i++) {
            derivativeCoefficients[i - 1] = coefficients[i] * i;
        }

        return derivativeCoefficients;
    }

    private static String formatPolynomial(double[] coefficients) {
        StringBuilder builder = new StringBuilder();

        for (int i = coefficients.length - 1; i >= 0; i--) {
            if (coefficients[i] != 0) {
                if (i < coefficients.length - 1) {
                    builder.append(" + ");
                }
                if (i > 0) {
                    builder.append(coefficients[i]).append("x^").append(i);
                } else {
                    builder.append(coefficients[i]);
                }
            }
        }

        return builder.toString();
    }
}





