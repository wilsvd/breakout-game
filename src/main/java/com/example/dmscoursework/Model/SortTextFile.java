package com.example.dmscoursework.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * The User class will store the relevant information of a player.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
class User
{
    private String m_Name;
    private int m_Score;
    private String m_Time;

    protected String getName() {
        return m_Name;
    }
    protected int getScore() {
        return m_Score;
    }
    protected String getTime() {
        return m_Time;
    }

    /**
     * Constructs a User which contains the information of the player such as
     * name, score and time.
     *
     * @param name
     * @param score
     * @param time
     */
    protected User(String name, int score, String time)
    {
        this.m_Name = name;
        this.m_Score = score;
        this.m_Time = time;
    }
}

/**
 * The scoreCompare class will compare the scores between users.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
class scoreCompare implements Comparator<User>
{
    @Override
    public int compare(User u1, User u2)
    {
        return u2.getScore() - u1.getScore();
    }
}

/**
 * The SortTextFile class will sort the players in order of score rankings.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class SortTextFile
{
    private ArrayList<User> m_UserRecord;

    /**
     * Gets the record of user data.
     *
     * @return an ArrayList<User>, which stores the information of players.
     */
    private ArrayList<User> getM_UserRecord() {
        return m_UserRecord;
    }

    /**
     * Sets the record of user data.
     *
     * @param userRecord a ArrayList<User>, which stores the information of
     *                   players.
     */
    private void setM_UserRecord(ArrayList<User> userRecord) {
        this.m_UserRecord = userRecord;
    }

    /**
     * Sorts the file by going through file with stored information and
     * storing it in an array and adding the array to an arrayList where the
     * arrayList is then sorted by the scores of the players.
     *
     * @throws IOException if file cannot be sorted.
     */
    public void sortFile() throws IOException
    {
        File file = new File("scoreStore.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        setM_UserRecord(new ArrayList<>());
        String currentLine = reader.readLine();

        while (currentLine != null)
        {
            String[] userInfo = currentLine.split("\\.");
            String m_Name = userInfo[0];
            int m_Score = Integer.valueOf(userInfo[1]);

            String m_Time = userInfo[2];
            getM_UserRecord().add(new User(m_Name, m_Score, m_Time));
            currentLine = reader.readLine();
        }
        Collections.sort(getM_UserRecord(), new scoreCompare());

        makeSortedFile();
        reader.close();
    }

    /**
     * Makes the new file with the sorted information.
     *
     * @throws IOException
     */
    private void makeSortedFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(
                "scoreboard.txt"));

        for (User user : getM_UserRecord())
        {
            writer.write(user.getName());
            writer.write("."+user.getScore());
            writer.write("."+user.getTime());
            writer.newLine();
        }

        writer.close();
    }
}