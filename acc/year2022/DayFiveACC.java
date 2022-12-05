package acc.year2022;

import java.io.File;
import java.util.*;

public class DayFiveACC {
    static String line ;
    static Map<Integer, Stack<String>> rackList = null;
        public static void main(String[] args) {
            Stack<String> rackOne = new Stack<>();
            Stack<String> rackTwo = new Stack<>();
            Stack<String> rackThree = new Stack<>();
            Stack<String> rackFour = new Stack<>();
            Stack<String> rackFive = new Stack<>();
            Stack<String> rackSix = new Stack<>();
            Stack<String> rackSeven = new Stack<>();
            Stack<String> rackEight = new Stack<>();
            Stack<String> rackNine = new Stack<>();

            rackList = new HashMap<>();
            rackList.put(1,rackOne);
            rackList.put(2,rackTwo);
            rackList.put(3,rackThree);
            rackList.put(4,rackFour);
            rackList.put(5,rackFive);
            rackList.put(6,rackSix);
            rackList.put(7,rackSeven);
            rackList.put(8,rackEight);
            rackList.put(9,rackNine);

            loadRackValues();
            puzzleOneEvaluate();
            puzzleTwoEvaluate();
        }

        private static void loadRackValues() {
            File inputFile = new File("InputOfPuzzle/InputDay5.txt");
            try (
                    Scanner sc = new Scanner(inputFile);){
                int countOverlapRanges = 0;
                while (sc.hasNext()) {
                    line = sc.nextLine();
                    if (!line.startsWith(" 1") && !line.isBlank())
                        splitInMultipleRacks(line);
                    else
                        System.out.println("rackLoaded");
                }
                System.out.println(countOverlapRanges);
            } catch (Exception e) {
                System.out.println("Error while processing the input of Puzzle 1");
            }
        }

        private static void puzzleOneEvaluate() {
            File inputFile = new File("InputOfPuzzle/InputDay5-1.txt");
            try (
                    Scanner sc = new Scanner(inputFile);){
                int countOverlapRanges = 0;
                while (sc.hasNext()) {
                    line = sc.nextLine();

                    if (line.startsWith("move"))
                         moveLogic(line);
                }
                int loopOfRacks=1;
                while (loopOfRacks<=rackList.size()) {
                    System.out.print(rackList.get(loopOfRacks).get(0).substring(1,2));
                    loopOfRacks++;
                }
                System.out.println(countOverlapRanges);
            } catch (Exception e) {
                System.out.println("Error while processing the input of Puzzle 2");
            }
        }

    private static void puzzleTwoEvaluate() {
        File inputFile = new File("InputOfPuzzle/InputDay5-1.txt");
        try (
                Scanner sc = new Scanner(inputFile);){
            int countOverlapRanges = 0;
            while (sc.hasNext()) {
                line = sc.nextLine();

                if (line.startsWith("move"))
                    moveLogic9001(line);
            }
            int loopOfRacks=1;
            while (loopOfRacks<=rackList.size()) {
                System.out.print(rackList.get(loopOfRacks).get(0).substring(1,2));
                loopOfRacks++;
            }
            System.out.println(countOverlapRanges);
        } catch (Exception e) {
            System.out.println("Error while processing the input of Puzzle 1");
        }
    }


        private static void splitInMultipleRacks(String line) {
            int charPosition = 0;
            int rackCounter = 1;
            while (rackCounter<=9) {
                    if (charPosition<line.length() && !line.substring(charPosition, charPosition + 3).isBlank()) {
                        rackList.get(rackCounter).add(line.substring(charPosition, charPosition + 3));
                    }


                charPosition+=4;
                rackCounter++;
            }

        }

        private static void moveLogic (String command)
        {
            String[] cmd = command.split(" ");
            int countToMove = Integer.parseInt(cmd[1]);
            int moveFrom = Integer.parseInt(cmd[3]);
            int moveTo = Integer.parseInt(cmd[5]);
            int moveCounter = 0;
            String valueToNoMove="";
            Stack<String> moveFromRack ;
            Stack<String> moveToRack ;
            while (moveCounter<countToMove) {
                moveFromRack = rackList.get(moveFrom);
                valueToNoMove = moveFromRack.remove(0);
                moveToRack = rackList.get(moveTo);
                moveToRack.add(0,valueToNoMove);
                moveCounter++;
            }

        }

    private static void moveLogic9001 (String command)
    {
        String[] cmd = command.split(" ");
        int countToMove = Integer.parseInt(cmd[1]);
        int moveFrom = Integer.parseInt(cmd[3]);
        int moveTo = Integer.parseInt(cmd[5]);
        int moveCounter = countToMove;
        String valueToNoMove="";
        Stack<String> moveFromRack ;
        Stack<String> moveToRack ;
        while (moveCounter>0) {
            moveFromRack = rackList.get(moveFrom);
            valueToNoMove = moveFromRack.remove(moveCounter-1);
            moveToRack = rackList.get(moveTo);
            moveToRack.add(0,valueToNoMove);
            moveCounter--;
        }

    }

}
