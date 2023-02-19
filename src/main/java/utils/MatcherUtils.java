package utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherUtils {


    // matches cell references like A1, A2, A3
    private static Pattern cellPattern = Pattern.compile("([A-Z]\\d+):?");

    // Patterns to match SUM or = operations of Spreadsheet
    private static Matcher sumMatcher1 = Pattern.compile("\\bSUM\\((?:\\w+\\d*:?)+\\)",
            Pattern.CASE_INSENSITIVE).matcher("");
    private static Matcher sumMatcher2 = Pattern.compile("=[A-Z]\\d+(?:\\+[A-Z]\\d+)+",
            Pattern.CASE_INSENSITIVE).matcher("");
    private static Matcher sumMatcher3 = Pattern.compile("SUM\\(([A-Z]+[0-9]+:[A-Z]+[0-9]+)\\)",
            Pattern.CASE_INSENSITIVE).matcher("");

    public static boolean matchesAtLeastOneRegex(String input) {
        return sumMatcher1.reset(input).matches()
                || sumMatcher2.reset(input).matches();
        // || sumMatcher3.reset(input).matches();
    }

    public static ArrayList<String> getCells(String inputFormula) {
        ArrayList<String> cellsList = new ArrayList<>();
        if (sumMatcher1.reset(inputFormula).matches()
                || sumMatcher2.reset(inputFormula).matches()) {
            Matcher matcher = cellPattern.matcher(inputFormula);
            while (matcher.find()) {
                String cellReference = matcher.group(1);
                cellsList.add(cellReference);
            }
        }
        return cellsList;
    }
}
