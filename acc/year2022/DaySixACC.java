package acc.year2022;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DaySixACC {

    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("StartTime: "+dtf.format(now));
        puzzleOneEvaluate(3);
        now = LocalDateTime.now();
        System.out.println("Completion Time of Puzzle 1: "+dtf.format(now));
        puzzleOneEvaluate(13);
        now = LocalDateTime.now();
        System.out.println("Completion Time of Puzzle 2: "+dtf.format(now));


    }

    static void puzzleOneEvaluate(int uniqueCharCount) {
        String line ;
        File inputFile = new File("InputOfPuzzle/InputDay6.txt");
        try (
                Scanner sc = new Scanner(inputFile);){
            int countOverlapRanges = 0;
            while (sc.hasNext()) {
                line = sc.nextLine();
                System.out.println(checkUniqueFirstFourChars(line,uniqueCharCount));
            }
            System.out.println(countOverlapRanges);
        } catch (Exception e) {
            System.out.println("Error while processing the input of Puzzle 1");
        }
    }

    private static int checkUniqueFirstFourChars (String line, int uniqueCharCount) {
        char[] chars = line.toCharArray();
        int counter = uniqueCharCount;
        while(counter<chars.length) {
            if (isUniqueLettersCombination(line.substring(counter-uniqueCharCount,counter+1))) {
                return counter+1;
            }
            counter++;
        }
        return 0;
    }

    private static boolean isUniqueLettersCombination(String input) {
        Set<Character> set = new HashSet<>();
        char[] characters = input.toCharArray();
        for (Character c : characters) {
            if (!set.add(c)) {
                return false;
            }
        }
        return true;
    }


}
