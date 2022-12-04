package acc2022;

import java.io.File;
import java.util.Scanner;



public class DayTwoACC {

    private static final int WIN = 6;
    private static final int DRAW = 3;
    private static final int LOSE = 0;

    private static final int SCORE_WITH_ROCK = 1;
    private static final int SCORE_WITH_PAPER = 2;
    private static final int SCORE_WITH_SCISSOR = 3;

    public static void main(String[] args) {
        puzzleTwoEvaluate();
    }

    static void puzzleOneEvaluate() {
        Scanner sc = null;
        try {
            File inputFile = new File("InputOfPuzzle/InputDay2-Puzzle1.txt");
            sc = new Scanner(inputFile);
            int currentTotalScore = 0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                currentTotalScore += calculateScore(line.substring(0,1), line.substring(2), line);
            }
            System.out.println(currentTotalScore);
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
            File inputFile = new File("InputOfPuzzle/InputDay2-Puzzle2.txt");
            sc = new Scanner(inputFile);
            int currentTotalScore = 0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                currentTotalScore += calculateScoreForPuzzleTwo(line.substring(0,1), line.substring(2), line);
            }
            System.out.println(currentTotalScore);
        } catch (Exception e) {
            System.out.println("Error while processing the input of Puzzle 1");
        }
        finally {
            sc.close();
        }
    }

    private static int calculateScoreForPuzzleTwo(String PlayerOne, String PlayerTwo, String line) {

        if (PlayerOne.equals("A")) {
            if (PlayerTwo.equals("X"))
                return LOSE + SCORE_WITH_SCISSOR;
            else if (PlayerTwo.equals("Y"))
                return DRAW + SCORE_WITH_ROCK;
            else if (PlayerTwo.equals("Z"))
                return WIN + SCORE_WITH_PAPER;
        }
        else if (PlayerOne.equals("B")) {
            if (PlayerTwo.equals("X"))
                return LOSE + SCORE_WITH_ROCK;
            else if (PlayerTwo.equals("Y"))
                return DRAW + SCORE_WITH_PAPER;
            else if (PlayerTwo.equals("Z"))
                return WIN + SCORE_WITH_SCISSOR;
        }
        else if (PlayerOne.equals("C")) {
            if (PlayerTwo.equals("X"))
                return LOSE + SCORE_WITH_PAPER;
            else if (PlayerTwo.equals("Y"))
                return DRAW + SCORE_WITH_SCISSOR;
            else if (PlayerTwo.equals("Z"))
                return WIN + SCORE_WITH_ROCK;
        }
        return 0;

    }


    private static int calculateScore(String PlayerOne, String PlayerTwo, String line) {
        int matchScore =0;
        int participationScore = 0;
        if ((line.equals("A Y")) ||
                (line.equals("B Z")) ||
                (line.equals("C X"))
        )
            matchScore = WIN;
        else if ((line.equals("A X")) ||
                (line.equals("B Y")) ||
                (line.equals("C Z"))
        )
            matchScore = DRAW;


        if (PlayerTwo.equals("X"))
            participationScore = SCORE_WITH_ROCK;
        else if (PlayerTwo.equals("Y"))
            participationScore = SCORE_WITH_PAPER;
        else if (PlayerTwo.equals("Z"))
            participationScore = SCORE_WITH_SCISSOR;

        return matchScore + participationScore;

    }


}
