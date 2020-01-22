import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    String pattern = "-\\d+|\\d+";

    @Test
    void add() throws Exception {

        String input = "";

        assertEquals(0,Calculator.add(input));

    }
}