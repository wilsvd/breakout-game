package com.example.dmscoursework.Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * The ScoreWriter will write to a file scoreStore.txt with the information
 * that a player has such as their username they inputted, their time taken
 * to beat the game as well their score.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class ScoreWriter {

    /**
     * Writes to a file with information regarding the player.
     *
     * @param username a String, username of the player.
     * @param score an integer, which is the score of the player.
     * @param time an integer, which is the time taken to complete game.
     */
    public void writeFile(String username, int score, int time) {
        try {
            File f1 = new File("scoreStore.txt");
            if (!f1.exists()) {
                f1.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(f1.getName(), true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(username + "." + score + "." + time + "s\n");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
