/**
 * CLASS: Main (main.java)
 * <p>
 * DESCRIPTION
 * <p>
 * COURSE AND PROJECT INFO
 * CSE205 Object Oriented Programming and Data Structures, Spring B 2019
 * PROJECT NUMBER: 1
 * <p>
 * AUTHOR: Vincent Hoang, vnhoang, vnhoang@asu.edu
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /**
     * main()
     *
     * calls run() to exit static method
     */
    public static void main(String[] args) {
        new Main().run();
    }


    /**
     * run()
     *
     * This is the main body of the program. Reads variables from an input file ('p01-in.txt'), performs an analysis
     * on the input numbers of how many runs, or consecutive number arrangements, are found in the input data set,
     * then outputs the results to an output file ('p01-runs.txt').
     */
    private void run() {
        String inputFileName = "p01-in.txt";
        String outputFileName = "p01-runs.txt";

        int RUNS_UP = 1;
        int RUNS_DN = 0;

        try {
            ArrayList<Integer> list = readInputFile(inputFileName);

            ArrayList<Integer> listRunsUpCount = findRuns(list, RUNS_UP);

            ArrayList<Integer> listRunsDnCount = findRuns(list, RUNS_DN);

            ArrayList<Integer> listRunsCount = merge(listRunsUpCount, listRunsDnCount);

            writeOutputFile(outputFileName, listRunsCount);
        } catch (FileNotFoundException e) {
            System.out.println("Oops, could not open \'" + inputFileName + "\' for reading. The program is ending.");
            System.exit(-100);
        } catch (IOException e) {
            System.out.println("Oops, could not open \'" + outputFileName + "\' for writing. The program is ending.");
            System.exit(-200);
        }
    }

    /**
     * findRuns()
     *
     * finds how many consecutive numbers are found within the data set while traversing in a specified direction
     *
     * @param pList an ArrayList containing Integers as the input data set
     * @param pDir the direction the method is traveling in. pDir = 1 for counting runs going upwards
     *             pDir = 2 for counting runs going downwards
     * @return ArrayList of type Integer containing how many runs are found at each length from 0 to pList.size()
     */
    private ArrayList<Integer> findRuns(ArrayList<Integer> pList, int pDir) {
        ArrayList<Integer> listRunsCount = arrayListCreate(pList.size(), 0);

        int i = 0;
        int k = 0;

        while (i < pList.size() - 1) {
            if (pDir == 1 && pList.get(i) <= pList.get(i + 1)) {
                k++;
            } else if (pDir == 0 && pList.get(i) >= pList.get(i + 1)) {
                k++;
            } else {
                if (k != 0) {
                    int valueToIncrement = listRunsCount.get(k);
                    listRunsCount.set(k, valueToIncrement + 1);

                    k = 0;
                }
            }
            i++;
        }

        if (k != 0) {
            int valueToIncrement = listRunsCount.get(k);
            listRunsCount.set(k, valueToIncrement + 1);
        }

        return listRunsCount;
    }

    /**
     * merge()
     *
     * merges the data values of the two directional runs lists to report a count of how many runs are found at each
     * interval
     *
     * @param pListRunsUpCount input ArrayList containing runs going up
     * @param pListRunsDnCount input ArrayList containing runs going down
     * @return combined ArrayList of type Integer containing a combined value for runs up and down at each interval
     */
    private ArrayList<Integer> merge(ArrayList<Integer> pListRunsUpCount, ArrayList<Integer> pListRunsDnCount) {
        ArrayList<Integer> listRunsCount = arrayListCreate(pListRunsUpCount.size(), 0);

        for (int i = 0; i < pListRunsUpCount.size() - 1; i++) {
            int combinedValue = pListRunsUpCount.get(i) + pListRunsDnCount.get(i);
            listRunsCount.set(i, combinedValue);
        }

        return listRunsCount;
    }

    /**
     * arrayListCreate()
     *
     * instantiates an ArrayList of type Integer with a specified size and a specified default value for each
     * Integer object contained in the ArrayList
     *
     * @param pSize the initial size of the ArrayList to be created
     * @param pInitValue the default value for each Integer object in the ArrayList
     * @return ArrayList of type Integer containing the above
     */
    private ArrayList<Integer> arrayListCreate(int pSize, int pInitValue) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < pSize; i++) {
            list.add(pInitValue);
        }

        return list;
    }

    /**
     * writeOutputFile()
     *
     * uses FileWriter to write the results of the combined runs list to a txt file with the specified file name.
     *
     * @param pFileName the name of the file to be written
     * @param pListRuns the ArrayList containing data to be written
     * @throws IOException if the file is unable to be accessed or written to
     */
    private void writeOutputFile(String pFileName, ArrayList<Integer> pListRuns) throws IOException {

        FileWriter mFileWriter = new FileWriter(pFileName);

        int pListRunsSum = 0;

        for (int i : pListRuns) {
            pListRunsSum += i;
        }

        mFileWriter.write("runs_total: " + pListRunsSum + "\n");
        mFileWriter.flush();

        for (int i = 1; i < pListRuns.size() - 1; i++) {
            mFileWriter.write("runs_k: " + pListRuns.get(i) + "\n");
            mFileWriter.flush();
        }

        mFileWriter.close();
    }

    /**
     * readInputFile()
     *
     * uses Scanner to parse input txt file with the specified name and adds all found int variables to an
     * ArrayList of type Integer
     *
     * @param pFileName the filename of the input data set
     * @return ArrayList of type Integer containing a formatted version of the input txt file
     * @throws FileNotFoundException if the input file is not found or able to be accessed
     */
    private ArrayList<Integer> readInputFile(String pFileName) throws FileNotFoundException {
        ArrayList<Integer> list = new ArrayList<>();

        Scanner mScanner = new Scanner(new File(pFileName));

        while (mScanner.hasNext()) {
            if (mScanner.hasNextInt()) {
                list.add(mScanner.nextInt());
            } else {
                mScanner.next();
            }
        }

        mScanner.close();

        return list;
    }
}
