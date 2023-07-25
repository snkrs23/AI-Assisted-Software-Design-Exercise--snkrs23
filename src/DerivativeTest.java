import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DerivativeTest {

    @Test
    void testPolynomialDerivative() {
        // Test case 1: Simple linear polynomial
        String result1 = Derivative.calculateDerivative("2x + 3", 1);
        assertEquals("2.0", result1);

        // Test case 2: Quadratic polynomial
        String result2 = Derivative.calculateDerivative("3x^2 + 2x + 1", 1);
        assertEquals("6.0x + 2.0", result2);

        // Test case 3: Higher-order polynomial
        String result3 = Derivative.calculateDerivative("5x^3 + 4x^2 + 3x + 2", 2);
        assertEquals("30.0x + 8.0", result3);

        // Test case 4: Polynomial with fractional coefficients
        String result4 = Derivative.calculateDerivative("1/2x^2 + 3/4x + 1/3", 1);
        assertEquals("1.0x + 0.75", result4);

        // Test case 5: Zero order derivative (original expression should be returned)
        String result5 = Derivative.calculateDerivative("4x^3 + 2x^2 + x + 3", 0);
        assertEquals("4x^3 + 2x^2 + x + 3", result5);
    }
}