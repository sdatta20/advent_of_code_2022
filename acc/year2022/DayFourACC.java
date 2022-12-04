package acc.year2022;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class DayFourACC {
    static int startOfRange1=0;
    static int endOfRange1=0;
    static int startOfRange2=0;
    static int endOfRange2=0;
    public static void main(String[] args) {

        puzzleOneEvaluate();
        puzzleTwoEvaluate();

    }

    static void puzzleOneEvaluate() {
        String line ;
        String[] items;
        File inputFile = new File("InputOfPuzzle/InputDay4-Puzzle1.txt");
        try (
             Scanner sc = new Scanner(inputFile);){
            int countOverlapRanges = 0;
            while (sc.hasNext()) {
                 line = sc.nextLine();
                 items = line.split(",");
                if (compareTwoRanges(items[0],items[1]))
                    countOverlapRanges+=1;
            }
           System.out.println(countOverlapRanges);
        } catch (Exception e) {
            System.out.println("Error while processing the input of Puzzle 1");
        }
    }

    private static boolean compareTwoRanges (String range1, String range2) {

        startOfRange1 = Integer.parseInt(range1.split("-")[0]);
        endOfRange1 = Integer.parseInt(range1.split("-")[1]);

        startOfRange2 = Integer.parseInt(range2.split("-")[0]);
        endOfRange2 = Integer.parseInt(range2.split("-")[1]);
        return  ((startOfRange1 <= startOfRange2 && endOfRange1>= endOfRange2) || (startOfRange2 <=startOfRange1 && endOfRange2>= endOfRange1));
    }

    static void puzzleTwoEvaluate() {
        String line ;
        String[] items;
        File inputFile = new File("InputOfPuzzle/InputDay4-Puzzle1.txt");
        try (
                Scanner sc = new Scanner(inputFile);){
            int countOverlapRanges = 0;
            while (sc.hasNext()) {
                line = sc.nextLine();
                items = line.split(",");
                if (compareTwoRangesForSomethingCommon(items[0],items[1]))
                    countOverlapRanges+=1;
            }
            System.out.println(countOverlapRanges);
        } catch (Exception e) {
            System.out.println("Error while processing the input of Puzzle 1");
        }
    }

    private static boolean compareTwoRangesForSomethingCommon (String range1, String range2) {
        startOfRange1 = Integer.parseInt(range1.split("-")[0]);
        endOfRange1 = Integer.parseInt(range1.split("-")[1]);

        startOfRange2 = Integer.parseInt(range2.split("-")[0]);
        endOfRange2 = Integer.parseInt(range2.split("-")[1]);

        List<Integer> firstListOfNumbers = IntStream.range(startOfRange1, endOfRange1+1)
                .boxed()
                .collect(toList());

        List<Integer> secondListOfNumbers = IntStream.range(startOfRange2, endOfRange2+1)
                .boxed()
                .collect(toList());

        List<Integer> common = firstListOfNumbers.stream().filter(secondListOfNumbers::contains).collect(toList());
        return   !common.isEmpty();

    }
}
