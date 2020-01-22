import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add() throws Exception {

        String input1 = "1,2,3,4";

        assertEquals(10,Calculator.add(input1));

        String input2 = "//;\n1000;1;2;";

        Assertions.assertThrows(Exception.class, () -> {
            Calculator.add(input2);
        });

    }
}