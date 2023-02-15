package com.example.dmscoursework.Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
/**
 * The MusicController is in charge of the music. The music controller is in
 * charge of getting the music, playing, pausing, resetting, etc. The user
 * will interact with the MusicController through the MusicScreen controller.
 * Buttons pressed on the MusicScreen controller will call the
 * MusicController to enact the users will.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class MusicController  {

    private final int PERIOD = 1000;
    private final double VOLUME = 0.5;

    private Media m_Media;
    private MediaPlayer m_MediaPlayer;
    private File m_Directory;
    private File[] m_Files;
    private ArrayList<File> m_Songs;
    private Timer m_Timer;
    private TimerTask m_Task;

    private int m_SongNumber = 0;
    private boolean m_Running;

    private static MusicController instance = new MusicController();

    /**
     * Get the media
     *
     * @return a Media
     */
    private Media getM_Media() {
        return m_Media;
    }

    /**
     * Set the media.
     *
     * @param media a Media.
     */
    private void setM_Media(Media media) {
        this.m_Media = media;
    }

    /**
     * Get media player.
     *
     * @return a MediaPlayer.
     */
    public MediaPlayer GetMediaPlayer() {
        return m_MediaPlayer;
    }
    /**
     * Set the media player.
     *
     * @param mediaPlayer a MediaPlayer.
     */
    private void setM_MediaPlayer(MediaPlayer mediaPlayer) {
        this.m_MediaPlayer = mediaPlayer;
    }

    /**
     * Get the directory of where music files located.
     *
     * @return a File, where music files located.
     */
    private File getM_Directory() {
        return m_Directory;
    }
    /**
     * Set the directory of where music files located.
     *
     * @param directory a File, where music files located.
     */
    private void setM_Directory(File directory) {
        this.m_Directory = directory;
    }

    /**
     * Get the music files.
     *
     * @return a File[], which contains music files.
     */
    private File[] getM_Files() {
        return m_Files;
    }
    /**
     * Set the music files.
     *
     * @param files a File[], which contains music files.
     */
    private void setM_Files(File[] files) {
        this.m_Files = files;
    }

    /**
     * Get the arraylist of song files.
     * @return an ArrayList<File>, which contains the songs.
     */
    private ArrayList<File> getM_Songs() {
        return m_Songs;
    }

    /**
     * Set the ArrayList to contain the music files.
     *
     * @param songs a ArrayList<File>, which contains the song.
     */
    private void setM_Songs(ArrayList<File> songs) {
        this.m_Songs = songs;
    }

    /**
     * Get the song number.
     *
     * @return an integer, which is the song number.
     */
    private int getM_SongNumber() {
        return m_SongNumber;
    }

    /**
     * Set the song number.
     *
     * @param songNumber an integer, which is the song number.
     */
    private void setM_SongNumber(int songNumber) {
        this.m_SongNumber = songNumber;
    }

    /**
     * Get the volume of the media player.
     *
     * @return a double, which is the volume.
     */
    public double GetM_Volume() {
        return GetMediaPlayer().getVolume();
    }
    /**
     * Set the volume of the mediaplayer.
     *
     * @param volume a double.
     */
    public void SetM_Volume(double volume) {
        GetMediaPlayer().setVolume(volume);
    }

    /**
     * Get the timer of the songs.
     *
     * @return a Timer.
     */
    private Timer getM_Timer() {
        return m_Timer;
    }
    /**
     * Set the timer of the songs.
     *
     * @param timer
     */
    private void setM_Timer(Timer timer) {
        this.m_Timer = timer;
    }

    /**
     * Get the timer task.
     *
     * @return a TimerTask.
     */
    private TimerTask getM_Task() {
        return m_Task;
    }
    /**
     * Set the timer task.
     *
     * @param task a TimerTask.
     */
    private void setM_Task(TimerTask task) {
        this.m_Task = task;
    }

    /**
     * Get if timer is running or not.
     *
     * @return a boolean, which is if the timer is running or not.
     */
    private boolean isM_Running() {
        return m_Running;
    }
    /**
     * Set if the timer is running or not.
     *
     * @param running a boolean, which is if the timer is running or not.
     */
    private void setM_Running(boolean running) {
        this.m_Running = running;
    }

    /**
     * Gets the music controller instance.
     *
     * @return a MusicController.
     */
    public static MusicController getInstance() {
        return instance;
    }

    /**
     * Constructs the MusicController which has the music to be played and
     * the volume for the music to be played at.
     */
    private MusicController() {
        setM_Songs(new ArrayList<>());
        setM_Directory(new File("src/Assets/music"));
        setM_Files(getM_Directory().listFiles());

        if (getM_Files() != null) {
            for (File file : getM_Files()) {
                getM_Songs().add(file);
            }
        }
        setM_Media(new Media(getM_Songs().get(getM_SongNumber()).toURI().toString()));
        setM_MediaPlayer(new MediaPlayer(getM_Media()));
        SetM_Volume(VOLUME);
    }

    /**
     * Plays the media.
     */
    public void PlayMedia() {
        beginTimer();
        SetM_Volume(GetM_Volume());
        GetMediaPlayer().play();
    }

    /**
     * Pauses the media.
     */
    public void PauseMedia() {
        CancelTimer();
        SetM_Volume(GetMediaPlayer().getVolume());
        GetMediaPlayer().pause();
    }

    /**
     * Resets the media.
     */
    public void ResetMedia() {
        GetMediaPlayer().seek(Duration.seconds(0.0));
    }

    /**
     * Controls the media player by setting the media and the volume.
     */
    private void ControlPlayer() {
        GetMediaPlayer().stop();

        if (isM_Running()) {
            CancelTimer();
        }
        setM_Media(new Media(getM_Songs().get(getM_SongNumber()).toURI().toString()));
        double volume = GetM_Volume();
        setM_MediaPlayer(new MediaPlayer(getM_Media()));
        SetM_Volume(volume);

        PlayMedia();
    }

    /**
     * Increment the song number and if it is the last song it goes to the
     * first song.
     */
    public void NextMedia() {
        if (getM_SongNumber() < getM_Songs().size() - 1) {
            setM_SongNumber(getM_SongNumber() + 1);
        } else {
            setM_SongNumber(0);
        }
        ControlPlayer();
    }

    /**
     * Decrement the song number and if it is the first song it goes to the
     * last song.
     */
    public void PreviousMedia() {
        if (getM_SongNumber() > 0) {
            setM_SongNumber(getM_SongNumber() - 1);
        } else {
            setM_SongNumber(getM_Songs().size() - 1);
        }
        ControlPlayer();
    }

    /**
     * Starts the timer.
     */
    private void beginTimer() {
        setM_Timer(new Timer());
        setM_Task(new TimerTask() {
            @Override
            public void run() {
                setM_Running(true);
                double current = GetMediaPlayer().getCurrentTime().toSeconds();
                double end = GetMediaPlayer().getTotalDuration().toSeconds();

                if (current/end == 1.0) {
                    CancelTimer();
                    NextMedia();
                }
            }
        });
        getM_Timer().scheduleAtFixedRate(getM_Task(), 0, PERIOD);
    }

    /**
     * Cancels the timer.
     */
    public void CancelTimer() {
        if (getM_Timer() != null) {
            setM_Running(false);
            getM_Timer().cancel();
        }
    }
}