import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    void add() {

        StringCalculator calculator = new StringCalculator();


        // Test cases that return a sum.
        String input1 = "";                                     // Should return 0
        String input2 = "1";                                    // Should return 1
        String input3 = "1,1";                                  // Should return 2
        String input4 = "1,2,3,4";                              // Should return 10
        String input5 = "1\n2,3";                               // Should return 6
        String input6 = "//;\n1;2";                             // Should return 3
        String input7 = "//4\n142";                             // Should return 3
        String input8 = "//;\n1000,1;2";                        // Should return 3
        String input9 = "//***\n1***2***3";                     // Should return 6
        String input10 = "//[:D][%]\n1:D2%3";                   // Should return 6
        String input11 = "//[***][%%%]\n1***2%%%3";             // Should return 6
        String input12 = "//[(-_-')][%]\n1(-_-')2%3";           // Should return 6
        String input13 = "//[abc][777][:(]\\n1abc27773:(1";     // Should return 7

        assertEquals(0,calculator.add(input1));
        assertEquals(1,calculator.add(input2));
        assertEquals(2,calculator.add(input3));
//        assertEquals(10,calculator.add(input4));
//        assertEquals(6,calculator.add(input5));
//        assertEquals(3,calculator.add(input6));
//        assertEquals(3,calculator.add(input7));
//        assertEquals(3,calculator.add(input8));
//        assertEquals(6,calculator.add(input9));
//        assertEquals(6,calculator.add(input10));
//        assertEquals(6,calculator.add(input11));
//        assertEquals(6,calculator.add(input12));
//        assertEquals(7,calculator.add(input13));

        // Test cases that throw and catch the Exception.
        String input14 = "//;\n1000;1;2;";
        String input15 = "   //;\n1000,1;2";
        String input16 = "1,2,3//;\n1000,1;2";
        String input17 = "-1,-2,3,4";

    }
}
