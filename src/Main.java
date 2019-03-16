/**
 * CLASS: Main (main.java)
 *
 * DESCRIPTION
 *
 * COURSE AND PROJECT INFO
 * CSE205 Object Oriented Programming and Data Structures, Spring B 2019
 * PROJECT NUMBER: 1
 *
 * AUTHOR: Vincent Hoang, vnhoang, vnhoang@asu.edu
 */

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {

    }

    private ArrayList<Integer> findRuns(ArrayList<Integer> pList, int pDir) {

    }

    private ArrayList<Integer> merge(ArrayList<Integer> pListRunsUpCount, ArrayList<Integer> pListRunsDnCount) {

        return null;
    }

    private ArrayList<Integer> arrayListCreate(int pSize, int pInitValue) {

        return new ArrayList<>();
    }

    private void writeFile(String pFileName, ArrayList<Integer> pListRuns) {
        FileWriter mFileWriter;

        try {
            mFileWriter = new FileWriter(pFileName);

        } catch (IOException e) {

        }
    }

    private ArrayList<Integer> getFileReader(String pFileName) {
        FileReader mFileReader;

        try {
            mFileReader = new FileReader(pFileName);


            return new ArrayList<>();

        } catch (FileNotFoundException e) {

        }

        return null;
    }
}
