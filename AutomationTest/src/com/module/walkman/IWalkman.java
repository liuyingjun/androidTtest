package com.module.walkman;

import java.io.File;

import com.android.uiautomator.core.UiObjectNotFoundException;

public interface IWalkman {

    /**
     * launch music player
     */
	public void launchWALKMANPlayer();

    /**
     * Play music file on background.
     *
     */
    void playMusicOnBackground()  throws UiObjectNotFoundException;

    public void playMusic(String musicName)  throws UiObjectNotFoundException;

    public void stopMusicWithWALKMAN()  throws UiObjectNotFoundException;

    public void stopMusicFromStatusBar()  throws UiObjectNotFoundException;

    void launchMusicUrl(String musicFolder) throws UiObjectNotFoundException;

    void setRepeatOne()  throws UiObjectNotFoundException;

    void playNextMusic()  throws UiObjectNotFoundException;

    void playPreviousMusic() throws UiObjectNotFoundException;

    void verifyMusicPlaying();

    void playMusic()  throws UiObjectNotFoundException;

    void setPlayMode(String playMode) throws UiObjectNotFoundException ;

    void isMusicSupported();

    void waitPlayingMusic() throws UiObjectNotFoundException;

    void playMusicURL(String dir, String musicName);

    //this state is the state with facebook icon maybe, add favorite icon, infinite icon
    void verifyAlbumStateWhenMusicPlaying(String musicName , boolean verifyMusicNameOnly)  throws UiObjectNotFoundException;

    String getCurrentSongName() throws UiObjectNotFoundException;

    void fastForwardCurrentSong() throws UiObjectNotFoundException;

    void fastRewindCurrentSong() throws UiObjectNotFoundException;
    /*
     * Set shuffle on/off
     * @param shuffle true turn on shuffle, false turn off shuffle
    */
    public void setShuffle(boolean shuffle) throws UiObjectNotFoundException;

    public void likeOneMusicAndAddComments(String comments) throws UiObjectNotFoundException;

    public void unlikeMusic()  throws UiObjectNotFoundException;

    public void stopPlayingMusic();

    public void startPlayingMusic() throws UiObjectNotFoundException;

    public void likeFriendsMusic() throws UiObjectNotFoundException;

    public void verifyMusicStateUnderLockScreen();

    public void switchToNextSongUnderLockScreenState() throws UiObjectNotFoundException;

    public void switchToPreSongUnderLockScreenState() throws UiObjectNotFoundException;

    /**
     * Check music status from Notification bar.
     *
     * @param value: playing/pause.
     */
    public void checkMusicStatus(String value);

    public void checkSearchKey() throws UiObjectNotFoundException;

    public void pausePlayMusicAndVerify() throws UiObjectNotFoundException;

    public void gotoPlaylist() throws UiObjectNotFoundException;

    public void checkDefaultPlaylist();

    public void createPlaylist(String name, boolean addSong, int addSongNumber) throws UiObjectNotFoundException;

    public void deletePlaylist(String name) throws UiObjectNotFoundException;

    void playMusicUI();
    
    public void playNextMusicFromStatusBar() throws UiObjectNotFoundException;
    
    public String getCurrentSongNameFromStatusBar() throws UiObjectNotFoundException;
}
