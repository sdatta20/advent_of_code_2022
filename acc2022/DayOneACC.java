package acc2022;

import java.util.*;
import java.io.*;

public class DayOneACC {
    public static void main(String[] args) {
        puzzleTwoEvaluate();
    }

    static void puzzleOneEvaluate() {
        Scanner sc = null;
        try {
            File inputFile = new File("InputOfPuzzle/InputDay1-Puzzle1.txt");
            sc = new Scanner(inputFile);
            int highestValue = 0, currentValue = 0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.isBlank()) {
                    highestValue = compareNumbers(highestValue, currentValue);
                    currentValue = 0;
                    continue;
                }
                currentValue += Integer.parseInt(line);
            }
            System.out.println(highestValue);
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
            File inputFile = new File("InputOfPuzzle/InputDay1-Puzzle2.txt");
            sc = new Scanner(inputFile);
            int currentValue = 0;
            List<Integer> calorieList = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.isBlank()) {
                    calorieList.add(currentValue);
                    currentValue = 0;
                    continue;
                }
                currentValue += Integer.parseInt(line);
            }
            Collections.sort(calorieList, Collections.reverseOrder());

            System.out.println(calorieList.get(0) + calorieList.get(1) + calorieList.get(2));
        } catch (Exception e) {
            System.out.println("Error while processing the input of Puzzle 2");
        }
        finally {
            sc.close();
        }
    }

    static int compareNumbers(int newValue, int oldValue) {
        if (newValue>oldValue)
            return newValue;
        else
            return oldValue;
    }
}
