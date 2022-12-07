package acc.year2022;

import java.io.File;
import java.util.*;

import static java.lang.Character.isDigit;

public class DaySevenACC {
    static String prefix ;
    static String currentDirectory;
    static boolean lsIsTriggered;
    static Directory directoryInfo;
    static Directory currentDir;
    static Integer totalSum=0;
    public static void main(String[] args) {
        genericProcessing();
        // Puzzle1
        System.out.println("Puzzle 1 Done"+puzzleOneSizeCheckThanThreshold(directoryInfo));

        //calculate minimum size required to delete
        int required = 30000000 - (70000000 - sizeTotal(directoryInfo));

        System.out.println("Puzzle 2 Done"+puzzleTwoCheck(directoryInfo,required));

    }

    static void genericProcessing() {
        String line ;

        File inputFile = new File("InputOfPuzzle/InputDay7.txt");
        try (
                Scanner sc = new Scanner(inputFile);){
            int counter = 1;
            while (sc.hasNext()) {
                line = sc.nextLine();
                judgeTheCommand(line,counter);
                counter++;
            }
            processDirectoryInfos(directoryInfo);

        } catch (Exception e) {
            System.out.println("Error while processing the input of Puzzle 1");
        }
    }

    private static int puzzleTwoCheck(Directory dir, int required) {
        int smallest = Integer.MAX_VALUE;
        for(Directory directory  : dir.directoryList)
            smallest = Math.min(smallest,puzzleTwoCheck(directory,required));
        if(sizeTotal(dir) > required)
            smallest = Math.min(smallest,sizeTotal(dir));
        return smallest;
    }

    private static int processDirectoryInfos(Directory directory) {
        int sumOfFileSize=0;
        int sumOfSubDirectories=0;
        for (Map.Entry<String,Integer> entry : directory.files.entrySet()) {
            sumOfFileSize += entry.getValue();
        }

        directory.directoryList.stream().forEach(
                dir ->  dir.directorySum= processDirectoryInfos(dir));
        return sumOfFileSize + sumOfSubDirectories;
    }


    private static int puzzleOneSizeCheckThanThreshold(Directory dir) {
        int total = 0;
        for( Directory directory : dir.directoryList)
            total += puzzleOneSizeCheckThanThreshold(directory);
        if(sizeTotal(dir) < 100000)
            total += sizeTotal(dir);
        return total;
    }

    private static  int sizeTotal(Directory dir) {
        int total = 0;
        try {
        for(Directory directory : dir.directoryList)
            total += sizeTotal(directory);
        total +=dir.directorySum;
        }
        catch (Exception exception) {
            System.out.println("error while calculating total");
        }
        return total;
    }

    private static void judgeTheCommand(String line, int counter) {

        try {

            if (line.startsWith("$ cd /")) {
                prefix = "root";
                directoryInfo = new Directory(null,prefix);
                currentDir = directoryInfo;
            }
            else if (line.startsWith("$ cd ..")) {
//                prefix = prefix.substring(0,prefix.length()-currentDirectory.length()-1);
                currentDir = currentDir.parentDirectory;
            }
            else if (line.startsWith("$ cd ")) {
                currentDirectory = line.replace("$ cd ","");
//                prefix = prefix + "," + currentDirectory;
                Directory  newSubDir = new Directory(currentDir,currentDirectory);
                currentDir.directoryList.add(newSubDir);
                currentDir = newSubDir;
            }
            else if (isDigit(line.toCharArray()[0])) {
                currentDir.files.put(line.split(" ")[1], Integer.parseInt(line.split(" ")[0]));
//                System.out.println(prefix + "," + line.split(" ")[1] +","+line.split(" ")[0]);

            }
        }
        catch (Exception exception) {
            System.out.println("error while processing line "+ line + "line Number" +counter);
        }


    }

    public static class Directory {
        String name;
        List<Directory> directoryList;
        Map<String, Integer> files;
        Directory parentDirectory;
        Integer directorySum=0;

        public Directory( Directory parentDirectory, String name) {
            this.parentDirectory = parentDirectory;
            this.files = new HashMap<>();
            this.name = name;
            this.directoryList = new ArrayList<>();
        }
    }

}

