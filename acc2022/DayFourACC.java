package acc2022;

import java.io.File;
import java.util.Scanner;

public class DayFourACC {

    public static void main(String[] args) {

        puzzleOneEvaluate();
        puzzleTwoEvaluate();

    }

    static void puzzleOneEvaluate() {
        Scanner sc = null;
        try {
            File inputFile = new File("InputOfPuzzle/InputDay4-Puzzle1.txt");
            sc = new Scanner(inputFile);
//            System.out.println(currentTotalScore);
        } catch (Exception e) {
            System.out.println("Error while processing the input of Puzzle 1");
        }
        finally {
            sc.close();
        }
    }

    static void puzzleTwoEvaluate() {
        Scanner sc = null;
        try {
            File inputFile = new File("InputOfPuzzle/InputDay4-Puzzle2.txt");
            sc = new Scanner(inputFile);
//            System.out.println(currentTotalScore);
        } catch (Exception e) {
            System.out.println("Error while processing the input of Puzzle 1");
        }
        finally {
            sc.close();
        }
    }
}
