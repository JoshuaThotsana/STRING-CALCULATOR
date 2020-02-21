import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


/** An add function that can handle multiple integers passed as a string.  */
public class StringCalculator {

    /** Instance variables. */
    String string = "";
    int[] matchedList = {};
    int[] negatives = {};

    /**
     * A function that takes two strings as parameter and returns the matched Regular expression.
     * @param regex the Regular expression parameter.
     * @param string the String parameter.
     * @return the matched pattern.
     */
    public Matcher getText(String regex, String string) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(string);
    }

    /**
     * This function takes the integers matched and appends them to a matchedList instance variable.
     * @param match the matched pattern.
     */
    public void setMatchedList(Matcher match) {

    while (match.find()) {  // A while loop to append matched integers between 0 and 999 inclusive.

            int matched = Integer.parseInt(match.group());  // Convert the matched Integer object to an int primitive type data.
            if (matched < 1000 && matched >= 0) {
                matchedList = Arrays.copyOf(matchedList,matchedList.length+1);
                matchedList[matchedList.length-1] = matched;
            }
            if (matched < 0) {  // For integers that are negative, append to the negatives instance variable.
                negatives = Arrays.copyOf(negatives,negatives.length+1);
                negatives[negatives.length-1] = matched;
            }
        }
    }

    /**
     * This function returns the sum of the matched integers provided that the string input is in the required format.
     * @param string the string input.
     * @return the sum of the integers.
     */
    public  int add(String string) {

        this.string = string;   // Set the global string variable declared with the string variable pass as a parameter.

        Matcher matchMultiple = getText("(?<=\\[).+?(?=\\])",string);   // This is to find all delimiters of any length.

        while (matchMultiple.find()) { // Replace all delimiters found with a comma.
            this.string = this.string.replace(matchMultiple.group(),",");
        }

        Matcher matcherSingle = getText("(?<=//).+?(?=\\n)",this.string);   // Match a default delimiter of this kind, "//...\n"

        while (matcherSingle.find()) {  // Replace delimiter "//...\n" with a comma."
            this.string = this.string.replace(matcherSingle.group(),",");
        }

        this.string = this.string.replace("//,\n","");  // Replace "//,\n" with an empty string.
        this.string = this.string.replace("\n",",");    // Replace \n with a comma in a case where there are no delimiters.

        setMatchedList(getText("-\\d+|\\d+",this.string));  // Append matched integers to matchedList instance variable.

        try {   // Throw and catch an Exception if negative integers are found.
            if(negatives.length > 0) { throw new Exception();}
        } catch (Exception e2) {
            System.out.println("ERROR: negatives not allowed " + Arrays.toString(negatives).replace("[","").replace("]",""));
            System.exit(0);
        }
        try {   // Throw and catch an Exception if the input string is not in a required format.
            if (matchedList.length > 0) {
                if (!getText("\\d+.+\\d+$|\\d+$",this.string).matches()) { throw new Exception();}
            }if (!string.startsWith("//") && string.indexOf("//") > 0) { throw new Exception();}
        } catch (Exception e1) {
            System.out.println("ERROR: invalid input");
            System.exit(0);
        }

        return IntStream.of(matchedList).sum();
    }
}
