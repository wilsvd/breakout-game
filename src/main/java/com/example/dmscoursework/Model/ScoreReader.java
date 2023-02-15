package com.example.dmscoursework.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 * The ScoreReader will read the scoreBoard.txt file and store the
 * information, so that the Scoreboard controller can use the data and
 * display it to the scoreboard. The ScoreReader before reading the file will
 * call the SortTextFile to make sure that the file is sorted before accessing
 * it.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class ScoreReader {

    private int SCORE_MAX = 10;

    String[] m_Scores = new String[10];
    private int m_Counter;

    /**
     * Get the counter.
     *
     * @return an integer, which is the index in the scores array.
     */
    public int GetM_Counter() {
        return m_Counter;
    }

    /**
     * Set the counter.
     *
     * @param counter an integer, which is the index in the scores array.
     */
    private void setM_Counter(int counter) {
        this.m_Counter = counter;
    }

    /**
     * Get the scores.
     *
     * @return a String[], which contains the scores.
     */
    public String[] GetM_Scores() {
        return m_Scores;
    }
    /**
     * Set the scores.
     *
     * @param i an integer, which is the index in array.
     * @param fileText a String, which is the text in the file.
     */
    private void setM_Scores(int i, String fileText) {
        this.m_Scores[i] = fileText;
    }

    /**
     * Reads the file and stores the information in an array.
     *
     * @throws IOException if file cannot be read.
     */
    public void readFile() throws IOException {
        new SortTextFile().sortFile();

        try {
            File f1 = new File("scoreboard.txt");
            Scanner myReader = new Scanner(f1);

            setM_Counter(0);
            while (myReader.hasNextLine() && GetM_Counter() < SCORE_MAX) {
                String fileText = myReader.nextLine();
                setM_Scores(GetM_Counter(), fileText);
                setM_Counter(GetM_Counter() + 1);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

