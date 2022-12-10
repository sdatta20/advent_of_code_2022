package acc.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayTen {
    static List<String> inputLines;
    static List<Integer> cyclesCheck ;
    static List<Integer> solutionOneResults;
    static int positionOfCheck;
    static int valueOfX;
    static List<String> crt ;
    public static void main(String[] args) {
        cyclesCheck = new ArrayList<>();
        cyclesCheck.add(20);
        cyclesCheck.add(60);
        cyclesCheck.add(100);
        cyclesCheck.add(140);
        cyclesCheck.add(180);
        cyclesCheck.add(220);
        positionOfCheck =0;
        solutionOneResults = new ArrayList<>();
        crt = new ArrayList<>();
        getInputLines();
        valueOfX = 1;
        processEachLine();
        System.out.println("processing done");
        printCrt(crt);
    }

    private static void printCrt(List<String> crt) {
        for (int i = 0; i < 6; i++) {
            for (int j = i*40; j < i*40+40 && j < crt.size(); j++) {
                System.out.print(crt.get(j));
            }
            System.out.println();
        }
    }

    private static void getInputLines()
    {
        String input = null;
        String[] lines = null;
        try {
            input = Files.readString(Paths.get("InputOfPuzzle/InputDay10.txt"));
            lines = input.split("\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        inputLines =  Arrays.asList(lines);
    }

    private static void processEachLine()
    {
        int rowCounter =0;
        int lineCounter =0;
        while  (rowCounter<inputLines.size()) {
            if (inputLines.get(rowCounter).substring(0,4).equals("addx")) {
                    crt.add(drawCrt(lineCounter, valueOfX));
                    crt.add(drawCrt(lineCounter+1, valueOfX));
                    valueOfX += Integer.parseInt(inputLines.get(rowCounter).split(" ")[1]);
                    lineCounter+=2;
            }
            else {
                    crt.add(drawCrt(lineCounter, valueOfX));
                    lineCounter+=1;
            }
            rowCounter++;
            if (            positionOfCheck!=6 &&
                    (lineCounter+2 == cyclesCheck.get(positionOfCheck) || lineCounter+1 == cyclesCheck.get(positionOfCheck) )
                ) {
                solutionOneResults.add(valueOfX*cyclesCheck.get(positionOfCheck));
                positionOfCheck++;
            }


        }

    }

    private static String drawCrt(int cyclePosition, int x) {
        cyclePosition = cyclePosition % 40;
        if (x == cyclePosition || x - 1 == cyclePosition || x + 1 == cyclePosition)
            return "#";
        else {
            return ".";
        }
    }


}
