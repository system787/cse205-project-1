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

    public static void main(String[] args) {
        new Main().run();
    }

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
        } catch (IOException e) {
            System.out.println("Oops, could not open \'" + outputFileName + "\' for writing. The program is ending.");
        }
    }

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

    private ArrayList<Integer> merge(ArrayList<Integer> pListRunsUpCount, ArrayList<Integer> pListRunsDnCount) {
        ArrayList<Integer> listRunsCount = arrayListCreate(pListRunsUpCount.size(), 0);

        for (int i = 0; i < pListRunsUpCount.size() - 1; i++) {
            int combinedValue = pListRunsUpCount.get(i) + pListRunsDnCount.get(i);
            listRunsCount.set(i, combinedValue);
        }

        return listRunsCount;
    }

    private ArrayList<Integer> arrayListCreate(int pSize, int pInitValue) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < pSize; i++) {
            list.add(pInitValue);
        }

        return list;
    }

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
