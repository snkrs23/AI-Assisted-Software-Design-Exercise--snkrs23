import java.util.Scanner;

interface TermIterator {
    boolean hasNext();

    String next();
}

class PolynomialIterator implements TermIterator {
    private final String[] terms;
    private int index = 0;

    public PolynomialIterator(String expression) {
        this.terms = expression.split("\\+");
    }

    @Override
    public boolean hasNext() {
        return index < terms.length;
    }

    @Override
    public String next() {
        if (hasNext()) {
            return terms[index++];
        }
        return null;
    }
}

interface CoefficientParser {
    double parse(String coefficient);
}

class IntegerCoefficientParser implements CoefficientParser {
    @Override
    public double parse(String coefficient) {
        return Integer.parseInt(coefficient);
    }
}

class FractionCoefficientParser implements CoefficientParser {
    @Override
    public double parse(String coefficient) {
        String[] fractionParts = coefficient.split("/");
        int numerator = Integer.parseInt(fractionParts[0]);
        int denominator = Integer.parseInt(fractionParts[1]);
        return (double) numerator / denominator;
    }
}

class DecimalCoefficientParser implements CoefficientParser {
    @Override
    public double parse(String coefficient) {
        return Double.parseDouble(coefficient);
    }
}

interface CoefficientParserFactory {
    CoefficientParser createParser(String coefficient);
}

class DefaultCoefficientParserFactory implements CoefficientParserFactory {
    @Override
    public CoefficientParser createParser(String coefficient) {
        if (coefficient.contains("/")) {
            return new FractionCoefficientParser();
        } else if (coefficient.contains(".")) {
            return new DecimalCoefficientParser();
        } else {
            return new IntegerCoefficientParser();
        }
    }
}

class Polynomial {
    private final String expression;

    public Polynomial(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}

class PolynomialDerivativeCalculator {
    private final CoefficientParserFactory parserFactory;

    public PolynomialDerivativeCalculator(CoefficientParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    public String calculateDerivative(Polynomial polynomial, int order) {
        String expression = polynomial.getExpression();

        for (int i = 1; i <= order; i++) {
            expression = calculateDerivative(expression);
        }

        return expression;
    }

    private String calculateDerivative(String expression) {
        TermIterator iterator = new PolynomialIterator(expression);
        StringBuilder derivative = new StringBuilder();

        while (iterator.hasNext()) {
            String term = iterator.next();
            double coefficient = parseCoefficient(term);
            double exponent = parseExponent(term);

            String derivativeTerm = constructDerivativeTerm(coefficient, exponent);
            derivative.append(derivativeTerm);
            derivative.append(" + ");
        }

        // Remove the trailing "+"
        derivative.setLength(derivative.length() - 3);

        return derivative.toString();
    }

    private double parseCoefficient(String term) {
        String coefficientStr = term;
        if (term.contains("x")) {
            coefficientStr = term.substring(0, term.indexOf("x"));
            coefficientStr = coefficientStr.isEmpty() ? "1" : coefficientStr;
        }
        return parseCoefficientValue(coefficientStr);
    }

    private double parseExponent(String term) {
        double exponent = 0;
        if (term.contains("^")) {
            String exponentStr = term.substring(term.indexOf("^") + 1);
            exponent = Double.parseDouble(exponentStr);
        }
        return exponent;
    }

    private double parseCoefficientValue(String coefficient) {
        CoefficientParser parser = parserFactory.createParser(coefficient);
        return parser.parse(coefficient);
    }

    private String constructDerivativeTerm(double coefficient, double exponent) {
        double newCoefficient = coefficient * exponent;
        double newExponent = exponent - 1;

        if (newExponent == 0) {
            // The term has no x (constant term)
            return String.valueOf(newCoefficient);
        } else {
            // The term has x with a non-zero exponent
            return newCoefficient + "x^" + newExponent;
        }
    }
}

public class Derivative {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the polynomial expression: ");
        String expression = scanner.nextLine();

        // Remove whitespace and convert to lowercase for easier processing
        expression = expression.replaceAll("\\s", "").toLowerCase();

        System.out.print("Enter the order of the derivative: ");
        int order = scanner.nextInt();

        CoefficientParserFactory parserFactory = new DefaultCoefficientParserFactory();
        PolynomialDerivativeCalculator calculator = new PolynomialDerivativeCalculator(parserFactory);

        Polynomial polynomial = new Polynomial(expression);
        String result = calculator.calculateDerivative(polynomial, order);

        System.out.println("The " + order + "-th order derivative of the polynomial is: " + result);

        scanner.close();
    }
}





