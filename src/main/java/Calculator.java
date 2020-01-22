import java.util.regex.*;

public class Calculator {

    static String numberPatterns = "-\\d+|\\d+";

    public static void main(String[] args) throws Exception {

        System.out.println(add("//***\n1***2***3"));

    }

    static int add(String string) throws Exception {

        if (string.length() >=2 && string.substring(0, 2).equals("//")) {

            Pattern delimiter = Pattern.compile("\\[.*?\\]");
            Matcher matcherDelimiter = delimiter.matcher(string);

            while (matcherDelimiter.find()) {
                int closeBraket = matcherDelimiter.group().indexOf("]");
                string = string.replace(matcherDelimiter.group().substring(1,closeBraket),",");
            }

            string =  string.replace(string.substring(2,string.indexOf("\n")),",");
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
