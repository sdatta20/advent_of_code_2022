package acc.year2022;

import java.io.File;
import java.util.Scanner;

public class DayNineACC {
    static  int lineCounter=0;
    static int[][] twoDMapArr;
    static int currentHeadRowPos =0;
    static  int currentHeadCollPos =0;
    static int currentTailRowPos =0;
    static int currentTailColPos =0;
    static int maxValue =0;
    public static void main(String[] args) {
        twoDMapArr = new int[1000][1000];
        currentHeadRowPos = 500;
        currentTailRowPos = 500;
        currentHeadCollPos = 500;
        currentTailColPos = 500;
        genericProcessing();
        printTable();
        System.out.println("total positions by tail "+maxValue);
    }

    static void  printTable() {
        int rowCounter =0;
        int colCounter =0;
        while (rowCounter<1000) {
            colCounter =0;

            while (colCounter<1000) {
                if (twoDMapArr[rowCounter][colCounter]==1)
                    maxValue++;
//                System.out.print(twoDMapArr[rowCounter][colCounter]+",");
                colCounter++;
            }
//            System.out.println();
            rowCounter++;
        }
    }
    static void genericProcessing() {
        String line ;

        File inputFile = new File("InputOfPuzzle/InputDay9.txt");
        try (
                Scanner sc = new Scanner(inputFile);){
            while (sc.hasNext()) {
                lineCounter++;
                line = sc.nextLine();
                processRow(line.split(" ")[0],Integer.parseInt(line.split(" ")[1]));
            }
        } catch (Exception e) {
            System.out.println("Error while processing the input of Puzzle 1");
        }
    }

    private static void processRow(String directionOfMove, int totalSteps) {
        switch (directionOfMove) {
            case "R":
                headPositionChange(false,true,totalSteps);
                break;
            case "L":
                headPositionChange(false,false,totalSteps);
                break;
            case "U":
                headPositionChange(true,false,totalSteps);
                break;
            case "D":
                headPositionChange(true,true,totalSteps);
                break;
        }
    }



    private static  void headPositionChange (boolean isRowChange, boolean isAddition,int totalSteps) {
        int stepCounter =1;
        while (stepCounter<=totalSteps) {
            if (!isRowChange)
            {
                if (isAddition) {
                    currentHeadCollPos++;
                }
                else {
                    currentHeadCollPos--;
                }
            }
            else {
                if (isAddition) {
                    currentHeadRowPos++;
                }
                else {
                    currentHeadRowPos--;
                }
            }
            tailPositionEvaluation(isRowChange);
            stepCounter++;
        }
    }

    private static  void tailPositionEvaluation (boolean isRowChange) {
        if (currentHeadRowPos == currentTailRowPos && currentHeadCollPos == currentTailColPos ) {
                return; // no change in tail position
        }

        if (isRowChange) {
            if ( currentHeadRowPos - currentTailRowPos >=2 ) {
                currentTailColPos = currentHeadCollPos;
                currentTailRowPos += 1;
            }
            else if ( currentTailRowPos - currentHeadRowPos >=2 ) {
                currentTailColPos = currentHeadCollPos;
                currentTailRowPos -= 1;
            }
        }
        else {
            if ( currentHeadCollPos - currentTailColPos >=2 ) {
                currentTailRowPos = currentHeadRowPos;
                currentTailColPos += 1;
            }
            else if ( currentTailColPos - currentHeadCollPos >=2 ) {
                currentTailRowPos = currentHeadRowPos;
                currentTailColPos -= 1;
            }
        }



        if (currentTailColPos <0)
            currentTailColPos =0;
        if (currentTailRowPos <0)
            currentTailRowPos =0;

        if (twoDMapArr[currentTailRowPos][currentTailColPos] == 0)
            twoDMapArr[currentTailRowPos][currentTailColPos] = 1;
    }
}
