package com.module.media;

import java.io.File;

import com.android.uiautomator.core.UiObjectNotFoundException;

import android.view.KeyEvent;

public interface IMedia {

    /**
     * launch music player
     */
    void launchRadioPlayer();

    /**
     * Check if there is headset connected.
     * @connect if return true : headset connected.
     *          if return false: There is no headset connected.
     */
    boolean isHeadsetConnectedAPI();

    /**
     * Play Radio on background.
     */
    void playRadioOnBackground();

    /**
     * Stop Radio.
     */
    void stopRadio() throws UiObjectNotFoundException;

    /**
     * launch video.
     *
     */
    void launchVideoPlayer();

    void playVideo(String videoName);

    void fastForwardAndRewind(String videoName);

    void extendFullScreen();

    void waitVideoPlaying() throws UiObjectNotFoundException;
	/**
     * Search channel. Make sure FM radio is launched before invoking this
     * method.
     */
    void searchChannel()  throws UiObjectNotFoundException;

    /**
     * Set favorite channel. Make sure FM radio is launched before invoking this
     * method.
     */
    void setFavorChannel(int channel)  throws UiObjectNotFoundException;

    /**
     * Edit Favorite Channel. Make sure FM radio is launched before invoking
     * this method.
     */
    void editFavorChannel()  throws UiObjectNotFoundException;

    /**
     * Delete Favorite Channel. Make sure FM radio is launched before invoking
     * this method.
     */
    void deleteFavorChannel() throws UiObjectNotFoundException;

    /**
     * Mark several channels as favorites. Make sure FM radio is launched before
     * invoking this method.
     */
    void markSeveralChannelsAsFavorites(int Num) throws UiObjectNotFoundException;

    /**
     * Check favorite channels. Make sure FM radio is launched before invoking
     * this method.
     *
     * @Num The favorite channels count.
     */
    void checkFavoriteChannels(int Num)  throws UiObjectNotFoundException;

    /**
     * Turn on or off Speaker. Make sure FM radio is launched before invoking
     * this method.
     *
     * @flag On or off
     */
    void TurnOnOrOffSpeaker(String flag) throws UiObjectNotFoundException;

    /**
     * Select all favorite channels one bye one. Make sure FM radio is launched
     * before invoking this method.
     *
     * @Num The favorite channels count.
     */
    void selectAllFavoriteChannels(int Num) throws UiObjectNotFoundException;


    void launchVideoPlayerOneByOne(String videoFolder) throws UiObjectNotFoundException;

    void verifyVideoPlayerState();

    /**
     * Launch track id
     */
    void launchTrackID();

    /**
     * Search music in track id
     *
     * @param searchMusic
     */
    void searchSongs(String searchMusic);

	public void playVideoURL(File dir,String videoName) ;

    /**
     * Launch YouTube.
     */
    void launchYouTube() throws UiObjectNotFoundException;

    /**
     * Clear search history in YouTube.
     */
    void clearSearchHistory() throws UiObjectNotFoundException;

    /**
     * Choose one video to play.
     */
    void chooseOneVideoToPlay() throws UiObjectNotFoundException;

    /**
     * Control player, like play/pause, forward/backward, customized frame.
     * @throws UiObjectNotFoundException
     */
    void playerControl() throws UiObjectNotFoundException;

    /**
     * Search video in YouTube.
     */
    void searchVideo() throws UiObjectNotFoundException;

    /**
     * Set the video play on backgroud on or off
     * @param String option: on , off
     *
     */
    void setVideoPlayBackgroundSetting(String option) throws UiObjectNotFoundException;

    void playingVideoOneByOne(String videoFolder, int duration) throws UiObjectNotFoundException;

    /**
     * Check FM Radio UI.
     */
    void checkFmRadioMmi();

    /**
     * Check radio dial UI.
     */
    void checkRadioDial();

    /**
     * Scroll down radio dial to bottom, then scroll up to top.
     */
    void scrollUpOrDownRadioDial();

    /**
     * Drag radio dial slowly by frequency.
     */
    void dragRadioDialSlowly();

    /**
     * Change Visualizer setting.
     *
     * @param visualizer: visualizer name.
     * @throws UiObjectNotFoundException
     */
    void changeVisualizerSetting(String visualizer) throws UiObjectNotFoundException;

    /**
     * Press FM Radio volume key.
     */
    void pressRadioVolumeKey();

    /**
     * Long press FM Radio volume up key.
     */
    void longPressRadioVolumeUpKey();

    /**
     * Long press FM Radio volume down key.
     */
    void longPressRadioVolumeDownKey();

    /**
     * press FM Radio volume mute key.
     */
    void pressRadioVolumeMuteKey();

    /**
     * Turn off FM Radio.
     *
     * @throws UiObjectNotFoundException
     */
    void turnOffFMRadio() throws UiObjectNotFoundException;

    /**
     * Turn on FM Radio.
     *
     * @throws UiObjectNotFoundException
     */
    void turnOnFMRadio() throws UiObjectNotFoundException;

    /**
     * Tap next button to check all channels.
     *
     * @throws UiObjectNotFoundException
     */
    void checkAllChannelsByTapNextButton() throws UiObjectNotFoundException;
    
    public boolean searchSongsWithoutName();
    
    public void repeatSearchSongs(int times) throws UiObjectNotFoundException;
}
