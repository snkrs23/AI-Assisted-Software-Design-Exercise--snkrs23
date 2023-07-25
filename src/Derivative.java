import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//An interface for parsing coefficient values from strings.
interface CoefficientParser {
    double parse(String coefficient);
}

//A CoefficientParser implementation for parsing integer coefficients.
class IntegerCoefficientParser implements CoefficientParser {
    @Override
    public double parse(String coefficient) {
        return Integer.parseInt(coefficient);
    }
}

//A CoefficientParser implementation for parsing fraction coefficients.
class FractionCoefficientParser implements CoefficientParser {
    @Override
    public double parse(String coefficient) {
        String[] fractionParts = coefficient.split("/");
        int numerator = Integer.parseInt(fractionParts[0]);
        int denominator = Integer.parseInt(fractionParts[1]);
        return (double) numerator / denominator;
    }
}

//A CoefficientParser implementation for parsing decimal coefficients.
class DecimalCoefficientParser implements CoefficientParser {
    @Override
    public double parse(String coefficient) {
        return Double.parseDouble(coefficient);
    }
}

//A simple polynomial derivative calculator that calculates the nth order derivative of a polynomial.
public class Derivative {
    private static final Pattern termPattern = Pattern.compile("(-?\\d+(\\.\\d+)?/?\\d*(\\.\\d+)?)?x(\\^(-?\\d+(\\.\\d+)?))?");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the polynomial expression: ");
        String expression = scanner.nextLine();

        // Remove whitespace and convert to lowercase for easier processing
        expression = expression.replaceAll("\\s", "").toLowerCase();

        System.out.print("Enter the order of the derivative: ");
        int order = scanner.nextInt();

        try {
            String result = calculateDerivative(expression, order);
            System.out.println("The " + order + "-th order derivative of the polynomial is: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid polynomial expression. Please enter a valid expression.");
        }

        scanner.close();
    }

    /**
     * Calculates the nth order derivative of a given polynomial expression.
     *
     * @param expression The polynomial expression to calculate the derivative for.
     * @param order      The order of the derivative to calculate.
     * @return The resulting polynomial expression after taking the derivative.
     */
    public static String calculateDerivative(String expression, int order) {
        for (int i = 0; i < order; i++) {
            expression = calculateDerivative(expression);
        }
        return expression;
    }

    /**
     * Calculates the derivative of a single term in the polynomial expression.
     *
     * @param expression The term to calculate the derivative for.
     * @return The resulting derivative term.
     */
    public static String calculateDerivative(String expression) {
        Matcher matcher = termPattern.matcher(expression);
        StringBuilder derivative = new StringBuilder();

        while (matcher.find()) {
            String coefficientStr = matcher.group(1);
            String exponentStr = matcher.group(5);

            double coefficient = parseCoefficient(coefficientStr);
            double exponent = parseExponent(exponentStr);

            double newCoefficient = coefficient * exponent;
            double newExponent = exponent - 1;

            if (newCoefficient != 0) {
                if (derivative.length() > 0) {
                    derivative.append(" + ");
                }

                if (newExponent == 0) {
                    derivative.append(newCoefficient);
                } else if (newExponent == 1) {
                    derivative.append(newCoefficient);
                    derivative.append("x");
                } else {
                    derivative.append(newCoefficient);
                    derivative.append("x^");
                    derivative.append(newExponent);
                }
            }
        }

        return derivative.toString();
    }

    /**
     * Parses the coefficient value from a string representation.
     *
     * @param coefficient The coefficient string to parse.
     * @return The parsed coefficient value as a double.
     */
    private static double parseCoefficient(String coefficient) {
        if (coefficient == null || coefficient.isEmpty() || coefficient.equals("-")) {
            return 1.0;
        }

        CoefficientParser parser = CoefficientParserFactory.createParser(coefficient);
        return parser.parse(coefficient);
    }

    /**
     * Parses the exponent value from a string representation.
     *
     * @param exponent The exponent string to parse.
     * @return The parsed exponent value as a double.
     */
    private static double parseExponent(String exponent) {
        if (exponent == null || exponent.isEmpty()) {
            return 1.0;
        }

        return Double.parseDouble(exponent);
    }
}

// Add a factory class to create parsers based on the coefficient type
class CoefficientParserFactory {
    public static CoefficientParser createParser(String coefficient) {
        if (coefficient.contains("/")) {
            return new FractionCoefficientParser();
        } else if (coefficient.contains(".")) {
            return new DecimalCoefficientParser();
        } else {
            return new IntegerCoefficientParser();
        }
    }
}