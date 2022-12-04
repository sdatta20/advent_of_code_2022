package acc2022;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class DayThreeACC {

    static Map<Character, Integer> characterIntegerMap;

    public static void main(String[] args) {

        stringToNumber();
        puzzleOneEvaluate();
        puzzleTwoEvaluate();

    }

    static void puzzleOneEvaluate() {
        Scanner sc = null;
        try {
            File inputFile = new File("InputOfPuzzle/InputDay3-Puzzle1.txt");
            sc = new Scanner(inputFile);
            String firstCompartment, secondCompartment;
            int currentTotalScore = 0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                firstCompartment = line.substring(0,line.length()/2);
                secondCompartment = line.substring(line.length()/2);
                currentTotalScore +=getCommonChars(firstCompartment,secondCompartment);
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
            File inputFile = new File("InputOfPuzzle/InputDay3-Puzzle2.txt");
            sc = new Scanner(inputFile);
            List<String> groupOfLines = new ArrayList<>();
            int lineCounterOf3 =0;
            int currentTotalScore = 0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                groupOfLines.add(line);
                lineCounterOf3++;
                if (lineCounterOf3 == 3) {
                    currentTotalScore += compareGroupOfLines(groupOfLines);
                    groupOfLines.clear();
                    lineCounterOf3 = 0;
                }
            }
            System.out.println(currentTotalScore);
        } catch (Exception e) {
            System.out.println("Error while processing the input of Puzzle 1");
        }
        finally {
            sc.close();
        }
    }

    private static  int compareGroupOfLines(List<String> groupOfLines) {
        List<String> firstList = new ArrayList<String>(Arrays.asList(groupOfLines.get(0).split("")));
        List<String> secondList = new ArrayList<String>(Arrays.asList(groupOfLines.get(1).split("")));
        List<String> thirdList = new ArrayList<String>(Arrays.asList(groupOfLines.get(2).split("")));
        List<String> lettersCommonBetweenFirstTwo = firstList.stream()
                .filter( str -> secondList.contains(str))
                .collect(Collectors.toList());
        List<String> lettersCommon = lettersCommonBetweenFirstTwo.stream()
                .filter( str -> thirdList.contains(str))
                .collect(Collectors.toList());
        return characterIntegerMap.get(lettersCommon.get(0).toCharArray()[0]);
    }

    private static int getCommonChars(String firstCompartment, String secondCompartment) {
        try {
            List<String> firstList = new ArrayList<String>(Arrays.asList(firstCompartment.split("")));
            List<String> lettersCommon = firstList.stream()
                            .filter( str -> secondCompartment.contains(str))
                                    .collect(Collectors.toList());
            return characterIntegerMap.get(lettersCommon.get(0).toCharArray()[0]);
        } catch (Exception e) {
            System.out.println("Error while processing the input of Puzzle 1");
        }
        return 0;
    }

    static void stringToNumber() {


        characterIntegerMap = new HashMap<>();
        char ch;
        int value=0;
        for(ch = 'a'; ch <= 'z'; ++ch){
            value++;
            characterIntegerMap.put(ch, value);
            System.out.println(ch+""+value);
        }
        for(ch = 'A'; ch <= 'Z'; ++ch){
            value++;
            characterIntegerMap.put(ch, value);
            System.out.println(ch+""+value);
        }
    }


}
