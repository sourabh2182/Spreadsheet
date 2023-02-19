public class SpreadSheetExecutor {

    public static void main(String[] args) throws Exception {

        SpreadSheet spreadSheet = new SpreadSheet();

        // set some dummy values
        spreadSheet.setCellValue("A1", "10");
        spreadSheet.setCellValue("A2", "12");

        // get the values
        int cellA1Value = spreadSheet.getCellValue("A1");
        int cellA2Value = spreadSheet.getCellValue("A2");
        System.out.println("Cell Value A1 = "+cellA1Value);
        System.out.println("Cell Value A2 = "+cellA2Value);

        //set cell value 'A3' by adding 'A1' and 'A2'
        System.out.println("set cell value 'A3' by adding 'A1' and 'A2' using SUM(A1:A2)");
        spreadSheet.setCellValue("A3", "SUM(A1:A2)");

        int cellA3Value = spreadSheet.getCellValue("A3");
        System.out.println("Cell Value A3 = "+cellA3Value);

        //set cell value 'A4' by adding 'A2' and 'A3'
        System.out.println("set cell value 'A4' by adding 'A1', 'A2' and 'A3' using = A1+A2+A3");
        spreadSheet.setCellValue("A4", "=A1+A2+A3");

        int cellA4Value = spreadSheet.getCellValue("A4");
        System.out.println("Cell Value A4 = "+cellA4Value);
    }
}
