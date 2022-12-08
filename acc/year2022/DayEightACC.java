package acc.year2022;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Character.isDigit;

public class DayEightACC {
    static List<List<Integer>> twoDMapArr;
    static int visibleTreeCount =0;
    static int maxTreeScore =0;
    public static void main(String[] args) {
        twoDMapArr = new ArrayList<>();
        genericProcessing();
        puzzleOneSolution();
        System.out.println("totalNoOfTreesVisible" + visibleTreeCount);
        puzzleTwoSolution();
        System.out.println("MaxTreeScorePossible" + maxTreeScore);
    }

    static void genericProcessing() {
        String line ;

        File inputFile = new File("InputOfPuzzle/InputDay8.txt");
        try (
                Scanner sc = new Scanner(inputFile);){
            while (sc.hasNext()) {
                line = sc.nextLine();
                processRow(line.split(""));
            }


        } catch (Exception e) {
            System.out.println("Error while processing the input of Puzzle 1");
        }
    }

    private static void processRow(String[] line) {
        List<Integer> rowValues = Arrays.asList(line).stream()
                .map(Integer:: parseInt)
                .collect(Collectors.toList());
        twoDMapArr.add(rowValues);
    }

    private static void puzzleOneSolution() {
        int rowCounter = 0;
        int colCounter = 0;
        for (List<Integer> row : twoDMapArr) {
            colCounter = 0;
            for (int cell: row) {
                if (checkSurroundingHeight(cell,colCounter,rowCounter))
                    visibleTreeCount++;
                colCounter++;
            }
            rowCounter++;
        }
    }

    private static void puzzleTwoSolution() {
        int rowCounter = 0;
        int colCounter = 0;
        for (List<Integer> row : twoDMapArr) {
            colCounter = 0;
            for (int cell: row) {
                if (getTreeScore(cell,colCounter,rowCounter)>maxTreeScore)
                    maxTreeScore = getTreeScore(cell,colCounter,rowCounter);
                colCounter++;
            }
            rowCounter++;
        }
    }

    private static int getTreeScore(int cellValue, int columnNo, int rowNo) {
        // check if first row or first column or check if last row and last column
        if (columnNo== 0
                || rowNo ==0
                || columnNo == twoDMapArr.get(0).size()-1
                || rowNo == twoDMapArr.size()-1)
            return 0;
        int rowCounter = 0;
        int columnCounter = 0;
        int topCheck = 0;
        int bottomCheck= 0;
        int leftCheck= 0;
        int rightCheck= 0;
        //top Check
        rowCounter = rowNo-1;
        while (rowCounter>=0) {
            topCheck++;
            if (cellValue <= twoDMapArr.get(rowCounter).get(columnNo)) {
                break;
            }
            rowCounter--;

        }

        //bottom Check
        rowCounter = rowNo+1;
        while (rowCounter< twoDMapArr.size()) {
            bottomCheck++;
            if (cellValue <= twoDMapArr.get(rowCounter).get(columnNo)) {
                break;
            }
            rowCounter++;


        }
        columnCounter = columnNo-1;
        //left Check
        while (columnCounter>=0) {
            leftCheck++;
            if (cellValue <= twoDMapArr.get(rowNo).get(columnCounter)) {
                break;
            }

            columnCounter--;
        }

        //right Check
        columnCounter = columnNo+1;
        while (columnCounter< twoDMapArr.get(0).size()) {
            rightCheck++;
            if (cellValue <= twoDMapArr.get(rowNo).get(columnCounter)) {
                break;
            }
            columnCounter++;

        }

        return  leftCheck*topCheck*rightCheck*bottomCheck;
    }

    private static boolean checkSurroundingHeight(int cellValue, int columnNo, int rowNo) {
        // check if first row or first column or check if last row and last column
        if (!ifFirstRowOrFirstColumn(columnNo,rowNo))
            return  false;
        int rowCounter = 0;
        int columnCounter = 0;
        boolean topCheck = false;
        boolean bottomCheck= false;
        boolean leftCheck= false;
        boolean rightCheck= false;
        //top Check
        while (rowCounter<rowNo) {
            if (cellValue <= twoDMapArr.get(rowCounter).get(columnNo)) {
                topCheck = true;
                break;
            }
                rowCounter++;
        }


        //bottom Check
        rowCounter = rowNo+1;
        while (rowCounter< twoDMapArr.size()) {
            if (cellValue <= twoDMapArr.get(rowCounter).get(columnNo)){
                bottomCheck = true;
                break;
            }
                rowCounter++;
        }


        //left Check
        while (columnCounter<columnNo) {
            if (cellValue <= twoDMapArr.get(rowNo).get(columnCounter)) {
                leftCheck = true;
                break;
            }
                columnCounter++;
        }


        //bottom Check
        columnCounter = columnNo+1;
        while (columnCounter< twoDMapArr.get(0).size()) {
            if (cellValue <= twoDMapArr.get(rowNo).get(columnCounter)) {
                rightCheck = true;
                break;
            }
                columnCounter++;
        }
        return (leftCheck && rightCheck && topCheck && bottomCheck);


    }
    private static  boolean ifFirstRowOrFirstColumn (int columnNo, int rowNo) {
       return !(columnNo+ rowNo== 0
                || columnNo == twoDMapArr.get(0).size()-1
                || rowNo == twoDMapArr.size()-1);

    }

}