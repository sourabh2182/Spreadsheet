import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static utils.MatcherUtils.*;
public class SpreadSheet {

    private HashMap<String, Integer> cellValues;

    public SpreadSheet() {
        cellValues = new HashMap<>();
    }

    public void setCellValue(String cellId, String formula) throws Exception {
        if (!isValidCellId(cellId)) {
            throw new Exception("Invalid cell ID.");
        }
        if (isNumeric(formula)) {
            cellValues.put(cellId, Integer.parseInt(formula));
        } else {
            cellValues.put(cellId, evaluateFormula(formula));
        }
    }

    private boolean isValidCellId(String cellId) {
        Pattern pattern = Pattern.compile("[A-Z]+[0-9]+");
        Matcher matcher = pattern.matcher(cellId);
        return matcher.matches();
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int value = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public int getCellValue(String cellId) throws Exception {
        if (!isValidCellId(cellId)) {
            throw new Exception("Invalid cell ID.");
        }
        int value = cellValues.getOrDefault(cellId, 0);
        return value;
    }

    private int evaluateFormula(String formula) throws Exception {
        int []sum = {0};
        if(matchesAtLeastOneRegex(formula))
        {
            ArrayList<String> cells = getCells(formula);
            cells.forEach(cell ->{
                try {
                    sum[0] += getCellValue(cell);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            throw new Exception("Invalid formula.");
        }
        return sum[0];
    }

}
