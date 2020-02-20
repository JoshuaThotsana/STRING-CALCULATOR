import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class StringCalculator {
    String string = "";
    int[] matchedList = {};
    int[] negatives = {};

    public Matcher getText(String regex, String string) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(string);
    }
    public void setMatchedList(Matcher match) {
        while (match.find()) {
            int matched = Integer.parseInt(match.group());
            if (matched < 1000 && matched >= 0) {
                matchedList = Arrays.copyOf(matchedList,matchedList.length+1);
                matchedList[matchedList.length-1] = matched;
            }
            else {
                negatives = Arrays.copyOf(negatives,negatives.length+1);
                negatives[negatives.length-1] = matched;
            }
        }
    }
    public  int add(String string) {
        this.string = string;
        Matcher matchMultiple = getText("(?<=\\[).+?(?=\\])",string);
        while (matchMultiple.find()) { this.string = this.string.replace(matchMultiple.group(),",");}
        Matcher matcherSingle = getText("(?<=//).+?(?=\\n)",this.string);
        while (matcherSingle.find()) { this.string = this.string.replace(matcherSingle.group(),",");}
        this.string = this.string.replace("//,\n","");
        this.string = this.string.replace("\n",",");
        try {
            if (matchedList.length > 1) {
                if (!getText("\\d(?<=\\d+).+\\d",this.string).matches()) { throw new Exception();}
            }
            if (!string.startsWith("//") && string.indexOf("//") > 0) { throw new Exception();}
        } catch (Exception e1) {
            System.out.println("ERROR: invalid input");
            System.exit(0);
        }
        setMatchedList(getText("-\\d+|\\d+",this.string));
        try {
            if(negatives.length > 0) { throw new Exception();}
        } catch (Exception e2) {
            System.out.println("ERROR: negatives not allowed " + Arrays.toString(negatives).replace("[","").replace("]",""));
            System.exit(0);
        }
        return IntStream.of(matchedList).sum();
    }
}
