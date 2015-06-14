
package com.module.album;

import com.android.uiautomator.core.UiObjectNotFoundException;

public interface IAlbum {

    /**
     * Launch Album.
     */
    void startAlbum() throws UiObjectNotFoundException;

    /**
     * Launch Sketch
     */
    void startSketch() throws UiObjectNotFoundException;

    /**
     * Drawing a picture from Sketch
     */
    void drawingAPictureBySketch() throws UiObjectNotFoundException;

    /**
     * Exit sketch drawing status.
     */
    void exitSketchDrawingStatus() throws UiObjectNotFoundException;
  
    /**
     * Open one picture in Album.
     */
    void openOnePicture() throws UiObjectNotFoundException;

    /**
     * View picture in album.
     *
     * @param viewCount the count of pictures
     */
    void viewPictureInAlbum(int viewCount);

    /**
     * Delete picture in album
     */
    void deletePictureInAlbum() ;

	/**
     * Open a screenshot in Album.
     */
    void openScreenshot() throws UiObjectNotFoundException;

    /**
     * Select slideshow mode in more menu. Make sure one picture already opened
     * before invoke this method.
     */
    void selectSlideshow() throws UiObjectNotFoundException;

    /**
     * Rotate picture to 4 directions.
     */
    void rotatePicture() throws UiObjectNotFoundException;

    /**
     * Copy screenshot to SD card.
     */
    void copyToSdcard() throws UiObjectNotFoundException;

    /**
     * Tag picture on map.
     *
     * @throws UiObjectNotFoundException
     */
    void tagPictureOnMap() throws UiObjectNotFoundException;

    void viewSlideshow(int duration) throws UiObjectNotFoundException;

    void startOnlineAlbum() throws UiObjectNotFoundException;

    void scanPicture(int viewCount);

    public void selectItemFromMenu() throws UiObjectNotFoundException;

    public void selectPicture(int location);

    void selectPictureByLandscape(int location);

    public void longTapPicture(int location);

    public void markAndUnMarkOnePictureFromMenu() throws UiObjectNotFoundException;

    public void markSeveralItemsAndDelete() throws UiObjectNotFoundException;

    public void markItemsByLongTap(boolean back);

    /**
     * Play video. Make sure entered Album, and selected one video to view.
     */
    void playVideo();

    /**
     * Select delete but cancel. Make sure entered Album, and selected one video/picture to view.
     *
     * @throws UiObjectNotFoundException
     */
    void selectDeleteButCancel() throws UiObjectNotFoundException;

    /**
     * Select one video/picture to view, delete this video/picture.
     *
     * @throws UiObjectNotFoundException
     */
    void deletePictureOrVideo() throws UiObjectNotFoundException;

    /**
     * Set picture as contact photo by contact Name. Make sure picture has been
     * opened in Album before invoke this method.
     *
     * @param contactName
     * @throws UiObjectNotFoundException
     */
    void setPictureAsContactPhoto(String contactName) throws UiObjectNotFoundException;

    /**
     * Set picture as wallpaper. Make sure picture has been opened in Album
     * before invoke this method.
     *
     * @throws UiObjectNotFoundException
     */
    void setPictureAsWallpaper() throws UiObjectNotFoundException;

    /**
     * Flick left and right to check pictures.
     */
    void flickLeftAndRightToCheckPictures();

    /**
     * Edit picture with photo editor in album
     *
     * @throws UiObjectNotFoundException
     */
    public void editPictureWithPhotoEditor() throws UiObjectNotFoundException;

    /**
     * Edit picture with photos in album
     *
     * @throws UiObjectNotFoundException
     */
    public void editPictureWithPhotos() throws UiObjectNotFoundException;

    /**
     * Edit picture with sketch in album
     *
     * @throws UiObjectNotFoundException
     */
    public void editPictureWithSketch() throws UiObjectNotFoundException;

    public void shareOnePictureToFacebook(String comments) throws UiObjectNotFoundException;

    public void openTheFirstAlbumFromFacebook() throws UiObjectNotFoundException;

    public void playSenseMeSlideshow(String theme, String music) throws UiObjectNotFoundException;

    public void shareOnePictureToEmail(String account, String subject) throws UiObjectNotFoundException;

    public void shareOnePictureToMMS(String recipient, String content) throws UiObjectNotFoundException;

	public void swipeToPictureView() throws UiObjectNotFoundException;

	void editNameTags(String name) throws UiObjectNotFoundException;

	void checkPictureDetails() throws UiObjectNotFoundException;

	void checkAllMoreMenuInAlbumMenu() throws UiObjectNotFoundException;
	
	public void openThrowInAlbum() throws UiObjectNotFoundException;

}
