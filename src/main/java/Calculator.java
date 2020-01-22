import java.util.regex.*;

/**
 * A simple method that takes a string as input and returns the sum of the numbers entered in the string.
 * @author Joshua Thotsana Mabotsa.
 */

public class Calculator {

    // initialized instance variable
    static String numberPatterns = "-\\d+|\\d+";

    /**
     * Main method signature
     * @param args String argument.
     * @throws Exception invokes a static method that throws exception.
     */
    public static void main(String[] args) throws Exception {

        System.out.println(add(""));

    }

    /**
     * A method that takes a string as a parameter and returns the sum of the digits in the string.
     * @param string            Takes a string as a parameter.
     * @return                  Returns the total sum of the numbers in the string.
     * @throws Exception        Throws exception if the string is not in the required format.
     */
    static int add(String string) throws Exception {

        if (string.length() >=2 && string.substring(0, 2).equals("//")) {   // check if a string has delimiters.

            Pattern delimiter = Pattern.compile("\\[.*?\\]");
            Matcher matcherDelimiter = delimiter.matcher(string);

            while (matcherDelimiter.find()) {             // find all delimiters and replace them with commas.

                int closeBraket = matcherDelimiter.group().indexOf("]");
                string = string.replace(matcherDelimiter.group().substring(1,closeBraket),",");

            }

            string =  string.replace(string.substring(2,string.indexOf("\n")),","); // replace //[delimiter]...[delimiter]\n..., with //,\n
            string = string.replace("\n","");
        }
        else {
            string = string.replace("\n",",");
        }

        string = string.replace("//,","");

        Pattern pattern = Pattern.compile(numberPatterns);
        Matcher matcher = pattern.matcher(string);
        StringBuilder negative = new StringBuilder("ERROR: negatives not allowed");
        StringBuilder format = new StringBuilder();

        int n = 0;
        while (matcher.find()) {
            int m = Integer.parseInt(matcher.group());

            format.append(m).append(",");

            if (m <0) {
                negative.append(" ").append(m).append(",");
            }
            else {
                if (m >= 1000) {
                    m = 0;
                }
                    n += m;
            }
        }
        if (!negative.toString().equals("ERROR: negatives not allowed")) {
            throw new Exception(negative.toString().substring(0,negative.toString().length()-1));
        }

        if (string.length() != 0) {
            if (!string.trim().equals(format.toString().substring(0,format.toString().length()-1))){
                throw new Exception("ERROR: invalid input");
            }
        }
        return n;
    }
}
