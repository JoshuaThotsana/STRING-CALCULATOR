import java.util.regex.*;

public class Calculator {

    static String numberPatterns = "-\\d+|\\d+";

    public static void main(String[] args) throws Exception {

        System.out.println(add("-1,-2,-3,8,8,898,8,5-566,-455,Joshua--22243,4"));

    }

    static int add(String string) throws Exception {

        if (string.length() >=2 && string.substring(0, 2).equals("//")) {

            Pattern delim = Pattern.compile("\\[.*?\\]");
            Matcher matcherDelim = delim.matcher(string);

            while (matcherDelim.find()) {
                int closeBraket = matcherDelim.group().indexOf("]");
                string = string.replace(matcherDelim.group().substring(1,closeBraket),",");
            }
            string =  string.replace(string.substring(2,string.indexOf("\n")),",");
        }
        Pattern pattern = Pattern.compile(numberPatterns);
        Matcher matcher = pattern.matcher(string);
        StringBuilder negative = new StringBuilder("ERROR: negatives not allowed");

        int n = 0;
        while (matcher.find()) {
            int m = Integer.parseInt(matcher.group());
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
        return n;
    }
}
